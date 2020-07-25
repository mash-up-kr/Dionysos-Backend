package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUserDto {
    private String uid;
    private String nickname;
    private ProviderType providerType;

    @Builder
    private ResponseUserDto(String uid, String nickname, ProviderType providerType) {
        this.uid = uid;
        this.nickname = nickname;
        this.providerType = providerType;
    }
}
