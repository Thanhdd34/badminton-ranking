package com.thanh.badminton_ranking.tournament.sevice;

import com.thanh.badminton_ranking.common.enums.TournamentStatus;
import com.thanh.badminton_ranking.common.exception.InvalidTournamentDateException;
import com.thanh.badminton_ranking.tournament.dto.request.TournamentRequest;
import com.thanh.badminton_ranking.tournament.dto.response.TournamentResponse;
import com.thanh.badminton_ranking.tournament.entity.Tournament;
import com.thanh.badminton_ranking.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamenrServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Override
    public TournamentResponse createTournament(
            TournamentRequest request) {

        //Validate request
        if (request == null
                || request.getStartDate() == null
                || request.getEndDate() == null) {
            throw new IllegalArgumentException(
                    "Tournament request and dates are required"
            );
        }

        // Validate dates
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new InvalidTournamentDateException();
        }

        // Create Entity
        Tournament tournament = new Tournament();

        // Map Request to Entity
        tournament.setName(request.getName());
        tournament.setLocation(request.getLocation());
        tournament.setStartDate(request.getStartDate());
        tournament.setEndDate(request.getEndDate());
        tournament.setStatus(TournamentStatus.UPCOMING);

        //  Save Entity
        Tournament savedTournament =
                tournamentRepository.save(tournament);

        // Map Entity to Response
        return new TournamentResponse(
                savedTournament.getId(),
                savedTournament.getName(),
                savedTournament.getLocation(),
                savedTournament.getStartDate(),
                savedTournament.getEndDate(),
                savedTournament.getStatus(),
                savedTournament.getCreatedAt(),
                savedTournament.getUpdatedAt());
    };



}
