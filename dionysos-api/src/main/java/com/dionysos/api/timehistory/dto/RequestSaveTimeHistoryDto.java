package com.dionysos.api.timehistory.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestSaveTimeHistoryDto {

    private LocalDateTime historyDay;
    private Long duration;

    @Builder
    private RequestSaveTimeHistoryDto(LocalDateTime historyDay, Long duration) {

        this.historyDay = historyDay;
        this.duration = duration;
    }

}
