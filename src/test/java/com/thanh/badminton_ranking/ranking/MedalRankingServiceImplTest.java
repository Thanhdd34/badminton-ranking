
package com.thanh.badminton_ranking.ranking.service;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.ranking.dto.response.MedalRankingResponse;
import com.thanh.badminton_ranking.result.entity.Result;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import com.thanh.badminton_ranking.common.enums.MedalType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedalRankingServiceImplTest {

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private MedalRankingServiceImpl medalRankingService;

    // TEST 1: COUNT MEDALS

    @Test
    void getMedalRanking_shouldCountMedalsCorrectly() {

        // Arrange
        Athlete athlete = createAthlete(1L, "Athlete A");

        when(athleteRepository.findAll())
                .thenReturn(List.of(athlete));

        when(resultRepository.findByAthlete_Id(1L))
                .thenReturn(List.of(
                        createResult(MedalType.GOLD),
                        createResult(MedalType.GOLD),
                        createResult(MedalType.SILVER),
                        createResult(MedalType.BRONZE),
                        createResult(null)
                ));

        // Act
        List<MedalRankingResponse> rankings =
                medalRankingService.getMedalRanking();

        // Assert
        assertThat(rankings).hasSize(1);

        MedalRankingResponse response = rankings.get(0);

        assertThat(response.getGold()).isEqualTo(2);
        assertThat(response.getSilver()).isEqualTo(1);
        assertThat(response.getBronze()).isEqualTo(1);
        assertThat(response.getTotalMedals()).isEqualTo(4);
        assertThat(response.getRank()).isEqualTo(1);

        verify(resultRepository).findByAthlete_Id(1L);
    }

    // TEST 2: SORT BY GOLD, SILVER, BRONZE

    @Test
    void getMedalRanking_shouldSortByMedalPriority() {

        // Arrange
        Athlete athleteA = createAthlete(1L, "Athlete A");
        Athlete athleteB = createAthlete(2L, "Athlete B");
        Athlete athleteC = createAthlete(3L, "Athlete C");

        when(athleteRepository.findAll())
                .thenReturn(List.of(
                        athleteA, athleteB, athleteC
                ));

        // A: 2 gold, 1 silver, 1 bronze
        when(resultRepository.findByAthlete_Id(1L))
                .thenReturn(List.of(
                        createResult(MedalType.GOLD),
                        createResult(MedalType.GOLD),
                        createResult(MedalType.SILVER),
                        createResult(MedalType.BRONZE)
                ));

        // B: 2 gold, 2 silver
        when(resultRepository.findByAthlete_Id(2L))
                .thenReturn(List.of(
                        createResult(MedalType.GOLD),
                        createResult(MedalType.GOLD),
                        createResult(MedalType.SILVER),
                        createResult(MedalType.SILVER)
                ));

        // C: 3 gold
        when(resultRepository.findByAthlete_Id(3L))
                .thenReturn(List.of(
                        createResult(MedalType.GOLD),
                        createResult(MedalType.GOLD),
                        createResult(MedalType.GOLD)
                ));

        // Act
        List<MedalRankingResponse> rankings =
                medalRankingService.getMedalRanking();

        // Assert
        assertThat(rankings).hasSize(3);

        assertThat(rankings)
                .extracting(MedalRankingResponse::getAthleteId)
                .containsExactly(3L, 2L, 1L);

        assertThat(rankings)
                .extracting(MedalRankingResponse::getRank)
                .containsExactly(1, 2, 3);
    }

    // TEST 3: ATHLETE HAS NO MEDALS

    @Test
    void getMedalRanking_shouldReturnZero_whenNoMedals() {

        // Arrange
        Athlete athlete = createAthlete(1L, "Athlete A");

        when(athleteRepository.findAll())
                .thenReturn(List.of(athlete));

        when(resultRepository.findByAthlete_Id(1L))
                .thenReturn(List.of());

        // Act
        List<MedalRankingResponse> rankings =
                medalRankingService.getMedalRanking();

        // Assert
        assertThat(rankings).hasSize(1);

        MedalRankingResponse response = rankings.get(0);

        assertThat(response.getGold()).isZero();
        assertThat(response.getSilver()).isZero();
        assertThat(response.getBronze()).isZero();
        assertThat(response.getTotalMedals()).isZero();
    }

    // TEST 4: NO ATHLETES

    @Test
    void getMedalRanking_shouldReturnEmpty_whenNoAthletes() {

        // Arrange
        when(athleteRepository.findAll())
                .thenReturn(List.of());

        // Act
        List<MedalRankingResponse> rankings =
                medalRankingService.getMedalRanking();

        // Assert
        assertThat(rankings).isEmpty();

        verifyNoInteractions(resultRepository);
    }

    // HELPER METHODS

    private Athlete createAthlete(Long id, String name) {

        Athlete athlete = new Athlete();
        athlete.setId(id);
        athlete.setFullName(name);

        return athlete;
    }

    private Result createResult(MedalType medal) {

        Result result = new Result();
        result.setMedal(medal);

        return result;
    }
}
