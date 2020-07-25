package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqeustChangeNicknameDto {
    private String nickname;

    @Builder
    private ReqeustChangeNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}
