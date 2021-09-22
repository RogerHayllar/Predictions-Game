package com.PremiershipPredictorGame.fixture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long > {
//    @Query(value = "SELECT * FROM fixture WHERE week = ?1", nativeQuery = true)
    List<Fixture> findFixtureByWeek(Integer week);
    @Query(value = "SELECT * FROM fixture WHERE away_team = ?1 or home_team = ?1", nativeQuery = true)
    List<Fixture> findFixturesByTeam(String team);


   Optional<Fixture> findByHomeTeamAndAwayTeamAndWeek(String homeTeam, String awayTeam, Integer week);
    @Query(value = "SELECT ID FROM fixture WHERE week = ?1", nativeQuery = true)
    List<Long> findIdsByWeek(Integer week);
    @Query(value = "SELECT * FROM fixture WHERE week = ?1", nativeQuery = true)
    List<Fixture> findFixturesByWeek(Integer week);
}
