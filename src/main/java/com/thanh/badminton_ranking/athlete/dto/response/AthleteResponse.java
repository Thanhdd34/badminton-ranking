package com.thanh.badminton_ranking.athlete.dto.response;

import com.thanh.badminton_ranking.common.enums.Gender;

import java.time.LocalDateTime;

public record  AthleteResponse(
        Long id,
        String fullName,
        Gender gender,
        String photoUrl,
        LocalDateTime createdAt
) {

}
