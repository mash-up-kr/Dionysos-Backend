package com.dionysos.api.diary.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String content;

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
