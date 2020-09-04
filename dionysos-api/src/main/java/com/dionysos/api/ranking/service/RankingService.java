package com.dionysos.api.ranking.service;

import com.dionysos.api.timehistory.dto.ResponseRankingDto;
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
public class RankingService {

    private final RankingRepository rankingRepository;
    private final int stndHr = 6;

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> dayRanking() {

        return rankingRepository.dayRanking(getStandardTime(LocalDateTime.now()),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> weekRanking() {

        return rankingRepository.weeklyMonthlyRanking(findWklyStndTms(),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResponseRankingDto> monthRanking() {

        return rankingRepository.weeklyMonthlyRanking(findMnlyStndTms(),
                LocalDateTime.now())
                .stream()
                .map(ResponseRankingDto::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime findWklyStndTms() {
        LocalDateTime time = LocalDate.now().atTime(stndHr, 0, 0);

        LocalTime baseTime = LocalTime.of(stndHr, 0,0);
        if(LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.MONDAY)
                && LocalTime.now().isAfter(baseTime)) { }
        else {
            time = time.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }

        return time;
    }


    private LocalDateTime findMnlyStndTms() {
        LocalDateTime time = LocalDate.now().atTime(stndHr, 0, 0);

        LocalTime baseTime = LocalTime.of(stndHr, 0,0);
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
