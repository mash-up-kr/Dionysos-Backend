package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseNicknameDto {
    private String nickname;

    @Builder
    public ResponseNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}
