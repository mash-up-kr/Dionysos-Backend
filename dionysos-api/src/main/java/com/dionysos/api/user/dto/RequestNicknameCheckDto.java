package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestNicknameCheckDto {
    private String nickname;

    @Builder
    private RequestNicknameCheckDto(String nickname) {
        this.nickname = nickname;
    }
}
