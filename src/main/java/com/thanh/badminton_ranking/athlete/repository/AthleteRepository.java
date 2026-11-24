package com.thanh.badminton_ranking.athlete.repository;

import com.thanh.badminton_ranking.athlete.entity.Athlete;
import com.thanh.badminton_ranking.common.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByGender(Gender gender);
}