package com.dionysos.api.diary.dto;

import com.dionysos.api.diary.entity.Diary;
import lombok.Getter;

@Getter
public class ResponseDiaryDto {
    private Long id;
    private String imageUrl;
    private String content;

    public ResponseDiaryDto(Diary entity) {
        this.id = entity.getId();
        this.imageUrl = entity.getImageUrl();
        this.content = entity.getContent();
    }
}
