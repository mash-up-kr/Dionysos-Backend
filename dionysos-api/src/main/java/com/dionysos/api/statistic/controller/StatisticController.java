package com.dionysos.api.statistic.controller;

import com.dionysos.api.statistic.dto.ResponseStatisticDto;
import com.dionysos.api.statistic.service.StatisticService;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/statistic")
@RestController
public class StatisticController {

    private final UserService userService;
    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<List<ResponseStatisticDto>> statisticList() {
        //User user = userService.getFromUid();
        //List<ResponseStatisticDto> statisticList = statisticService.findAll(user.getId());

        List<ResponseStatisticDto> statisticList = statisticService.mockData();
        return ResponseEntity.status(HttpStatus.OK)
                .body(statisticList);
    }
}