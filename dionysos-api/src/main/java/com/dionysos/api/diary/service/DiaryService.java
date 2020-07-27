package com.dionysos.api.diary.service;

import com.dionysos.api.diary.dto.RequestCreateDiaryDto;
import com.dionysos.api.diary.dto.RequestUpdateDiaryDto;
import com.dionysos.api.diary.dto.ResponseDiaryDto;
import com.dionysos.api.diary.entity.Diary;
import com.dionysos.api.diary.repository.DiaryRepository;
import com.dionysos.api.exception.BadRequestException;
import com.dionysos.api.exception.UnAuthorizedException;
import com.dionysos.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final S3Uploader s3Uploader;

    private static final String DIRECTORY_NAME = "diary";

    public void create(RequestCreateDiaryDto dto,
                       User user
    ) {
        String imageUrl = s3Uploader.upload(dto.getUploadFile(),
                DIRECTORY_NAME
        );
        Diary diary = dto.toEntity(imageUrl,
                user
        );
        diaryRepository.save(diary);
    }

    public void update(Long id,
                       RequestUpdateDiaryDto requestUpdateDiaryDto,
                       Long user_id
    ) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("해당 id가 없습니다.id= " + id));

        if (diary.getUser()
                .getId() != user_id) {
            throw new UnAuthorizedException("해당 게시글의 주인이 아닙니다.");
        }

        diary.update(requestUpdateDiaryDto.getImageUrl(),
                requestUpdateDiaryDto.getContent()
        );
    }

    @Transactional
    public void delete(Long id,
                       Long user_id
    ) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(("해당 id가 없습니다.id= " + id)));

        if (diary.getUser()
                .getId() != user_id
        ) {
            throw new UnAuthorizedException("해당 게시글의 주인이 아닙니다.");
        }

        diaryRepository.delete(diary);
    }

    @Transactional(readOnly = true)
    public List<ResponseDiaryDto> findAll(Long user_id) {
        return diaryRepository.findAllByUserId(user_id)
                .stream()
                .map(diary -> ResponseDiaryDto.builder()
                        .id(diary.getId())
                        .content(diary.getContent())
                        .imageUrl(diary.getImageUrl())
                        .uid(diary.getUser().getUid())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResponseDiaryDto findById(Long id,
                                     Long user_id
    ) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(()-> new BadRequestException(("해당 id가 없습니다.id= " + id)));

        if (diary.getUser()
                .getId() != user_id
        ) {
            throw new UnAuthorizedException("해당 게시글의 주인이 아닙니다.");
        }

        return ResponseDiaryDto.builder()
                .uid(diary.getUser()
                        .getUid())
                .imageUrl(diary.getImageUrl())
                .id(diary.getId())
                .content(diary.getContent())
                .build();
    }
}
