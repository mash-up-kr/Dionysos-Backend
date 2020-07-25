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

    @Builder
    public RequestCreateDiaryDto(String content) {
        this.content = content;
    }

    public Diary toEntity(String imageUrl) {
        return Diary.builder()
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}
