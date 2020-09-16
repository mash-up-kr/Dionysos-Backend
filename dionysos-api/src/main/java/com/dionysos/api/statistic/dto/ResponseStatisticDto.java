package com.dionysos.api.statistic.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseStatisticDto {

    private int date;
    private int level;

    @Builder
    private ResponseStatisticDto(int date, int level) {
        this.date = date;
        this.level = level;
    }
}
