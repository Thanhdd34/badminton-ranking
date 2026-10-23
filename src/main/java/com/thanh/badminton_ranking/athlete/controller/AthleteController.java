package com.thanh.badminton_ranking.athlete.controller;

import com.thanh.badminton_ranking.athlete.dto.request.AthleteRequest;
import com.thanh.badminton_ranking.athlete.dto.response.AthleteResponse;
import com.thanh.badminton_ranking.athlete.service.AthleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @GetMapping
    public List<AthleteResponse> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    @GetMapping("/{id}")
    public AthleteResponse getAthleteById(@PathVariable Long id) {
        return athleteService.getAthleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AthleteResponse createAthlete(
            @Valid @RequestBody AthleteRequest request) {
        return athleteService.createAthlete(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
    }
}
