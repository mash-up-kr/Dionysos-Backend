package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class RequestSignUpDto {
    private String uid;
    private String nickname;

    @Builder
    public RequestSignUpDto(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }
}
