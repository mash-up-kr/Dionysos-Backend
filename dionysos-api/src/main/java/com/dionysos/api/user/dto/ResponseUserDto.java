package com.dionysos.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUserDto {
    private String uid;
    private String nickname;

    @Builder
    public ResponseUserDto(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }
}
