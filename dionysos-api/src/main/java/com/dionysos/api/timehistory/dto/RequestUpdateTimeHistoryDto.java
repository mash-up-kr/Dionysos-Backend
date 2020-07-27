package com.dionysos.api.timehistory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestUpdateTimeHistoryDto {

    private LocalDateTime historyDay;
    private Long duration;

    @Builder
    private RequestUpdateTimeHistoryDto(LocalDateTime historyDay, Long duration) {
        this.historyDay = historyDay;
        this.duration = duration;
    }

}
