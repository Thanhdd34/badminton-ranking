package com.thanh.badminton_ranking.tournament.controller;

import com.thanh.badminton_ranking.tournament.dto.request.TournamentRequest;
import com.thanh.badminton_ranking.tournament.dto.response.TournamentResponse;
import com.thanh.badminton_ranking.tournament.entity.Tournament;
import com.thanh.badminton_ranking.tournament.sevice.TournamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<TournamentResponse> createTournament(@Valid @RequestBody TournamentRequest request){
       TournamentResponse tournamentResponse = tournamentService.createTournament(request);
       return ResponseEntity.status(HttpStatus.CREATED)
                            .body(tournamentResponse);
    }
}
