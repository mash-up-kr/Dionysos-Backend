package com.dionysos.api.timehistory.controller;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.service.TimeHistoryService;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time-history")
public class TimeHistoryController {

    private final TimeHistoryService timeHistoryService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity createOrUpdate(@RequestBody RequestSaveTimeHistoryDto requestSaveTimeHistoryDto) throws Exception {
        User user = userService.getFromUid();
        timeHistoryService.createOrUpdate(requestSaveTimeHistoryDto, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Get
    //duration만 넘겨주기
    @GetMapping("")
    public ResponseEntity<ResponseTimeHistoryDto> findByUid() {
        User user = userService.getFromUid();
        ResponseTimeHistoryDto dto = timeHistoryService.findByUid(user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
