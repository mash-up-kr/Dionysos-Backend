package com.dionysos.api.timehistory.dto;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestSaveTimeHistoryDto {

    private LocalDateTime historyDate;

    @Builder
    public RequestSaveTimeHistoryDto(LocalDateTime historyDate) {
        this.historyDate = historyDate;
    }

    public TimeHistory toEntity() {
        return TimeHistory.builder()
                .historyDay(historyDate)    //우리 서버 시간 기준으로
                .build();
    }


}
