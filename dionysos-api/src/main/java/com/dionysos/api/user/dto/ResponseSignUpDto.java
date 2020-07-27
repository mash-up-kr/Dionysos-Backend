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
    private String jwt;
    private ProviderType provider;

    @Builder
    private ResponseSignUpDto(String uid,
                              String nickname,
                              String jwt,
                              ProviderType provider
    ) {
        this.uid = uid;
        this.nickname = nickname;
        this.jwt = jwt;
        this.provider = provider;
    }
}
