package com.dionysos.api.diary.dto;

import com.dionysos.api.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDiaryDto {
    private Long id;
    private String imageUrl;
    private String content;
    private String uid;

    @Builder
    private ResponseDiaryDto(Long id, String imageUrl, String content, String uid) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.content = content;
        this.uid = uid;
    }
}
