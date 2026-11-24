package com.thanh.badminton_ranking.ranking.controller;

import com.thanh.badminton_ranking.common.enums.Gender;
import com.thanh.badminton_ranking.ranking.dto.RankingResponse;
import com.thanh.badminton_ranking.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/men")
    public ResponseEntity<List<RankingResponse>> getMenRanking() {

        List<RankingResponse> rankings =
                rankingService.getRankingByGender(Gender.MALE);

        return ResponseEntity.ok(rankings);
    }


    @GetMapping("/women")
    public ResponseEntity<List<RankingResponse>> getWomenRanking() {

        List<RankingResponse> rankings =
                rankingService.getRankingByGender(Gender.FEMALE);

        return ResponseEntity.ok(rankings);
    }
}
