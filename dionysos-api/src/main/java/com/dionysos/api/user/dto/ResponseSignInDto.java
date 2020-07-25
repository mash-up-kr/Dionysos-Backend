package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSignInDto {
    private String uid;
    private String nickname;
    private String jwt;

    @Builder
    private ResponseSignInDto(String uid, String nickname, String jwt) {
        this.uid = uid;
        this.nickname = nickname;
        this.jwt = jwt;
    }
}
