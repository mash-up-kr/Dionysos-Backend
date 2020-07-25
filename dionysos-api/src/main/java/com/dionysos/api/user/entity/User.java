package com.dionysos.api.user.entity;

import lombok.*;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private ProviderType provider;

    @Builder
    private User(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
