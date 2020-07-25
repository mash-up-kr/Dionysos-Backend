package com.dionysos.api.diary.entity;

import com.dionysos.api.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Diary(String imageUrl, String content) {
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public void update(String imageUrl, String content) {
        this.imageUrl = imageUrl;
        this.content = content;
    }
}
