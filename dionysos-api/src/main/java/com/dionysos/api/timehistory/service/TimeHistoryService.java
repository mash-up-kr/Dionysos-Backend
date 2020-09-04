package com.dionysos.api.timehistory.service;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.exception.NotFoundTimeHistoryException;
import com.dionysos.api.timehistory.repository.TimeHistoryRepository;
import com.dionysos.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TimeHistoryService {

    private final TimeHistoryRepository timeHistoryRepository;
    private final int stndHr = 6;

    @Transactional
    public void createOrUpdate(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                               User user
    ) {
        Optional<TimeHistory> dayRecOptional = timeHistoryRepository.getTimeHistory(user.getId(),
                getStandardTime(requestSaveTimeHistoryDto.getHistoryDay()),
                requestSaveTimeHistoryDto.getHistoryDay());
        if (dayRecOptional.isPresent()) {
            dayRecOptional.get().update(requestSaveTimeHistoryDto.getDuration(), requestSaveTimeHistoryDto.getHistoryDay());
        } else {
            create(requestSaveTimeHistoryDto, user);
        }
    }

    @Transactional
    public void create(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                       User user
    ) {
        TimeHistory timeHistory = TimeHistory.builder()
                .historyDay(requestSaveTimeHistoryDto.getHistoryDay())
                .user(user)
                .duration(requestSaveTimeHistoryDto.getDuration())
                .build();
        timeHistoryRepository.save(timeHistory);
    }

    public ResponseTimeHistoryDto getTotalHrDay(User user) {

        TimeHistory entity = timeHistoryRepository.getDuration(user.getId(),
                getStandardTime(LocalDateTime.now()),
                LocalDateTime.now()
        )
                .orElseThrow(NotFoundTimeHistoryException::new);
        return ResponseTimeHistoryDto.builder()
                .historyDay(entity.getHistoryDay())
                .duration(entity.getDuration())
                .build();
    }

    private LocalDateTime getStandardTime(LocalDateTime dateTime) {

        LocalTime time = LocalTime.of(6, 0);

        if (dateTime.getHour() < stndHr) {
            dateTime = dateTime.minusDays(1);
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        } else {
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        }
    }

}
