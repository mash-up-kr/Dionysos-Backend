package com.dionysos.api.ranking.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Getter
public class ResponseRankingDto {

    private Long userId;
    private String nickname;
    private Long duration;

    @Builder
    public ResponseRankingDto(TimeHistory entity) {
        this.userId = entity.getUser().getId();
        this.nickname = entity.getUser().getNickname();
        this.duration = entity.getDuration();
    }

    @Builder
    public ResponseRankingDto(ResponseRankingDtoImpl responseRankingWeekMonthDto) {
        this.userId = responseRankingWeekMonthDto.getId();
        this.duration = responseRankingWeekMonthDto.getDuration();
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
}
