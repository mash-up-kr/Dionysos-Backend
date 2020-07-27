package com.dionysos.api.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateDiaryDto {

    private String imageUrl;
    private String content;

    @Builder
    private RequestUpdateDiaryDto(String imageUrl,
                                  String content
    ) {
        this.imageUrl = imageUrl;
        this.content = content;
    }
}
