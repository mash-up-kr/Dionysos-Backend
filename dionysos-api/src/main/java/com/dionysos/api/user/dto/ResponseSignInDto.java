package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSignInDto {
    private String uid;
    private String nickname;
    private String jws;

    @Builder
    private ResponseSignInDto(String uid, String nickname, String jws) {
        this.uid = uid;
        this.nickname = nickname;
        this.jws = jws;
    }
}
