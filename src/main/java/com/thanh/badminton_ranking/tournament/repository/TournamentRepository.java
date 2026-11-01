package com.thanh.badminton_ranking.tournament.repository;

import com.thanh.badminton_ranking.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
