package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.ProviderType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSignUpDto {
    private String uid;
    private String nickname;
    private ProviderType provider;

    @Builder
    private RequestSignUpDto(String uid, String nickname, ProviderType provider) {
        this.uid = uid;
        this.nickname = nickname;
        this.provider = provider;
    }
}
