package com.PremiershipPredictorGame.userPrediction;

import com.PremiershipPredictorGame.fixture.Fixture;
import com.PremiershipPredictorGame.fixture.FixtureRepository;
import com.PremiershipPredictorGame.login.Token;
import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.user.UserRepository;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserPredictionService {
    private final UserPredictionRepository userPredictionRepository;
    private final UserRepository userRepository;
    private final FixtureRepository fixtureRepository;
    @Autowired
    public UserPredictionService(UserPredictionRepository userPredictionRepository, UserRepository userRepository, FixtureRepository fixtureRepository) {
        this.userPredictionRepository = userPredictionRepository;
        this.userRepository = userRepository;
        this.fixtureRepository = fixtureRepository;
    }


    public UserPrediction createNewPrediction(PredictionRequest submittedPrediction, Token token){
        if(submittedPrediction.getWager()<0){
            throw new IllegalStateException("wager must be positive");
            //Stop any negative wagers being made, although front end also stops this, this stops any http requests
        }

        Fixture fixture = fixtureRepository.findById(submittedPrediction.getId())
                .orElseThrow(() -> new IllegalStateException("fixture does not exist"));
        if(fixture.getWinner()!=null) {
            throw new IllegalStateException("fixture has already been played");
        } //cannot allow predictions on games where result is known
        User user = userRepository.findByUsername(token.getUsername())
                .orElseThrow(() -> new IllegalStateException("user does not exist"));
        if(!token.getPassword().equals(user.getPassword())){
            throw new IllegalStateException("incorrect password");
        }// need to authenticate the user
        if(user.getScore() < submittedPrediction.getWager()){
            throw new IllegalStateException("not enough points to wager");
        } //user needs sufficient points to wager
        Optional<UserPrediction> existingPrediction = userPredictionRepository.findByUserAndFixture(user,fixture);
        System.out.println(existingPrediction);
        if(existingPrediction.isPresent()){
            throw new IllegalStateException("Prediction already made");
            //cannot allow two predictions to be made by a user on one fixture
        }

        UserPrediction userPrediction = new UserPrediction(user, fixture, submittedPrediction.getWager(), submittedPrediction.getPredictedWinner());
        userPredictionRepository.save(userPrediction);
        return userPrediction;

    }


    @Transactional
    public void addPredictionToUserAndFixture(UserPrediction prediction){
        Fixture fixture = prediction.getFixture();
        User user = prediction.getUser();
        Set<UserPrediction> existingPredictionsOfFixture = fixture.getPredictions();
        existingPredictionsOfFixture.add(prediction);
        fixture.setPredictions(existingPredictionsOfFixture);
        Set<UserPrediction> existingPredictionsMadeByUser = user.getPredictions();
        existingPredictionsMadeByUser.add(prediction);
        user.setPredictions(existingPredictionsMadeByUser);
        user.setScore(user.getScore()- prediction.getWager());
    }



    @Transactional
    public void awardPointForFixture(Integer week) {
        List<Fixture> fixturesInWeek = fixtureRepository.findFixturesByWeek(week);
        for (Fixture fixture : fixturesInWeek) {

            Set<UserPrediction> predictions = fixture.getPredictions();
            int correctTotalWagers = 0;
            int incorrectTotalWagers = 0;
            List<UserPrediction> correctPredictions = new ArrayList<>();
            for (UserPrediction prediction : predictions) {
                User user = prediction.getUser();
                if (prediction.getPredictedWinner().equals("DRAW")){
                user.setScore(user.getScore()+ prediction.getWager());
                prediction.setAwardedScore(0);
                // return wager to users in case of draw
                }
                else {
                    if (prediction.getPredictedWinner().equals(fixture.getWinner())) {
                        correctPredictions.add(prediction);
                        correctTotalWagers += prediction.getWager();
                    } else {
                        incorrectTotalWagers += prediction.getWager();
                        prediction.setAwardedScore(0);

                    }
                }
                //add up all of the incorrect wagers and correct wagers, and create a list of the users who predicted correctly


            }
            for (UserPrediction correctPrediction : correctPredictions) {
                User user = correctPrediction.getUser();
                int awardedPoints = correctPrediction.getWager() + (correctPrediction.getWager() * incorrectTotalWagers / (correctTotalWagers));
                user.setScore(user.getScore() + awardedPoints);
                correctPrediction.setAwardedScore(awardedPoints);
                //give each of the users who were correct their points
            }
        }
    }
    @Transactional
    public void deletePrediction(Long fixtureID, String username, String password) {
    User user = userRepository.findByUsername(username).
            orElseThrow(() -> new IllegalStateException("user does not exist"));
    if(!user.getPassword().equals(password)){
        throw new IllegalStateException("user password incorrect");
    } //verify that the user details are correct
    Fixture predictedFixture = fixtureRepository.findById(fixtureID).
                orElseThrow(() -> new IllegalStateException("fixture does not exist"));
    UserPrediction predictionToBeDeleted = userPredictionRepository.findByUserAndFixture(user, predictedFixture)
        .orElseThrow(() -> new IllegalStateException("prediction does not exist"));
    userPredictionRepository.delete(predictionToBeDeleted);
        System.out.println("prediction deleted from userPrediction table");

    Set<UserPrediction> userPredictions = user.getPredictions();
        System.out.println("user's predictions before deletion"+userPredictions);
    userPredictions.removeIf(prediction -> prediction.getFixture().equals(predictedFixture));
    user.setPredictions(userPredictions);
        System.out.println("user's predictions after deletion"+user.getPredictions());
    Set<UserPrediction> fixturePredictions = predictedFixture.getPredictions();
    fixturePredictions.removeIf(prediction -> prediction.getUser().equals(user));
    predictedFixture.setPredictions(fixturePredictions);

    user.setScore(user.getScore() + predictionToBeDeleted.getWager());
    // give the user their wager back
    }

    public Boolean checkIfPredictionHasBeenMade(Long fixtureID, String username) {
        System.out.println("attempting");
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new IllegalStateException("User doesn't exist"));
        UserPrediction prediction = userPredictionRepository.findByUsernameAndFixtureId(username, fixtureID);
        if(prediction==null) {
            return false;
        }
        return true;
        }

    public List<PredictionWithFixture> getUsersPredictionsByWeek(String username, Integer week) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("User doesn't exist"));
        List<PredictionWithFixture> predictionsByWeek = new ArrayList<>();
        for(UserPrediction prediction: user.getPredictions()){
            if(prediction.getFixture().getWeek().equals(week)){
                predictionsByWeek.add(new PredictionWithFixture(prediction, prediction.getFixture()));
            }
        }
        return predictionsByWeek;
    }
}
