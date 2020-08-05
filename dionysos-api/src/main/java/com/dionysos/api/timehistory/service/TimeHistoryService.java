package com.dionysos.api.timehistory.service;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseRankingDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.repository.TimeHistoryRepository;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TimeHistoryService {

    private final TimeHistoryRepository timeHistoryRepository;

    @Transactional
    public void createOrUpdate(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                               User user
    ) {
        if (timeHistoryRepository.getTimeHistory(user.getId(),
                getStandardTime(requestSaveTimeHistoryDto.getHistoryDay()),
                requestSaveTimeHistoryDto.getHistoryDay())
                .isPresent()
        ) {
            //재시작, 일시정지(=중지)
            update(requestSaveTimeHistoryDto, user);
        } else {
            //전 날 작업 마무리 여부 확인
            try {
                dayEnd(user);
            } catch (Exception e) {
            } finally {
                create(requestSaveTimeHistoryDto, user);
            }
        }
    }


    @Transactional
    public void create(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                       User user
    ) {
        Long duration = countDuration(getStandardTime(requestSaveTimeHistoryDto.getHistoryDay()));

        TimeHistory timeHistory = TimeHistory.builder()
                .historyDay(requestSaveTimeHistoryDto.getHistoryDay())
                .user(user)
                .duration(duration)
                .build();
        timeHistoryRepository.save(timeHistory);
    }

    @Transactional
    public void update(RequestSaveTimeHistoryDto requestSaveTimeHistoryDto,
                       User user
    ) {

        TimeHistory timeHistory = timeHistoryRepository.getTimeHistory(user.getId(),
                getStandardTime(requestSaveTimeHistoryDto.getHistoryDay()),
                LocalDateTime.now()
        )
                .orElseThrow(() -> new IllegalArgumentException("해당 timeHistory가 없습니다."));
        timeHistory.update(requestSaveTimeHistoryDto.getDuration(), requestSaveTimeHistoryDto.getHistoryDay());
    }

    @Transactional
    public void dayEnd(User user) {
        TimeHistory timeHistory = timeHistoryRepository.getLastDay(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("마지막 날을 못 찾았습니다"));
        if (timeHistory.isRunning()) {
            int countedSecond = 60 - timeHistory.getHistoryDay().getSecond();
            int countedMinute = 60 * (60 - timeHistory.getHistoryDay().getMinute());
            int countedHour = 60 * 60 * (timeHistory.getHistoryDay().getHour() - 6);

            if (countedHour < 0) {
                countedHour *= -1;
            }

            Long newDuration = (long) countedSecond + countedMinute + countedHour + timeHistory.getDuration();
            timeHistory.update(newDuration, LocalDateTime.of(timeHistory.getHistoryDay().getYear(),
                    timeHistory.getHistoryDay().getMonth(),
                    timeHistory.getHistoryDay().getDayOfMonth(),
                    05,
                    59,
                    59));
        }
    }


    public ResponseTimeHistoryDto findByUid(User user) {

        TimeHistory entity = timeHistoryRepository.getDuration(user.getId(),
                getStandardTime(LocalDateTime.now()),
                LocalDateTime.now()
        )
                .orElseThrow(() -> new IllegalArgumentException("오늘 기록이 없습니다"));
        return new ResponseTimeHistoryDto(entity);
    }

    private LocalDateTime getStandardTime(LocalDateTime startTime) {

        if (startTime.getHour() < 6) {
            startTime = startTime.minusDays(1)
                    .plusHours(6 - startTime.getHour())
                    .minusMinutes(startTime.getMinute())
                    .minusSeconds(startTime.getSecond())
                    .minusNanos(startTime.getNano());
        } else {
            startTime = startTime.minusHours(startTime.getHour() - 6)
                    .minusMinutes(startTime.getMinute())
                    .minusSeconds(startTime.getSecond())
                    .minusNanos(startTime.getNano());
        }

        return startTime;
    }

    private Long countDuration(LocalDateTime historyDay) {
        int countedHour;
        if (historyDay.getHour() < 6) {
            countedHour = 60 * 60 * (historyDay.getHour() + 18);
        } else {
            countedHour = 60 * 60 * historyDay.getHour();
        }
        int countedMinute = 60 * historyDay.getMinute();
        int countedSecond = historyDay.getSecond();

        return (long) countedHour + countedMinute + countedSecond;
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> dayRanking() {

        return timeHistoryRepository.dayRanking(getStandardTime(LocalDateTime.now()),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    //일주일 랭킹은 월-일
    @Transactional(readOnly = true)
    public List<ResponseRankingDto> weekRanking() {

        System.out.println(findWklyStndTms());
        System.out.println(timeHistoryRepository.dayRanking(findWklyStndTms(),
                LocalDateTime.now()));

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
        LocalDateTime time = LocalDate.now().atTime(06, 00, 00);

        LocalTime baseTime = LocalTime.of(6, 0,0);
        if(LocalDateTime.now().getDayOfWeek().equals("MONDAY")
                && LocalTime.now().isAfter(baseTime)) { }
        else {
            time = time.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }

        return time;
    }


    private LocalDateTime findMnlyStndTms() {
        LocalDateTime time = LocalDate.now().atTime(06, 00, 00);

        LocalTime baseTime = LocalTime.of(6, 0,0);
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
