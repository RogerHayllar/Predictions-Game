package com.PremiershipPredictorGame.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/fixture")
public class FixtureController {
    private final FixtureService fixtureService;
    @Autowired
    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }
    @GetMapping(path = "/week/{week}")
    public List<Fixture> getFixturesByWeek(@PathVariable("week") Integer week){
         return fixtureService.getFixturesByWeek(week);
    }
    @PostMapping(path = "/admin/create")
    public void addNewFixture(@RequestBody Fixture fixture){
        fixtureService.addNewFixture(fixture);
    }
    @PutMapping(path="/admin/updateScore/{fixtureId}")
    public void updateOutcome(@PathVariable("fixtureId") Long fixtureId,
                              @RequestParam("winner") String winner){
        fixtureService.updateOutcome(fixtureId, winner);
    }
    @DeleteMapping(path="/admin/{fixtureId}")
    public void deleteFixture(@PathVariable("fixtureId") Long fixtureId){
        fixtureService.deleteFixture(fixtureId);

    }
    @PutMapping(path="/lock/{week}")
    public void lockWeeksFixtures(@PathVariable("week") Integer week){
        fixtureService.lockWeeksFixtures(week);

    }
    @GetMapping(path ="/team")
    public List<Fixture> getFixturesByTeam(@RequestParam String team){
        return fixtureService.getFixturesByTeam(team);
    }
    @GetMapping(path = "/{id}")
    public Fixture getFixtureById(@PathVariable("id") Long id){
        return fixtureService.getFixturesById(id);
    }


}
