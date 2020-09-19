package com.dionysos.api.ranking.controller;

import com.dionysos.api.ranking.service.RankingService;
import com.dionysos.api.ranking.dto.ResponseRankingDto;
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

    private final RankingService rankingService;

    @GetMapping("/day")
    public ResponseEntity<List<ResponseRankingDto>> getDailyRanking() {
        List<ResponseRankingDto> dtos = rankingService.getDailyRanking();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<List<ResponseRankingDto>> getWeeklyRanking() {
        List<ResponseRankingDto> dtos = rankingService.getWeeklyRanking();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/month")
    public ResponseEntity<List<ResponseRankingDto>> getMonthlyRanking() {
        List<ResponseRankingDto> dtos = rankingService.getMonthlyRanking();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
