package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.ProviderType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSignInDto {
    private String uid;
    private ProviderType provider;

    @Builder
    private RequestSignInDto(String uid, ProviderType provider) {
        this.uid = uid;
        this.provider = provider;
    }
}
