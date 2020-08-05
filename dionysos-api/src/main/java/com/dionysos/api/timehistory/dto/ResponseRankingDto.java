package com.dionysos.api.timehistory.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
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


    @Builder
    public ResponseRankingDto(ResponseRankingWeekMonthDto responseRankingWeekMonthDto) {

        this.id = responseRankingWeekMonthDto.getId();
        this.duration = responseRankingWeekMonthDto.getDuration();
    }
}
