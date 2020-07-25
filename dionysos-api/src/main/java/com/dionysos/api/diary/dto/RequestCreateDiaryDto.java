package com.dionysos.api.diary.dto;

import com.dionysos.api.diary.entity.Diary;
import com.dionysos.api.user.dto.ReqeustChangeNicknameDto;
import com.dionysos.api.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class RequestCreateDiaryDto {
    private String content;
    private MultipartFile uploadFile;

    @Builder
    private RequestCreateDiaryDto(String content, MultipartFile uploadFile) {
        this.content = content;
        this.uploadFile = uploadFile;
    }

    public Diary toEntity(String imageUrl, User user) {
        return Diary.builder()
                .imageUrl(imageUrl)
                .content(content)
                .user(user)
                .build();
    }
}
