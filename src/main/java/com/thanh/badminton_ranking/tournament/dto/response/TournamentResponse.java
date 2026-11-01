package com.thanh.badminton_ranking.tournament.dto.response;

import com.thanh.badminton_ranking.common.enums.TournamentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentResponse {
    private Long id;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private TournamentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
