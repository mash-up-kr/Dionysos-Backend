package com.dionysos.api.ranking.service;

import com.dionysos.api.common.util.DateUtil;
import com.dionysos.api.ranking.repository.RankingRepository;
import com.dionysos.api.ranking.dto.ResponseRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import static com.dionysos.api.common.util.DateUtil.getStandardTime;

@RequiredArgsConstructor
@Service
public class RankingService {

    private final RankingRepository rankingRepository;

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> getDailyRanking() {
        LocalDateTime standardTime = getStandardTime(LocalDateTime.now());

        return rankingRepository.dayRanking(standardTime,
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> getWeeklyRanking() {

        return rankingRepository.weeklyMonthlyRanking(findWklyStandardTms(),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> getMonthlyRanking() {

        return rankingRepository.weeklyMonthlyRanking(findMonthlyStandardTms(),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime findWklyStandardTms() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime baseTime = LocalTime.of(DateUtil.standardHour, DateUtil.standardMinute);

        int day = now.getDayOfWeek().getValue();
        if(isBeforeBaseTimeStamp(day, baseTime)) {
            return now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).toLocalDate().atTime(baseTime);
        } else {
            return now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atTime(baseTime);
        }
    }

    private LocalDateTime findMonthlyStandardTms() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime baseTime = LocalTime.of(DateUtil.standardHour, DateUtil.standardMinute);

        if(isBeforeBaseTimeStamp(now.getDayOfMonth(), baseTime)) {
            return now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atTime(baseTime);
        } else {
            return now.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atTime(baseTime);
        }
    }

    private boolean isBeforeBaseTimeStamp(int day, LocalTime baseTime) {
        return (day==1) && (LocalTime.now().isBefore(baseTime));
    }
}
