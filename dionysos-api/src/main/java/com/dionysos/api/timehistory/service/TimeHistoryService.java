package com.dionysos.api.timehistory.service;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
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

import java.time.LocalDateTime;

import static com.dionysos.api.common.util.DateUtil.*;


@RequiredArgsConstructor
@Service
public class TimeHistoryService {

    private final TimeHistoryRepository timeHistoryRepository;
    private final RunningUserRedisRepository runningUserRedisRepository;

    @Transactional
    public void createOrUpdate(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                               User user
    ) {
        LocalDateTime standardTime = getStandardTime(requestSaveTimeHistoryDto.getHistoryDay());
        LocalDateTime endTime = requestSaveTimeHistoryDto.getHistoryDay();

        TimeHistory timeHistory = timeHistoryRepository.getTimeHistory(user.getId(),
                standardTime,
                endTime)
                .orElseGet(() -> TimeHistory.builder()
                        .user(user)
                        .build());
        timeHistory.update(requestSaveTimeHistoryDto.getDuration(), endTime);
        timeHistoryRepository.save(timeHistory);

        runningUserRedisRepository.save(RunningUser.builder()
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
}
