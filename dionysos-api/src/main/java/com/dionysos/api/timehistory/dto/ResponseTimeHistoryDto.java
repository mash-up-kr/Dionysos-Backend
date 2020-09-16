package com.dionysos.api.timehistory.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseTimeHistoryDto {
    private LocalDateTime historyDay;
    private Long duration;

    @Builder
    private ResponseTimeHistoryDto(LocalDateTime historyDay, Long duration) {
        this.historyDay = historyDay;
        this.duration = duration;
    }

}