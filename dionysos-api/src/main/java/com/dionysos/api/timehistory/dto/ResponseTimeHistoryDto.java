package com.dionysos.api.timehistory.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseTimeHistoryDto {
    private Long id;
    private LocalDateTime historyDay;
    private Long duration;

    public ResponseTimeHistoryDto(TimeHistory entity) {
        this.id = entity.getId();
        this.historyDay = entity.getHistoryDay();
        this.duration = entity.getDuration();
    }

}