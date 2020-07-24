package com.dionysos.api.user.dto;

import com.dionysos.api.user.entity.User;
import lombok.*;

@Getter
@AllArgsConstructor
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

    public User toEntity() {
        return User.builder()
                .uid(uid)
                .nickname(nickname)
                .build();
    }
}
