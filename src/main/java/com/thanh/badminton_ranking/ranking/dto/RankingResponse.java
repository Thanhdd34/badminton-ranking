package com.thanh.badminton_ranking.ranking.dto;

import com.thanh.badminton_ranking.common.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankingResponse {
    private int rank;
    private Long athleteId;
    private String athleteName;
    private Gender gender;
    private long totalPoints;
    private long resultCount;
    private double averagePoints;
}
