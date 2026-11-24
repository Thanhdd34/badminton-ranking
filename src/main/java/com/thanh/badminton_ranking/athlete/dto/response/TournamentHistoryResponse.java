package com.thanh.badminton_ranking.athlete.dto.response;

import com.thanh.badminton_ranking.common.enums.EventType;
import com.thanh.badminton_ranking.common.enums.MedalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentHistoryResponse {
    private Long tournamentId;

    private String tournamentName;

    private LocalDate startDate;

    private LocalDate endDate;

    private EventType eventType;

    private MedalType medal;

    private Integer points;
}
