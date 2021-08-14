package com.PremiershipPredictorGame.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LeagueService {
    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }
    @PostMapping
    public void addNewLeague(League league) {
        Optional<League> existingLeague = leagueRepository.findLeagueByLeagueName(league.getLeagueName());

        if(existingLeague.isPresent()){
            throw new IllegalStateException("League with name" +league.getLeagueName()+"already exists");
        }
        leagueRepository.save(league);

    }
    @Transactional
    public void updateLeaguePassword(Long leagueId, String password) {
        boolean exists = leagueRepository.existsById(leagueId);
        if(!exists){
            throw new IllegalStateException("league with id" + leagueId + "does not exist");
        }
        League league = leagueRepository.getById(leagueId);
        league.setPassword(password);

    }
}
