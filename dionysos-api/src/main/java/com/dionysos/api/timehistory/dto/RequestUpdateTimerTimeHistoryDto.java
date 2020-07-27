package com.dionysos.api.timehistory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateTimerTimeHistoryDto {
    private Long duration;

    private RequestUpdateTimerTimeHistoryDto(Long duration) {
        this.duration = duration;
    }
}
