package com.thanh.badminton_ranking.tournament.sevice;

import com.thanh.badminton_ranking.tournament.dto.request.TournamentRequest;
import com.thanh.badminton_ranking.tournament.dto.response.TournamentResponse;


public interface TournamentService {
    TournamentResponse createTournament(TournamentRequest request);
}
