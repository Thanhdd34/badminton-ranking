package com.thanh.badminton_ranking.athlete.service;

import com.thanh.badminton_ranking.athlete.dto.response.AthleteProfileResponse;

public interface AthleteProfileService {
    AthleteProfileResponse getAthleteProfile(Long athleteId);
}
