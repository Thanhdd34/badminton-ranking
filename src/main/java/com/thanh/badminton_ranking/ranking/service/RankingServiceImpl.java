package com.thanh.badminton_ranking.ranking.service;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.common.enums.Gender;
import com.thanh.badminton_ranking.ranking.dto.RankingResponse;
import com.thanh.badminton_ranking.result.entity.Result;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final AthleteRepository athleteRepository;
    private final ResultRepository resultRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RankingResponse> getRankingByGender(Gender gender) {

        // 1. Get athletes by gender
        List<Athlete> athletes =
                athleteRepository.findByGender(gender);

        // 2. Calculate ranking information
        List<RankingResponse> rankings = athletes.stream()
                .map(athlete -> {

                    List<Result> results =
                            resultRepository.findByAthlete_Id(
                                    athlete.getId()
                            );

                    long totalPoints = results.stream()
                            .mapToLong(Result::getPoints)
                            .sum();

                    long resultCount = results.size();

                    double averagePoints = resultCount == 0
                            ? 0.0
                            : (double) totalPoints / resultCount;

                    RankingResponse response = new RankingResponse();

                    response.setAthleteId(athlete.getId());
                    response.setAthleteName(athlete.getFullName());
                    response.setGender(athlete.getGender());
                    response.setTotalPoints(totalPoints);
                    response.setResultCount(resultCount);
                    response.setAveragePoints(averagePoints);

                    return response;
                })
                .sorted(
                        Comparator.comparingDouble(
                                        RankingResponse::getAveragePoints
                                ).reversed()
                                .thenComparing(
                                        RankingResponse::getAthleteId
                                )
                )
                .toList();

        // 3. Assign ranking positions
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        return rankings;
    }
}
