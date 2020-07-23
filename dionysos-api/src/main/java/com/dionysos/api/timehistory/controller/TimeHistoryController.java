package com.dionysos.api.timehistory.controller;

import com.dionysos.api.timehistory.dto.RequestTimeHistoryDto;
import com.dionysos.api.timehistory.service.TimeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-history")
public class TimeHistoryController {

    @Autowired
    private TimeHistoryService timeHistoryService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody RequestTimeHistoryDto requestTimeHistoryDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
