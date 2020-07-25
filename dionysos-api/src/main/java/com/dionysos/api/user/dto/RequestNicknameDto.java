package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestNicknameDto {
    private String nickname;

    @Builder
    public RequestNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}
