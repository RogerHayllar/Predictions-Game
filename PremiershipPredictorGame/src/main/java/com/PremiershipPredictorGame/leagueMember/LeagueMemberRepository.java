package com.PremiershipPredictorGame.leagueMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueMemberRepository extends JpaRepository<LeagueMember, Long> {
    @Query(value = "SELECT league_id FROM League_Member where user_id = ?1", nativeQuery = true)
    Optional<List<Long>> findLeagueIdsByMemberId(Long userId);
}
