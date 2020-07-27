package com.dionysos.api.timehistory.controller;

import com.dionysos.api.timehistory.dto.RequestTimeHistoryDto;
import com.dionysos.api.timehistory.service.TimeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time-history")
public class TimeHistoryController {

    private final TimeHistoryService timeHistoryService;

    @PostMapping
    public ResponseEntity create(@RequestBody RequestTimeHistoryDto requestTimeHistoryDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
