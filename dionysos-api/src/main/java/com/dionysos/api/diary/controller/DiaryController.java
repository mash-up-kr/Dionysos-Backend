package com.dionysos.api.diary.controller;

import com.dionysos.api.diary.dto.RequestCreateDiaryDto;
import com.dionysos.api.diary.dto.RequestUpdateDiaryDto;
import com.dionysos.api.diary.dto.ResponseDiaryDto;
import com.dionysos.api.diary.service.DiaryService;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/diary")
@RestController
public class DiaryController {

    private final UserService userService;
    private final DiaryService diaryService;

    @GetMapping
    public ResponseEntity<List<ResponseDiaryDto>> diaryList() {
        User user = userService.getFromUid();
        List<ResponseDiaryDto> diaryList = diaryService.findAll(user.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(diaryList);
    }

    @PostMapping("/save")
    public ResponseEntity create(@RequestParam(value = "data") MultipartFile data, @RequestParam(value = "content") String content) {
        RequestCreateDiaryDto dto = RequestCreateDiaryDto.builder()
                .content(content)
                .uploadFile(data)
                .build();
        User user = userService.getFromUid();
        diaryService.create(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody RequestUpdateDiaryDto requestUpdateDiaryDto) {
        User user = userService.getFromUid();
        diaryService.update(id, requestUpdateDiaryDto, user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDiaryDto> findById(@PathVariable Long id) {
        User user = userService.getFromUid();
        ResponseDiaryDto dto = diaryService.findById(id, user.getId());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        User user = userService.getFromUid();
        diaryService.delete(id, user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
