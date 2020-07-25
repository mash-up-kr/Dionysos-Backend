package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.ProviderType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSignUpDto {
    private String uid;
    private String nickname;
    private ProviderType providerType;
    private String jwt;

    @Builder
    private ResponseSignUpDto(String uid, String nickname, String jwt, ProviderType providerType) {
        this.uid = uid;
        this.nickname = nickname;
        this.jwt = jwt;
        this.providerType = providerType;
    }
}
