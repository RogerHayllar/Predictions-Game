package com.PremiershipPredictorGame.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
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
    public void updateOutcome(Long fixtureId, Integer outcome) {
        Fixture fixture = fixtureRepository.getById(fixtureId);
        fixture.setOutcome(outcome);
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
}
