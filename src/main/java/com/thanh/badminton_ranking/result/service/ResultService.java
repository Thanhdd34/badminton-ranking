package com.thanh.badminton_ranking.result.service;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.result.dto.ResultRequest;
import com.thanh.badminton_ranking.result.dto.ResultResponse;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResultService {
    ResultResponse createResult(@Valid RegisterRequest resultRequest);


    ResultResponse createResult(ResultRequest request);

    List<ResultResponse> getAllResults();

    ResultResponse getResultById(Long id);

    //ResultResponse getResultsByAthleteId(Athlete athleteId);

    //ResultResponse getResultsByAthleteId(Athlete athleteId);

}
