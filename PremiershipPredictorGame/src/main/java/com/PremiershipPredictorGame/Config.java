package com.PremiershipPredictorGame;

import com.PremiershipPredictorGame.fixture.Fixture;
import com.PremiershipPredictorGame.fixture.FixtureRepository;
import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository
    ) {
        return args ->
        {
            User Roger = new User(
                    "Roger123",
                    "passwordRoger"

            );
            User Harry = new User(
                    "Harry123",
                    "passwordHarry"
            );
            userRepository.saveAll(
                    List.of(Roger, Harry));

        };
    }

}



