package com.dionysos.api.ranking.controller;

import com.dionysos.api.ranking.service.RankingService;
import com.dionysos.api.timehistory.dto.ResponseRankingDto;
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
@RequestMapping("/ranking")
@RestController
public class RankingController {

    private final UserService userService;
    private final RankingService rankingService;

    @GetMapping("/now")
    public ResponseEntity getCurrentRanking() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking/day")
    public ResponseEntity<List<ResponseRankingDto>> dayRanking() {
        User user = userService.getFromUid();
        List<ResponseRankingDto> dtos = rankingService.dayRanking();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/ranking/weekly")
    public ResponseEntity<List<ResponseRankingDto>> weekRanking() {
        List<ResponseRankingDto> dtos = rankingService.weekRanking();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/ranking/monthly")
    public ResponseEntity<List<ResponseRankingDto>> monthRanking() {
        List<ResponseRankingDto> dtos = rankingService.monthRanking();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
