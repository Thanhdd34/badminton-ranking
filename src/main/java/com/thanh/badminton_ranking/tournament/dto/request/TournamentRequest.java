package com.thanh.badminton_ranking.tournament.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TournamentRequest {

    @NotBlank(message = "Tournament name is required!")
    @Size(max = 150, message = "Tournament name must not exceed 150 characters")
    private String name;

    @Size(max = 150)
    private String location;

    @NotNull(message = "Start day is require!")
    private LocalDate startDate;

    @NotNull(message = "End day is require!")
    private LocalDate endDate;
}
