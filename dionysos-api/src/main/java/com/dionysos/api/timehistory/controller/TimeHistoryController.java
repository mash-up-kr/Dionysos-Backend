package com.dionysos.api.timehistory.controller;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.RequestUpdateTimeHistoryDto;
import com.dionysos.api.timehistory.dto.RequestUpdateTimerTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
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
    public ResponseEntity create(@RequestBody RequestSaveTimeHistoryDto requestSaveTimeHistoryDto) throws Exception {
        User user = userService.getFromUid();
        timeHistoryService.create(requestSaveTimeHistoryDto, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Put -> 사용자가 일시정지/종료/재시작 했을 경우
    @PutMapping("")
    public ResponseEntity update(@RequestBody RequestUpdateTimeHistoryDto requestUpdateTimeHistoryDto) {
        User user = userService.getFromUid();
        timeHistoryService.update(requestUpdateTimeHistoryDto, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/timer")
    public ResponseEntity updateTimer(@RequestBody RequestUpdateTimerTimeHistoryDto requestUpdateTimerTimeHistoryDto) {
        User user = userService.getFromUid();
        timeHistoryService.updateTimer(requestUpdateTimerTimeHistoryDto, user);
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
