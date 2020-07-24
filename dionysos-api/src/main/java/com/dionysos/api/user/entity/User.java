package com.dionysos.api.user.entity;

import com.dionysos.api.user.dto.RequestSignUpDto;
import lombok.*;

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

    @NonNull
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
