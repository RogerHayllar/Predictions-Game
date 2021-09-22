package com.PremiershipPredictorGame.userPrediction;

import com.PremiershipPredictorGame.fixture.Fixture;
import com.PremiershipPredictorGame.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPredictionRepository extends JpaRepository<UserPrediction, Long> {



    Optional<UserPrediction> findByUserAndFixture(User user, Fixture fixture);
    @Query(value = "SELECT * FROM user_prediction u WHERE u.username=?1 AND u.fixture_id = ?2", nativeQuery = true)
    UserPrediction findByUsernameAndFixtureId(String username, Long fixtureID);
}
