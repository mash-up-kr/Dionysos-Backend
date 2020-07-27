package com.dionysos.api.timehistory.service;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.RequestUpdateTimeHistoryDto;
import com.dionysos.api.timehistory.dto.RequestUpdateTimerTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.repository.TimeHistoryRepository;
import com.dionysos.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class TimeHistoryService {

    private final TimeHistoryRepository timeHistoryRepository;

    @Transactional
    public void create(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto, User user) throws Exception {

        if(timeHistoryRepository.getTimeHistory(user.getId(), getStandardTime(), LocalDateTime.now()).isPresent())
            throw new Exception("이미 기록된 날짜입니다. PUT 메소드로 요청해주세요.");

        TimeHistory timeHistory = TimeHistory.builder()
                .historyDay(requestSaveTimeHistoryDto.getHistoryDay())
                .user(user)
                .build();
        timeHistoryRepository.save(timeHistory);
    }

    @Transactional
    public void update(RequestUpdateTimeHistoryDto requestUpdateTimeHistoryDto, User user) {

        TimeHistory timeHistory = timeHistoryRepository.getTimeHistory(user.getId(), getStandardTime() , LocalDateTime.now()).orElseThrow(()-> new IllegalArgumentException("해당 timeHistory가 없습니다."));
        timeHistory.update(requestUpdateTimeHistoryDto.getDuration(), requestUpdateTimeHistoryDto.getHistoryDay());
    }


    public ResponseTimeHistoryDto findByUid(User user) {

        TimeHistory entity = timeHistoryRepository.getDuration(user.getId(), getStandardTime(), LocalDateTime.now())
                .orElseThrow(() -> new IllegalArgumentException("해당 timeHistory가 없습니다. user_id="+user.getUid() ));

        return new ResponseTimeHistoryDto(entity);
    }

    public void updateTimer(RequestUpdateTimerTimeHistoryDto requestUpdateTimerTimeHistoryDto, User user) {

        TimeHistory timeHistory = timeHistoryRepository.getTimeHistory(user.getId(), getStandardTime() , LocalDateTime.now()).orElseThrow(()-> new IllegalArgumentException("해당 timeHistory가 없습니다."));
        timeHistory.updateTimer(requestUpdateTimerTimeHistoryDto.getDuration());

    }

    private LocalDateTime getStandardTime() {
        LocalDateTime startTime = LocalDateTime.now();

        if(0<=startTime.getHour() && startTime.getHour()<6) {
            startTime= startTime.minusDays(1)
                    .plusHours(6-startTime.getHour())
                    .minusMinutes(startTime.getMinute())
                    .minusSeconds(startTime.getSecond())
                    .minusNanos(startTime.getNano());
        }
        else {
            startTime = startTime.minusHours(startTime.getHour()-6)
                    .minusMinutes(startTime.getMinute())
                    .minusSeconds(startTime.getSecond())
                    .minusNanos(startTime.getNano());
        }

        return startTime;
    }
}
