package com.thanh.badminton_ranking.athlete.service;


import com.thanh.badminton_ranking.athlete.dto.request.AthleteRequest;
import com.thanh.badminton_ranking.athlete.dto.response.AthleteResponse;
import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;

    @Override
    public List<AthleteResponse> getAllAthletes() {
        return athleteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public AthleteResponse getAthleteById(Long id) {
        Athlete athlete = athleteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Athlete not found with id: " + id));

        return toResponse(athlete);
    }

    @Override
    public AthleteResponse createAthlete(AthleteRequest request) {
        Athlete athlete = new Athlete();

        athlete.setFullName(request.fullName());
        athlete.setGender(request.gender());
        athlete.setPhotoUrl(request.photoUrl());

        Athlete savedAthlete = athleteRepository.save(athlete);

        return toResponse(savedAthlete);
    }

    private AthleteResponse toResponse(Athlete athlete) {
        return new AthleteResponse(
                athlete.getId(),
                athlete.getFullName(),
                athlete.getGender(),
                athlete.getPhotoUrl(),
                athlete.getCreatedAt(),
                athlete.getUpdatedAt()
        );
    }

    @Override
    public void  deleteAthlete(Long id) {
        Athlete athlete = athleteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Athlete not found with id: " + id));

        athleteRepository.delete(athlete);
    }

}
