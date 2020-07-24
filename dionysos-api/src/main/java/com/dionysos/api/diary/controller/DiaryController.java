package com.dionysos.api.diary.controller;

import com.dionysos.api.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("")
    public ResponseEntity create() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
