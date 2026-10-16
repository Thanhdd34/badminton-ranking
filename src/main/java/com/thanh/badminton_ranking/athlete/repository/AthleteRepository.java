package com.thanh.badminton_ranking.athlete.repository;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}