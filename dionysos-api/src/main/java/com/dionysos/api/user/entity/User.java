package com.dionysos.api.user.entity;

import com.dionysos.api.user.dto.RequestSignUpDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String nickname;

    @Builder
    public User(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }

    private User(RequestSignUpDto requestSignUpDto) {
        this.uid = requestSignUpDto.getUid();
        this.nickname = requestSignUpDto.getNickname();
    }

    public static User from(RequestSignUpDto reqeustSignUpDto) {
        return new User(reqeustSignUpDto);
    }

}
