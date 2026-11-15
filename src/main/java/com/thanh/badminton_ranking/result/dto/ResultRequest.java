package com.thanh.badminton_ranking.result.dto;

import com.thanh.badminton_ranking.common.enums.EventType;
import com.thanh.badminton_ranking.common.enums.MedalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultRequest {

    @NotNull(message = "Athlete ID is required")
    @Positive(message = "Athlete ID must be positive")
    private Long athleteId;

    @NotNull(message = "Tournament ID is required")
    @Positive(message = "Tournament ID must be positive")
    private Long tournamentId;

    @NotNull(message = "Event type is required")
    private EventType eventType;

    private MedalType medal;

    @NotNull(message = "Points are required")
    @PositiveOrZero(message = "Points must be >= 0")
    private Integer points;
}
