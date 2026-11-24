package com.thanh.badminton_ranking.athlete.dto.response;

import com.thanh.badminton_ranking.common.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AthleteProfileResponse {
    private Long athleteId;

    private String name;

    private Gender gender;

    private double averagePoints;

    private long totalPoints;

    private long totalTournaments;

    private long gold;

    private long silver;

    private long bronze;

    private List<TournamentHistoryResponse> tournamentHistory;
}
