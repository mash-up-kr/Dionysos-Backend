package com.dionysos.api.diary.service;

import com.dionysos.api.diary.dto.DiaryListResponseDto;
import com.dionysos.api.diary.dto.RequestCreateDiaryDto;
import com.dionysos.api.diary.dto.RequestUpdateDiaryDto;
import com.dionysos.api.diary.dto.ResponseDiaryDto;
import com.dionysos.api.diary.entity.Diary;
import com.dionysos.api.diary.repository.DiaryRepository;
import com.dionysos.api.exception.BadRequestException;
import com.dionysos.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final S3Uploader s3Uploader;

    private static final String DIRECTORY_NAME = "diary";

    public void create(RequestCreateDiaryDto dto, User user) {
        String imageUrl = s3Uploader.upload(dto.getUploadFile(), DIRECTORY_NAME);
        Diary diary = dto.toEntity(imageUrl, user);
        diaryRepository.save(diary);
    }

    public void update(Long id, RequestUpdateDiaryDto requestUpdateDiaryDto, User user) {
        Diary searchedDiary = diaryRepository.findById(id).orElseThrow(() -> new BadRequestException("해당 id가 없습니다.id= "+id ));

        if (searchedDiary.getUser().getId() != user.getId()) {
            throw new BadRequestException("해당 게시글의 주인이 아닙니다.");
        }

        searchedDiary.update(requestUpdateDiaryDto.getImageUrl(), requestUpdateDiaryDto.getContent());
    }

    public ResponseDiaryDto findById(Long id) {
        Diary entity = diaryRepository.findById(id).orElseThrow(()-> new BadRequestException(("해당 id가 없습니다.id= "+id));

        return new ResponseDiaryDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(()-> new BadRequestException(("해당 id가 없습니다.id= "+id));
        diaryRepository.delete(diary);
    }

    public List<Diary> findAll(int user_id) {
        return diaryRepository.findAllByUserId().stream()
                .map(DiaryListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Diary> getDiaries(User user) {
        return diaryRepository.findAllByUserId(user.getId());
    }
}
