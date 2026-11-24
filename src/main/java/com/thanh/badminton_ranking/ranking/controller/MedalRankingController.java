package com.thanh.badminton_ranking.ranking.controller;


import com.thanh.badminton_ranking.ranking.dto.response.MedalRankingResponse;
import com.thanh.badminton_ranking.ranking.service.MedalRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rankings/medals")
@RequiredArgsConstructor
public class MedalRankingController {

    private final MedalRankingService medalRankingService;

    @GetMapping
    public ResponseEntity<List<MedalRankingResponse>> getMedalRanking() {

        List<MedalRankingResponse> rankings =
                medalRankingService.getMedalRanking();

        return ResponseEntity.ok(rankings);
    }
}
