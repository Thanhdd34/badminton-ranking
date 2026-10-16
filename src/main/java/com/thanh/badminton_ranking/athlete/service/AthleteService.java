package com.thanh.badminton_ranking.athlete.service;

import com.thanh.badminton_ranking.athlete.dto.response.AthleteResponse;

import java.util.List;

public interface AthleteService {
    List<AthleteResponse> getAllAthletes();

    AthleteResponse getAthleteById(Long id);
}
