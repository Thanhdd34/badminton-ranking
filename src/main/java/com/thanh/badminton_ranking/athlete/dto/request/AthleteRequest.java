package com.thanh.badminton_ranking.athlete.dto.request;

import com.thanh.badminton_ranking.common.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AthleteRequest (

        @NotBlank(message = "Full name must not be blank")
        @Size(max = 100, message = "Full name must not exceed 100 characters")
        String fullName,

        @NotNull(message = "Gender must not be null")
        Gender gender,

        @Size(max = 500, message = "Photo URL must not exceed 500 characters")
        String photoUrl
){
}
