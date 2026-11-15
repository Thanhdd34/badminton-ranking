package com.thanh.badminton_ranking.result.dto;

import com.thanh.badminton_ranking.common.enums.EventType;
import com.thanh.badminton_ranking.common.enums.MedalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    private Long id;

    private Long athleteId;

    private Long tournamentId;

    private EventType eventType;

    private MedalType medal;

    private Integer points;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
