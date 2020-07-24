package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor
public class RequestUserDto {
    private String uid;
    private String nickname;

    @Builder
    public RequestUserDto(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }
}
