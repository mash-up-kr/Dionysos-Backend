package com.dionysos.api.user.entity;

import com.dionysos.api.timehistory.entity.TimeHistory;
import lombok.*;

import javax.persistence.*;
import com.dionysos.api.diary.entity.Diary;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String uid;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<TimeHistory> timeHistoriesList;

    @Enumerated(EnumType.STRING)
    private ProviderType provider;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Diary> diaries;

    @Builder
    private User(String uid,
                 String nickname,
                 ProviderType provider
    ) {
        this.uid = uid;
        this.nickname = nickname;
        this.provider = provider;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
