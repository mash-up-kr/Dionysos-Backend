package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSignInDto {
    private String nickname;

    @Builder
    private ResponseSignInDto(String nickname) {
        this.nickname = nickname;
    }
}
