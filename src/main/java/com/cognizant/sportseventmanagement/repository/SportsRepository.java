package com.cognizant.sportseventmanagement.repository;

import com.cognizant.sportseventmanagement.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportsRepository extends JpaRepository<Sports, Long> {

    Optional<Sports> findSportBySportsId(Long sportsId);
    Optional<Sports> findSportBySportsName(String sportsName);
}
