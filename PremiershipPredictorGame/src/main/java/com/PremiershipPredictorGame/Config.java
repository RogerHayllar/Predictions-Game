//package com.PremiershipPredictorGame;
//
//import com.PremiershipPredictorGame.fixture.Fixture;
//import com.PremiershipPredictorGame.fixture.FixtureRepository;
//import com.PremiershipPredictorGame.league.League;
//import com.PremiershipPredictorGame.league.LeagueRepository;
//import com.PremiershipPredictorGame.user.User;
//import com.PremiershipPredictorGame.user.UserRepository;
//import com.PremiershipPredictorGame.userPrediction.UserPrediction;
//import com.PremiershipPredictorGame.userPrediction.UserPredictionRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class Config {
//    @Bean
//    CommandLineRunner commandLineRunnerUser(UserRepository userRepository,
//                                            LeagueRepository leagueRepository,
//                                            FixtureRepository fixtureRepository,
//                                            UserPredictionRepository userPredictionRepository
//    ) {
//        return args ->
//        {
//            User roger = new User(
//                    "Roger",
//                    "passwordRoger"
//
//            );
//            roger.setScore(1);
//            User harry = new User(
//                    "Harry",
//                    "passwordHarry"
//            );
//            roger.setScore(100000);
//            harry.setScore(1000);
//            userRepository.saveAll(
//                    List.of(roger, harry));
//            League GlobalLeague = new League("Global League", "password");
//            League league1 = new League("League1", "pass1");
//            League league2 = new League("League2", "pass2");
//            League league3 = new League("League3", "pass3");
//            leagueRepository.saveAll(List.of(league1, league2, league3));
//            roger.setLeaguesJoined(Set.of(league1, league2));
//            harry.setLeaguesJoined(Set.of(league2, league3));
//            userRepository.saveAll(
//                    List.of(roger, harry));
//            league1.setLeagueMembers(Set.of(roger));
//            league2.setLeagueMembers(Set.of(roger,harry));
//            league3.setLeagueMembers(Set.of(harry));
//            leagueRepository.saveAll(List.of(league1, league2, league3));
//            Fixture fix1 = new Fixture(
//                    "EXE",
//                    "BAT",
//                    1
//            );
//            Fixture fix2 = new Fixture(
//                    "EXE",
//                    "NOR",
//                    2
//            );
//            Fixture fix3 = new Fixture(
//                    "BAT",
//                   "NOR",
//                    2
//            );
//            fixtureRepository.saveAll(List.of(fix1, fix2, fix3));
//            UserPrediction RogerEXEvBAT = new UserPrediction(
//                    roger,
//                    fix1,
//                    100,
//                    "EXE"
//            );
//            UserPrediction HarryEXEvBAT = new UserPrediction(
//                    harry,
//                    fix1,
//                    100,
//                    "BATH"
//            );
//
//            roger.setPredictions(Set.of(RogerEXEvBAT));
//            harry.setPredictions(Set.of(HarryEXEvBAT));
//            fix1.setPredictions(Set.of(RogerEXEvBAT,
//                    HarryEXEvBAT));
//            userPredictionRepository.saveAll(List.of(RogerEXEvBAT,HarryEXEvBAT));
//            userRepository.saveAll(
//                    List.of(roger, harry));
//            fix1.setWinner("EXE");
//            fixtureRepository.saveAll(List.of(fix1, fix2, fix3));
//
//        };
//    }
//
//
//}
//
//
//
