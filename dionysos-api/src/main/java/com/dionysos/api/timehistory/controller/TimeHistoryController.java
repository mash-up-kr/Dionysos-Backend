package com.dionysos.api.timehistory.controller;

import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.RequestUpdateTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.timehistory.service.TimeHistoryService;
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
    //start라고 이름 바꾸고
    public ResponseEntity create(@RequestBody RequestSaveTimeHistoryDto requestSaveTimeHistoryDto) {
        timeHistoryService.create(requestSaveTimeHistoryDto);
        userService.getFromUid();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Put -> 사용자가 일시정지 또는 종료 했을 경우
    //duration만 받아와서 그대로 저장
    //id 말고
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody RequestUpdateTimeHistoryDto requestUpdateTimeHistoryDto) {
        timeHistoryService.update(id, requestUpdateTimeHistoryDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Put -> 사용자가 다시 시작했을 경우
    //startTime을 갱신
    //isRunning = true
    //duration을 프론트에서 계속 더한다음에 최종값 주나? 아니면 내가 최종값 구해야하나?
    @PutMapping("/{id}")

    //Get
    //duration만 넘겨주기
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTimeHistoryDto> findById(@PathVariable Long id) {
        ResponseTimeHistoryDto dto = timeHistoryService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
