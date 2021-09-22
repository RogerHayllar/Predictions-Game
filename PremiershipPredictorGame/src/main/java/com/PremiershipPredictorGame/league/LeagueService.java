package com.PremiershipPredictorGame.league;

import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final UserRepository userRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository, UserRepository userRepository) {
        this.leagueRepository = leagueRepository;
        this.userRepository = userRepository;
    }
    @PostMapping
    public void addNewLeague(String leagueName, String password) {
        Optional<League> existingLeague = leagueRepository.findLeagueByLeagueName(leagueName);
        if(existingLeague.isPresent()){
            throw new IllegalStateException("League with name" +leagueName+"already exists");
        }
        League league = new League(leagueName, password);
        leagueRepository.save(league);

    }
    @Transactional
    public void updateLeaguePassword(String leagueName, String password) {

        League league = leagueRepository.findLeagueByLeagueName(leagueName)
                .orElseThrow(()-> new IllegalStateException("League with name"+leagueName+"does not exist"));
        league.setPassword(password);
    }
    @Transactional
    public void addLeagueMember(String leagueName, String username, String password) {
        League league = leagueRepository.findLeagueByLeagueName(leagueName)
                .orElseThrow(()-> new IllegalStateException("League with name"+leagueName+"does not exist"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new IllegalStateException("User"+username+"does not exist"));
        if(!password.equals(league.getPassword())){
            throw new IllegalStateException("incorrect league password");
        }
        if(user.getLeaguesJoined().contains(league)){
            throw new IllegalStateException("user is already a member of league ");
        }
        if(league.getLeagueMembers().contains(user)){
            throw new IllegalStateException("user is already a member of league ");
        }
        Set<League> leaguesJoined = user.getLeaguesJoined();
        leaguesJoined.add(league);
        user.setLeaguesJoined(leaguesJoined);
        Set<User> leagueMembers = league.getLeagueMembers();
        leagueMembers.add(user);
        league.setLeagueMembers(leagueMembers);

    }

    public League getLeagueByName(String leagueName) {
        return leagueRepository.findLeagueByLeagueName(leagueName).orElseThrow(() -> new IllegalStateException("league with name" + leagueName + "not found"));
    }

    public List<LeagueStanding> getUsernameAndScoresOfLeague(String leagueName) {
        League league = getLeagueByName(leagueName);
        Set<User> leagueMembers = league.getLeagueMembers();
        List<LeagueStanding> members = new ArrayList<>();
        for( User user: leagueMembers){
            members.add(new LeagueStanding(user.getUsername(), user.getScore()));
        }
        Collections.sort(members, new LeagueStandingSorter());
        return members;
    }
}
