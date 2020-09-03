package com.dionysos.api.statistic.service;

import com.dionysos.api.statistic.dto.ResponseStatisticDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticService {

    private List<ResponseStatisticDto>  statisticList;

    public List<ResponseStatisticDto> mockData() {
        ResponseStatisticDto mockDto = ResponseStatisticDto.builder()
                .historyDay(LocalDateTime.parse("2020-09-03T10:00:00"))
                .duration(300L)
                .build();
        statisticList.add(mockDto);

        return statisticList;
    }
}