package com.dionysos.api.timehistory.controller;

import com.dionysos.api.common.response.DionysosAPIResponse;
import com.dionysos.api.timehistory.dto.RequestSaveTimeHistoryDto;
import com.dionysos.api.timehistory.dto.ResponseTimeHistoryDto;
import com.dionysos.api.timehistory.service.TimeHistoryService;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/time-history")
@RestController
public class TimeHistoryController {

    private final TimeHistoryService timeHistoryService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity createOrUpdate(@RequestBody RequestSaveTimeHistoryDto requestSaveTimeHistoryDto) throws Exception {
        User user = userService.getFromUid();
        timeHistoryService.createOrUpdate(requestSaveTimeHistoryDto, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public ResponseEntity<DionysosAPIResponse<ResponseTimeHistoryDto>> getTotalHrDay() {
        User user = userService.getFromUid();
        ResponseTimeHistoryDto dto = timeHistoryService.getTotalHrDay(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(DionysosAPIResponse.<ResponseTimeHistoryDto>builder().result(dto).build());
    }
}
