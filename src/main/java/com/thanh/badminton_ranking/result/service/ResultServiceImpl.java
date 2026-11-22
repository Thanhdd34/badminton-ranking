package com.thanh.badminton_ranking.result.service;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.common.exception.AthleteNotFoundException;
import com.thanh.badminton_ranking.common.exception.DuplicateResultException;
import com.thanh.badminton_ranking.common.exception.TournamentNotFoundException;
import com.thanh.badminton_ranking.result.dto.ResultRequest;
import com.thanh.badminton_ranking.result.dto.ResultResponse;
import com.thanh.badminton_ranking.result.entity.Result;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import com.thanh.badminton_ranking.tournament.entity.Tournament;
import com.thanh.badminton_ranking.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final AthleteRepository athleteRepository;
    private final TournamentRepository tournamentRepository;

    @Override
    public ResultResponse createResult(RegisterRequest resultRequest) {
        return null;
    }

    @Transactional
    @Override
    public ResultResponse createResult(ResultRequest request){
        // find Athlete
        Athlete athlete = athleteRepository.findById(request.getAthleteId())
                .orElseThrow(()-> new AthleteNotFoundException(request.getAthleteId()));

        //find Tournament
        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new TournamentNotFoundException(
                        request.getTournamentId()));

        //check duplicate result
        boolean exists = resultRepository
                .existsByAthlete_IdAndTournament_IdAndEventType(
                        athlete.getId(),
                        tournament.getId(),
                        request.getEventType()
                );
        if(exists){
            throw new DuplicateResultException();
        }

        //map request to Entity
        Result result = new Result();

        result.setAthlete(athlete);
        result.setTournament(tournament);
        result.setEventType(request.getEventType());
        result.setMedal(request.getMedal());
        result.setPoints(request.getPoints());

        //  Save Result
        Result savedResult = resultRepository.save(result);

        //  Map Entity to Response
        return toResponse(savedResult);
    }
    private ResultResponse toResponse(Result result) {
        return new ResultResponse(
                result.getId(),
                result.getAthlete().getId(),
                result.getTournament().getId(),
                result.getEventType(),
                result.getMedal(),
                result.getPoints(),
                result.getCreatedAt(),
                result.getUpdatedAt()
        );
}
}
