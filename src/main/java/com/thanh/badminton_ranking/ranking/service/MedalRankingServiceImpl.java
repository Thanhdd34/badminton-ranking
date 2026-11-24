package com.thanh.badminton_ranking.ranking.service;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.common.enums.MedalType;
import com.thanh.badminton_ranking.ranking.dto.response.MedalRankingResponse;
import com.thanh.badminton_ranking.result.entity.Result;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedalRankingServiceImpl implements MedalRankingService{

    private final AthleteRepository athleteRepository;
    private final ResultRepository resultRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MedalRankingResponse> getMedalRanking() {

        // 1. Get all athletes
        List<Athlete> athletes = athleteRepository.findAll();

        // 2. Calculate medals for each athlete
        List<MedalRankingResponse> rankings = athletes.stream()
                .map(athlete -> {

                    List<Result> results =
                            resultRepository.findByAthlete_Id(
                                    athlete.getId()
                            );

                    long gold = results.stream()
                            .filter(result ->
                                    result.getMedal() == MedalType.GOLD)
                            .count();

                    long silver = results.stream()
                            .filter(result ->
                                    result.getMedal() == MedalType.SILVER)
                            .count();

                    long bronze = results.stream()
                            .filter(result ->
                                    result.getMedal() == MedalType.BRONZE)
                            .count();

                    long totalMedals = gold + silver + bronze;

                    MedalRankingResponse response =
                            new MedalRankingResponse();

                    response.setAthleteId(athlete.getId());
                    response.setAthleteName(athlete.getFullName());
                    response.setGold(gold);
                    response.setSilver(silver);
                    response.setBronze(bronze);
                    response.setTotalMedals(totalMedals);

                    return response;
                })
                .sorted(
                        Comparator.comparingLong(
                                        MedalRankingResponse::getGold
                                ).reversed()
                                .thenComparing(
                                        Comparator.comparingLong(
                                                MedalRankingResponse::getSilver
                                        ).reversed()
                                )
                                .thenComparing(
                                        Comparator.comparingLong(
                                                MedalRankingResponse::getBronze
                                        ).reversed()
                                )
                                .thenComparing(
                                        MedalRankingResponse::getAthleteId
                                )
                )
                .toList();

        // 3. Assign ranks
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        return rankings;
    }
}
