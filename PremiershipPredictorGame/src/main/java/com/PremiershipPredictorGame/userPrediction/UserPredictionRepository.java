package com.PremiershipPredictorGame.userPrediction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPredictionRepository extends JpaRepository<UserPrediction, Long> {
}
