package com.dionysos.api.diary.dto;

import com.dionysos.api.diary.entity.Diary;
import lombok.Getter;

@Getter
public class DiaryListResponseDto {
    private Long id;
    private String imageUrl;
    private String content;

    public DiaryListResponseDto(Diary entity) {
        this.id = entity.getId();
        this.imageUrl = entity.getImageUrl();
        this.content = entity.getContent();
    }
}
