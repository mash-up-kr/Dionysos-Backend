package com.dionysos.api.user.entity;

import com.dionysos.api.timehistory.entity.TimeHistory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<TimeHistory> timeHistoriesList;

    @Builder
    public User(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
