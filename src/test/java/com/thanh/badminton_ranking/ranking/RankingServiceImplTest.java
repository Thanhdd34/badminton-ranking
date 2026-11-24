package com.thanh.badminton_ranking.ranking;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.athlete.repository.AthleteRepository;
import com.thanh.badminton_ranking.common.enums.Gender;
import com.thanh.badminton_ranking.ranking.dto.response.RankingResponse;
import com.thanh.badminton_ranking.ranking.service.RankingServiceImpl;
import com.thanh.badminton_ranking.result.entity.Result;
import com.thanh.badminton_ranking.result.repository.ResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class RankingServiceImplTest {

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private RankingServiceImpl rankingService;

    //Sắp xếp theo Average Points
    @Test
    void getRankingByGender_shouldSortByAveragePointsDescending() {

        // Arrange
        Athlete athlete1 = new Athlete();
        athlete1.setId(1L);
        athlete1.setFullName("Athlete A");
        athlete1.setGender(Gender.MALE);

        Athlete athlete2 = new Athlete();
        athlete2.setId(2L);
        athlete2.setFullName("Athlete B");
        athlete2.setGender(Gender.MALE);

        when(athleteRepository.findByGender(Gender.MALE))
                .thenReturn(List.of(athlete1, athlete2));

        Result result1 = new Result();
        result1.setPoints(1000);

        Result result2 = new Result();
        result2.setPoints(500);

        Result result3 = new Result();
        result3.setPoints(900);

        Result result4 = new Result();
        result4.setPoints(800);

        when(resultRepository.findByAthlete_Id(1L))
                .thenReturn(List.of(result1, result2));

        when(resultRepository.findByAthlete_Id(2L))
                .thenReturn(List.of(result3, result4));

        // Act
        List<RankingResponse> rankings =
                rankingService.getRankingByGender(Gender.MALE);

        // Assert
        assertThat(rankings).hasSize(2);

        assertThat(rankings.get(0).getAthleteId()).isEqualTo(2L);
        assertThat(rankings.get(0).getRank()).isEqualTo(1);
        assertThat(rankings.get(0).getAveragePoints())
                .isEqualTo(850.0);

        assertThat(rankings.get(1).getAthleteId()).isEqualTo(1L);
        assertThat(rankings.get(1).getRank()).isEqualTo(2);
        assertThat(rankings.get(1).getAveragePoints())
                .isEqualTo(750.0);

        verify(athleteRepository).findByGender(Gender.MALE);
        verify(resultRepository).findByAthlete_Id(1L);
        verify(resultRepository).findByAthlete_Id(2L);
    }
    //Athlete chưa có Result
    @Test
    void getRankingByGender_shouldReturnZero_whenAthleteHasNoResults() {

        // Arrange
        Athlete athlete = new Athlete();
        athlete.setId(1L);
        athlete.setFullName("Athlete A");
        athlete.setGender(Gender.MALE);

        when(athleteRepository.findByGender(Gender.MALE))
                .thenReturn(List.of(athlete));

        when(resultRepository.findByAthlete_Id(1L))
                .thenReturn(List.of());

        // Act
        List<RankingResponse> rankings =
                rankingService.getRankingByGender(Gender.MALE);

        // Assert
        assertThat(rankings).hasSize(1);
        assertThat(rankings.get(0).getResultCount()).isZero();
        assertThat(rankings.get(0).getTotalPoints()).isZero();
        assertThat(rankings.get(0).getAveragePoints()).isZero();
        assertThat(rankings.get(0).getRank()).isEqualTo(1);
    }

    //Không có Athlete

    @Test
    void getRankingByGender_shouldReturnEmpty_whenNoAthletes() {

        // Arrange
        when(athleteRepository.findByGender(Gender.FEMALE))
                .thenReturn(List.of());

        // Act
        List<RankingResponse> rankings =
                rankingService.getRankingByGender(Gender.FEMALE);

        // Assert
        assertThat(rankings).isEmpty();

        verifyNoInteractions(resultRepository);
    }

}
