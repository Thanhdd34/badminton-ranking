package com.thanh.badminton_ranking.result.repository;

import com.thanh.badminton_ranking.common.enums.EventType;
import com.thanh.badminton_ranking.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository <Result, Long>{

    boolean existsByAthlete_IdAndTournament_IdAndEventType(
            Long athleteId,
            Long tournamentId,
            EventType eventType
    );

    List<Result> findByAthlete_Id(Long athleteId);
}
