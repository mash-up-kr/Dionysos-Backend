package com.dionysos.api.statistic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseStatisticDto {

    private LocalDateTime historyDay;
    private Long duration;

    @Builder
    private ResponseStatisticDto(LocalDateTime historyDay,
                                 Long duration
    ) {
        this.historyDay = historyDay;
        this.duration = duration;
    }

}
