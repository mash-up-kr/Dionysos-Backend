package com.dionysos.api.diary.dto;

import com.dionysos.api.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCreateDiaryDto {
    private Long id;
    private String content;
    private String imageUrl;

    @Builder
    public RequestCreateDiaryDto(String content, String imageUrl) {
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Diary toEntity() {
        return Diary.builder()
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}
