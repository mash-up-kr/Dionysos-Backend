package com.dionysos.api.timehistory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateTimeHistoryDto {

    private Long duration;

    @Builder
    public RequestUpdateTimeHistoryDto(Long duration) {
        this.duration = duration;
    }

}
