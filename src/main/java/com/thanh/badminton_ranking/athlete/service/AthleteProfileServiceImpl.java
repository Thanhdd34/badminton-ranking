package com.thanh.badminton_ranking.athlete.service;

import com.thanh.badminton_ranking.athlete.dto.response.AthleteProfileResponse;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AthleteProfileServiceImpl implements AthleteProfileService{
    private final AthleteRepository athleteRepository;

    private final ResultRepository resultRepository;

    @Override
    @Transactional(readOnly = true)
    public AthleteProfileResponse getAthleteProfile(Long athleteId) {

        // TODO 1: Find Athlete
        // TODO 2: Find Results by Athlete ID
        // TODO 3: Calculate totalPoints
        // TODO 4: Calculate averagePoints
        // TODO 5: Count medals
        // TODO 6: Count distinct tournaments
        // TODO 7: Map TournamentHistoryResponse
        // TODO 8: Return AthleteProfileResponse

        return null;
    }
}
