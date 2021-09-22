package com.PremiershipPredictorGame.user;

import com.PremiershipPredictorGame.fixture.FixtureRepository;
import com.PremiershipPredictorGame.league.League;
import com.PremiershipPredictorGame.login.Token;
import com.PremiershipPredictorGame.userPrediction.UserPrediction;
import com.PremiershipPredictorGame.userPrediction.UserPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPredictionRepository userPredictionRepository;
    private final FixtureRepository fixtureRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserPredictionRepository userPredictionRepository, FixtureRepository fixtureRepository) {
        this.userRepository = userRepository;
        this.userPredictionRepository = userPredictionRepository;
        this.fixtureRepository = fixtureRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public void addNewUser(User user) {
        Optional<User> userByUsername = userRepository
                .findByUsername(user.getUsername());
        if(userByUsername.isPresent()){
            throw new IllegalStateException("username taken");
        }
        userRepository.save(user);

    }
    public void deleteUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new IllegalStateException("User with username" + username + "does not exist"));
        userRepository.delete(user);
    }
    @Transactional
    public void updateUserPassword(String username,
                           String passwordNew, String passwordOld) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new IllegalStateException("user with username" + username +"does not exist"));
        if( !user.getPassword().equals(passwordOld)){
            throw new IllegalStateException("Old password is incorrect");
        }
        if(passwordNew!=null && passwordNew.length() > 0){
            user.setPassword(passwordNew);


        }
    }
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new IllegalStateException("User with username"+username+"does not exist"));
    }


//    @Transactional
//    public void updateUserScore(String username, Integer week) {
//        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalStateException("User with username"+username+"does not exist"));
//        List<Long> fixtureIds = fixtureRepository.findIdsByWeek(week);
//        List<UserPrediction> predictions = userPredictionRepository.findByUserAndId(user, fixtureIds);
//        int score = user.getScore().intValue();
//        for(UserPrediction prediction: predictions){
//            score += prediction.getAwardedScore();
//        }
//        Integer scoreFinal = Integer.valueOf(score);
//        user.setScore(scoreFinal);
//    }

    public List<String> getLeaguesByUsername(String username) {
        User user = userRepository.findByUsername(username).
                orElseThrow(()->new IllegalStateException("User with username"+username+"does not exist"));
        List<String> leagues = new ArrayList<>();
        for(League league: user.getLeaguesJoined()){
            leagues.add(league.getLeagueName());
        }
        return leagues;
    }
    public boolean verifyToken(Token token, String username){
        User user = userRepository.findByUsername(username).
            orElseThrow(()->new IllegalStateException("User with username"+username+"does not exist"));
        if (token.getUsername().equals(user.getUsername()) && token.getPassword().equals(user.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean verifyAdminToken(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        if(username.equals("admin")&&password.equals("admin")){
            return true;
        }
        return false;
    }

    public Integer getScoreByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalStateException("User with username"+username+"does not exist"));
        return user.getScore();
    }
    @Transactional
    public void add300PointsToUsers(String username, String password) {
        if(username.equals("admin") && password.equals("admin")){
            List<User> users = userRepository.findAll();
            for(User user: users){
                user.setScore(user.getScore()+300);
            }
        }
    }
}
