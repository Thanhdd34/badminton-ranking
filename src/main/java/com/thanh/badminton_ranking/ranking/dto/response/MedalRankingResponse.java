package com.thanh.badminton_ranking.ranking.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedalRankingResponse {

    private int rank;

    private Long athleteId;

    private String athleteName;

    private long gold;

    private long silver;

    private long bronze;

    private long totalMedals;
}
