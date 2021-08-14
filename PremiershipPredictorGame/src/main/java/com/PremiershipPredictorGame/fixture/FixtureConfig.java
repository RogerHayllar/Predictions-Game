package com.PremiershipPredictorGame.fixture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FixtureConfig {
    @Bean
    CommandLineRunner commandLineRunnerFixture(FixtureRepository fixtureRepository) {
        return args -> {

            Fixture fix1 = new Fixture(
                    Fixture.Team.EXE,
                    Fixture.Team.BAT,
                    null,
                    1
            );
            Fixture fix2 = new Fixture(
                    Fixture.Team.EXE,
                    Fixture.Team.NOR,
                    null,
                    2
            );
            Fixture fix3 = new Fixture(
                    Fixture.Team.BAT,
                    Fixture.Team.NOR,
                    null,
                    2
            );
            fixtureRepository.saveAll(List.of(fix1, fix2, fix3));
        };
    }
}
