package com.PremiershipPredictorGame.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fixture")
public class FixtureController {
    private final FixtureService fixtureService;
    @Autowired
    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }
    @GetMapping("/week")
    public List<Fixture> getFixturesByWeek(@RequestParam Integer week){
         return fixtureService.getFixturesByWeek(week);
    }
    @PostMapping
    public void addNewFixture(@RequestBody Fixture fixture){
        fixtureService.addNewFixture(fixture);
    }
    @PutMapping(path="{fixtureId}")
    public void updateOutcome(@PathVariable("fixtureId") Long fixtureId,
                              @RequestParam Integer outcome){
        fixtureService.updateOutcome(fixtureId, outcome);
    }
    @DeleteMapping(path="{fixtureId}")
    public void deleteFixture(@PathVariable("fixtureId") Long fixtureId){
        fixtureService.deleteFixture(fixtureId);

    }
    @GetMapping("/team")
    public List<Fixture> getFixturesByTeam(@RequestParam String team){
        return fixtureService.getFixturesByTeam(team);
    }



}
