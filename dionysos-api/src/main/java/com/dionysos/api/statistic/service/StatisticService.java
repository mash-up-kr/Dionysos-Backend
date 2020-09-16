package com.dionysos.api.statistic.service;

import com.dionysos.api.statistic.dto.ResponseStatisticDto;
import com.dionysos.api.statistic.repository.StatisticRepository;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.repository.TimeHistoryRepository;
import com.dionysos.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@RequiredArgsConstructor
@Service
public class StatisticService {

    private static final int STANDARD_HOUR = 5;
    private static final int SECONDS = 3600;

    private final StatisticRepository statisticRepository;

    public List<ResponseStatisticDto> getStatistic(User user, int year, int month) {
        Long userId = user.getId();
        LocalDateTime beginTimeStamp = getBeginTimeStamp(year, month);
        LocalDateTime endTimeStamp = getEndTimeStamp(year, month);
        List<TimeHistory> timeHistoryList = statisticRepository.getHistoryDayAndDuration(userId, beginTimeStamp, endTimeStamp);

        return getDayHistoryFromList(timeHistoryList);
    }

    private LocalDateTime getBeginTimeStamp(int year, int month) {
        int firstDayOfMonth = 1;
        LocalDate date = YearMonth.of(year, month).atDay(firstDayOfMonth);
        LocalTime time = LocalTime.of(STANDARD_HOUR, 0);
        return LocalDateTime.of(date, time);
    }

    private LocalDateTime getEndTimeStamp(int year, int month) {
        LocalDate date = YearMonth.of(year, month).atEndOfMonth().plusDays(1);
        LocalTime time = LocalTime.of(STANDARD_HOUR, 0).minusSeconds(1);
        return LocalDateTime.of(date, time);
    }

    private List<ResponseStatisticDto> getDayHistoryFromList(List<TimeHistory> timeHistoryList) {
        List<ResponseStatisticDto> responseStatisticDtoList = new ArrayList<>();
        for(TimeHistory timeHistory : timeHistoryList) {
            ResponseStatisticDto dto = getResponseStaticDto(timeHistory);
            responseStatisticDtoList.add(dto);
        }
        return responseStatisticDtoList;
    }

    private ResponseStatisticDto getResponseStaticDto(TimeHistory timeHistory) {
        int date = getDate(timeHistory.getHistoryDay());
        int level = getLevel(timeHistory.getDuration());
        return ResponseStatisticDto.builder()
                .date(date)
                .level(level)
                .build();
    }

    private int getDate(LocalDateTime historyDay) {
        int day = historyDay.getDayOfMonth();
        int hour = historyDay.getHour();
        if (hour < STANDARD_HOUR) {
           return (day-1);
        }
        return day;
    }

    private int getLevel(Long duration) {
        int hour = (int) Math.ceil(duration / SECONDS);
        switch(hour) {
            case 1: case 2: case 3: case 4:
                return 1;
            case 5: case 6: case 7: case 8:
                return 2;
            case 9: case 10: case 11: case 12:
                return 3;
            case 13: case 14: case 15: case 16:
                return 4;
            case 17: case 18: case 19: case 20:
                return 5;
            case 21: case 22: case 23: case 24:
                return 6;
        }
        return 1;
    }

    /*
    public List<ResponseStatisticDto> mockData() {
        ResponseStatisticDto mockDto = ResponseStatisticDto.builder()
                .historyDay(LocalDateTime.parse("2020-09-03T10:00:00"))
                .duration(300L)
                .build();
        statisticList.add(mockDto);

        return statisticList;
    }
     */
}