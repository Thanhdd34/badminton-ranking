package com.thanh.badminton_ranking.athlete.service;

import com.thanh.badminton_ranking.athlete.dto.response.AthleteProfileResponse;
import com.thanh.badminton_ranking.athlete.dto.response.TournamentHistoryResponse;
import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.common.enums.MedalType;
import com.thanh.badminton_ranking.common.exception.AthleteNotFoundException;
import com.thanh.badminton_ranking.result.entity.Result;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import com.thanh.badminton_ranking.tournament.entity.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AthleteProfileServiceImpl implements AthleteProfileService{
    private final AthleteRepository athleteRepository;

    private final ResultRepository resultRepository;

    @Override
    @Transactional(readOnly = true)
    public AthleteProfileResponse getAthleteProfile(Long athleteId) {

        // 1. Find athlete
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new AthleteNotFoundException(
                        "Athlete not found with id: " + athleteId
                ));

        // 2. Get athlete results
        List<Result> results =
                resultRepository.findByAthlete_Id(athleteId);

        // 3. Calculate total points
        long totalPoints = results.stream()
                .mapToLong(result ->
                        result.getPoints() == null
                                ? 0L
                                : result.getPoints()
                )
                .sum();

        // 4. Calculate average points
        long resultCount = results.size();

        double averagePoints = resultCount == 0
                ? 0.0
                : (double) totalPoints / resultCount;

        // 5. Calculate medal counts
        long gold = results.stream()
                .filter(result ->
                        result.getMedal()== MedalType.GOLD
                )
                .count();

        long silver = results.stream()
                .filter(result ->
                        result.getMedal() == MedalType.SILVER
                )
                .count();

        long bronze = results.stream()
                .filter(result ->
                        result.getMedal() == MedalType.BRONZE
                )
                .count();

        // 6. Calculate total tournaments
        long totalTournaments = results.stream()
                .map(Result::getTournament)
                .filter(Objects::nonNull)
                .map(Tournament::getId)
                .filter(Objects::nonNull)
                .distinct()
                .count();

        // 7. Map tournament history
        List<TournamentHistoryResponse> tournamentHistory =
                results.stream()
                        .sorted(Comparator.comparing(
                                (Result result) ->
                                        result.getTournament() == null
                                                ? null
                                                : result.getTournament()
                                                .getStartDate(),
                                Comparator.nullsLast(
                                        Comparator.reverseOrder()
                                )
                        ))
                        .map(this::mapToTournamentHistory)
                        .toList();

        // 8. Return athlete profile
        return new AthleteProfileResponse(
                athlete.getId(),
                athlete.getFullName(),
                athlete.getGender(),
                averagePoints,
                totalPoints,
                totalTournaments,
                gold,
                silver,
                bronze,
                tournamentHistory
        );
    }
    // Map Result to TournamentHistoryResponse
    private TournamentHistoryResponse mapToTournamentHistory(
            Result result
    ) {

        Tournament tournament = result.getTournament();

        return new TournamentHistoryResponse(
                tournament == null ? null : tournament.getId(),
                tournament == null ? null : tournament.getName(),
                tournament == null ? null : tournament.getStartDate(),
                tournament == null ? null : tournament.getEndDate(),
                result.getEventType(),
                result.getMedal(),
                result.getPoints()
        );
    }
}
