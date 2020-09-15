package com.dionysos.api.ranking.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    public ResponseRankingDto(ResponseRankingDtoImpl responseRankingWeekMonthDto) {
        this.id = responseRankingWeekMonthDto.getId();
        //this.nickname = getUser(responseRankingWeekMonthDto.getId()).getNickname();
        this.duration = responseRankingWeekMonthDto.getDuration();
    }
}
