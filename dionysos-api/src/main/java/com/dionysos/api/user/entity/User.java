package com.dionysos.api.user.entity;

import com.dionysos.api.user.dto.RequestSignUpDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String uid;

    private String nickname;

    private User(RequestSignUpDto requestSignUpDto) {
        this.uid = requestSignUpDto.getUid();
        this.nickname = requestSignUpDto.getNickname();
    }

    public static User from(RequestSignUpDto reqeustSignUpDto) {
        return new User(reqeustSignUpDto);
    }

}
