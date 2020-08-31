package com.dionysos.api.timehistory.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseTimeHistoryDto {
    private Long id;
    private LocalDateTime historyDay;
    private Long duration;

    @Builder
    private ResponseTimeHistoryDto(Long id, LocalDateTime historyDay, Long duration) {
        this.id = id;
        this.historyDay = historyDay;
        this.duration = duration;
    }

}