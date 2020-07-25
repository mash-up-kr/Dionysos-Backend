package com.dionysos.api.diary.controller;

import com.dionysos.api.diary.dto.RequestCreateDiaryDto;
import com.dionysos.api.diary.dto.RequestUpdateDiaryDto;
import com.dionysos.api.diary.dto.ResponseDiaryDto;
import com.dionysos.api.diary.entity.Diary;
import com.dionysos.api.diary.service.DiaryService;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final UserService userService;
    private final DiaryService diaryService;

//    @GetMapping("")
//    public ResponseEntity<List<Diary>> diaryList() {
//        User user = userService.getFromUid();
//        List<Diary> diaryList = (List<Diary>) diaryService.findAll();
//        return new ResponseEntity<List<Diary>>(diaryList, new HttpHeaders(), HttpStatus.OK);
//    }

    @PostMapping("/save")
    public ResponseEntity create(@RequestParam("data") MultipartFile multipartFile,
                                 @RequestParam("content") String content) throws IOException {
        diaryService.create(multipartFile, content);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody RequestUpdateDiaryDto requestUpdateDiaryDto) {
        diaryService.update(id, requestUpdateDiaryDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDiaryDto> findById(@PathVariable Long id) {
        ResponseDiaryDto dto = diaryService.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        diaryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
