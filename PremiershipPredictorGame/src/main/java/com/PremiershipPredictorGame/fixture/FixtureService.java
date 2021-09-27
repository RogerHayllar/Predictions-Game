package com.PremiershipPredictorGame.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FixtureService {
    private final FixtureRepository fixtureRepository;
    @Autowired
    public FixtureService(FixtureRepository fixtureRepository) {
        this.fixtureRepository = fixtureRepository;
    }


    public List<Fixture> getFixturesByWeek(Integer week) {
        List<Fixture> fixtures= fixtureRepository.findFixtureByWeek(week);
        return fixtures;
    }

    public void addNewFixture(Fixture fixture) {
        fixtureRepository.save(fixture);
    }
    @Transactional
    public void updateOutcome(Long fixtureId, String winner) {
        Fixture fixture = fixtureRepository.getById(fixtureId);
        fixture.setWinner(winner);
    }

    public void deleteFixture(Long fixtureId) {
        boolean exists = fixtureRepository.existsById(fixtureId);
        if(!exists){
            throw new IllegalStateException("fixture with id"+fixtureId+"does not exist");
        }
        fixtureRepository.deleteById(fixtureId);
    }
    public List<Fixture> getFixturesByTeam(String team){
        List<Fixture> fixtures = fixtureRepository.findFixturesByTeam(team);
        return fixtures;
    }

    public Fixture getFixturesById(Long id) {
        Fixture fixture = fixtureRepository.findById(id).orElseThrow(()
                ->new IllegalStateException("fixture with id"+ id +"does not exist"));
        return fixture;
    }
    @Transactional
    public void lockWeeksFixtures(Integer week, String username, String password) {
        if(username.equals("admin")&& password.equals("admin")) {
            List<Fixture> fixtures = fixtureRepository.findFixtureByWeek(week);
            for (Fixture fixture : fixtures) {
                fixture.setWinner("locked");
            }
        }
        else{
            throw new IllegalStateException("admin not verified");
        }
    }
}
