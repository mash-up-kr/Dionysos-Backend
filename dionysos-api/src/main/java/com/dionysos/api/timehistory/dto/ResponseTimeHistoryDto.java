package com.dionysos.api.timehistory.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import lombok.Getter;

@Getter
public class ResponseTimeHistoryDto {
    private Long id;
    private Long duration;

    public ResponseTimeHistoryDto(TimeHistory entity) {
        this.id = entity.getId();
        this.duration = entity.getDuration();
    }

}