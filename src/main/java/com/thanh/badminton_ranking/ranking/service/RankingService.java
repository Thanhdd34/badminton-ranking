package com.thanh.badminton_ranking.ranking.service;

import com.thanh.badminton_ranking.common.enums.Gender;
import com.thanh.badminton_ranking.ranking.dto.RankingResponse;

import java.util.List;

public interface RankingService {
    List<RankingResponse> getRankingByGender(Gender gender);
}
