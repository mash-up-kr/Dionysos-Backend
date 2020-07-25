package com.dionysos.api.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUIDDto {
    private String uid;

    @Builder
    public RequestUIDDto(String uid) {
        this.uid = uid;
    }
}
