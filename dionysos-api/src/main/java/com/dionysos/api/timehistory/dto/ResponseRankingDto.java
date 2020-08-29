package com.dionysos.api.timehistory.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseRankingDto {

    private Long id;
    private String nickname;

    private Long duration;

    @Builder
    public ResponseRankingDto(TimeHistory entity) {
        this.nickname = entity.getUser().getNickname();
        this.duration = entity.getDuration();
    }

    public ResponseRankingDto(ResponseRankingWeekMonthDto responseRankingWeekMonthDto) {

        this.id = responseRankingWeekMonthDto.getId();
        this.duration = responseRankingWeekMonthDto.getDuration();
    }
}
