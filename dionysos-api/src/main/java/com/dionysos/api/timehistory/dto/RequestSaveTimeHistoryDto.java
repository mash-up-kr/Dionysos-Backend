package com.dionysos.api.timehistory.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestSaveTimeHistoryDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime historyDay;

    private Long duration;

    @Builder
    private RequestSaveTimeHistoryDto(LocalDateTime historyDay,
                                      Long duration
    ) {

        this.historyDay = historyDay;
        this.duration = duration;
    }

}
