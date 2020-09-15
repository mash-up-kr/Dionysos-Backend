package com.dionysos.api.timehistory.service;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseRankingDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.RunningUser;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.exception.NotFoundTimeHistoryException;
import com.dionysos.api.timehistory.repository.RunningUserRedisRepository;
import com.dionysos.api.timehistory.repository.TimeHistoryRepository;
import com.dionysos.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TimeHistoryService {

    private final TimeHistoryRepository timeHistoryRepository;
    private final RunningUserRedisRepository runningUserRedisRepository;
    private static final int stndHour = 5;
    private static final int stndMinute = 0;

    @Transactional
    public void createOrUpdate(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                               User user
    ) {
        LocalDateTime standardTime = getStandardTime(requestSaveTimeHistoryDto.getHistoryDay());
        TimeHistory timeHistory = timeHistoryRepository.getTimeHistory(user.getId(),
                standardTime,
                requestSaveTimeHistoryDto.getHistoryDay())
                .orElseGet(() -> TimeHistory.builder()
                        .user(user)
                        .build());
        timeHistory.update(requestSaveTimeHistoryDto.getDuration(), requestSaveTimeHistoryDto.getHistoryDay());

        timeHistoryRepository.save(timeHistory);
        RunningUser running = runningUserRedisRepository.save(RunningUser.builder()
                .userId(user.getId())
                .duration(timeHistory.getDuration())
                .build());
    }

    public ResponseTimeHistoryDto getTotalHrDay(User user) {

        LocalDateTime standardTime = getStandardTime(LocalDateTime.now());
        TimeHistory entity = timeHistoryRepository.getDuration(user.getId(),
                standardTime,
                LocalDateTime.now()
        )
                .orElseThrow(NotFoundTimeHistoryException::new);
        return ResponseTimeHistoryDto.builder()
                .historyDay(entity.getHistoryDay())
                .duration(entity.getDuration())
                .build();
    }

    private LocalDateTime getStandardTime(LocalDateTime dateTime) {

        LocalTime time = LocalTime.of(stndHour, stndMinute);

        if (dateTime.getHour() < stndHour) {
            dateTime = dateTime.minusDays(1);
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        } else {
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        }
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> dayRanking() {
        LocalDateTime standardTime = getStandardTime(LocalDateTime.now());

        return timeHistoryRepository.dayRanking(standardTime,
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> weekRanking() {

        return timeHistoryRepository.weeklyMonthlyRanking(findWklyStndTms(),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> monthRanking() {

        return timeHistoryRepository.weeklyMonthlyRanking(findMnlyStndTms(),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime findWklyStndTms() {
        LocalDateTime time = LocalDate.now().atTime(stndHour, stndMinute);

        LocalTime baseTime = LocalTime.of(stndHour, stndMinute);
        if(LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.MONDAY)
                && LocalTime.now().isAfter(baseTime)) { }
        else {
            time = time.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }

        return time;
    }


    private LocalDateTime findMnlyStndTms() {
        LocalDateTime time = LocalDate.now().atTime(stndHour, stndMinute);

        LocalTime baseTime = LocalTime.of(stndHour, stndMinute);
        if(LocalDateTime.now().getDayOfMonth()==1
                && LocalTime.now().isBefore(baseTime)) {
            time = time.minusMonths(1);
        }
        else {
            time = time.with(TemporalAdjusters.firstDayOfMonth());
        }

        return time;
    }

}
