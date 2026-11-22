
package com.thanh.badminton_ranking.result.service;

import com.thanh.badminton_ranking.athlete.entity.Athlete;

import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;

import com.thanh.badminton_ranking.common.exception.AthleteNotFoundException;
import com.thanh.badminton_ranking.common.exception.DuplicateResultException;
import com.thanh.badminton_ranking.common.exception.TournamentNotFoundException;
import com.thanh.badminton_ranking.tournament.entity.Tournament;

import com.thanh.badminton_ranking.tournament.repository.TournamentRepository;

import com.thanh.badminton_ranking.result.dto.ResultRequest;
import com.thanh.badminton_ranking.result.dto.ResultResponse;
import com.thanh.badminton_ranking.result.entity.Result;

import com.thanh.badminton_ranking.result.repository.ResultRepository;

import com.thanh.badminton_ranking.common.enums.EventType;
import com.thanh.badminton_ranking.common.enums.MedalType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private ResultServiceImpl resultService;

    // ======================================
    // TEST 1: CREATE RESULT SUCCESS
    // ======================================

    @Test
    void createResult_shouldSuccess_whenDataValid() {

        // Arrange
        ResultRequest request = createValidRequest();

        Athlete athlete = new Athlete();
        athlete.setId(1L);

        Tournament tournament = new Tournament();
        tournament.setId(10L);

        when(athleteRepository.findById(1L))
                .thenReturn(Optional.of(athlete));

        when(tournamentRepository.findById(10L))
                .thenReturn(Optional.of(tournament));

        when(resultRepository
                .existsByAthlete_IdAndTournament_IdAndEventType(
                        1L, 10L, EventType.MENS_DOUBLES))
                .thenReturn(false);

        when(resultRepository.save(any(Result.class)))
                .thenAnswer(invocation -> {
                    Result result = invocation.getArgument(0);
                    result.setId(100L);
                    return result;
                });

        // Act
        ResultResponse response =
                resultService.createResult(request);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(100L);
        assertThat(response.getAthleteId()).isEqualTo(1L);
        assertThat(response.getTournamentId()).isEqualTo(10L);
        assertThat(response.getEventType())
                .isEqualTo(EventType.MENS_DOUBLES);
        assertThat(response.getMedal())
                .isEqualTo(MedalType.GOLD);
        assertThat(response.getPoints()).isEqualTo(1000);

        ArgumentCaptor<Result> resultCaptor =
                ArgumentCaptor.forClass(Result.class);

        verify(resultRepository, times(1))
                .save(resultCaptor.capture());

        Result savedResult = resultCaptor.getValue();

        assertThat(savedResult.getAthlete())
                .isSameAs(athlete);
        assertThat(savedResult.getTournament())
                .isSameAs(tournament);
        assertThat(savedResult.getEventType())
                .isEqualTo(EventType.MENS_DOUBLES);
        assertThat(savedResult.getMedal())
                .isEqualTo(MedalType.GOLD);
        assertThat(savedResult.getPoints())
                .isEqualTo(1000);
    }

    // ======================================
    // TEST 2: ATHLETE NOT FOUND
    // ======================================

    @Test
    void createResult_shouldThrow_whenAthleteNotFound() {

        // Arrange
        ResultRequest request = createValidRequest();

        when(athleteRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() ->
                resultService.createResult(request))
                .isInstanceOf(
                        AthleteNotFoundException.class
                );

        verify(tournamentRepository, never())
                .findById(any());

        verify(resultRepository, never())
                .save(any(Result.class));
    }

    // ======================================
    // TEST 3: TOURNAMENT NOT FOUND
    // ======================================

    @Test
    void createResult_shouldThrow_whenTournamentNotFound() {

        // Arrange
        ResultRequest request = createValidRequest();

        Athlete athlete = new Athlete();
        athlete.setId(1L);

        when(athleteRepository.findById(1L))
                .thenReturn(Optional.of(athlete));

        when(tournamentRepository.findById(10L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() ->
                resultService.createResult(request))
                .isInstanceOf(
                        TournamentNotFoundException.class
                );

        verify(resultRepository, never())
                .save(any(Result.class));
    }

    // ======================================
    // TEST 4: DUPLICATE RESULT
    // ======================================

    @Test
    void createResult_shouldThrow_whenResultAlreadyExists() {

        // Arrange
        ResultRequest request = createValidRequest();

        Athlete athlete = new Athlete();
        athlete.setId(1L);

        Tournament tournament = new Tournament();
        tournament.setId(10L);

        when(athleteRepository.findById(1L))
                .thenReturn(Optional.of(athlete));

        when(tournamentRepository.findById(10L))
                .thenReturn(Optional.of(tournament));

        when(resultRepository
                .existsByAthlete_IdAndTournament_IdAndEventType(
                        1L, 10L, EventType.MENS_DOUBLES))
                .thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() ->
                resultService.createResult(request))
                .isInstanceOf(
                        DuplicateResultException.class
                );

        verify(resultRepository, never())
                .save(any(Result.class));
    }

    // ======================================
    // HELPER METHOD
    // ======================================

    private ResultRequest createValidRequest() {

        ResultRequest request = new ResultRequest();

        request.setAthleteId(1L);
        request.setTournamentId(10L);
        request.setEventType(EventType.MENS_DOUBLES);
        request.setMedal(MedalType.GOLD);
        request.setPoints(1000);

        return request;
    }
}
