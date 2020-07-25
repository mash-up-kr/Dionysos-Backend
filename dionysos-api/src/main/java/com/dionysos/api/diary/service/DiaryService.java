package com.dionysos.api.diary.service;

import com.dionysos.api.diary.dto.DiaryListResponseDto;
import com.dionysos.api.diary.dto.RequestCreateDiaryDto;
import com.dionysos.api.diary.dto.RequestUpdateDiaryDto;
import com.dionysos.api.diary.dto.ResponseDiaryDto;
import com.dionysos.api.diary.entity.Diary;
import com.dionysos.api.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public Long create(RequestCreateDiaryDto requestCreateDiaryDto) {
        return diaryRepository.save(requestCreateDiaryDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, RequestUpdateDiaryDto requestUpdateDiaryDto) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다.id= "+id ));

        diary.update(requestUpdateDiaryDto.getContent(), requestUpdateDiaryDto.getImageUrl());
        return id;
    }

    public ResponseDiaryDto findById(Long id) {
        Diary entity = diaryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 id가 없습니다.id= "+id));

        return new ResponseDiaryDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 id가 없습니다.id= "+id));
        diaryRepository.delete(diary);
    }

    public Object findAll() {
        return diaryRepository.findAll().stream()
                .map(DiaryListResponseDto::new)
                .collect(Collectors.toList());
    }
}
