package com.PremiershipPredictorGame.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    @Query(value = "SELECT * FROM LEAGUE l WHERE l.league_name = ?1", nativeQuery = true)
    Optional<League> findLeagueByLeagueName(String leagueName);
}
