package com.thanh.badminton_ranking.user.repository;

import com.thanh.badminton_ranking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
