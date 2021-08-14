package com.PremiershipPredictorGame.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/league")
public class LeagueController {
    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping
    public void createNewLeague(@RequestBody League league){
        leagueService.addNewLeague(league);
    }

    @PutMapping(path= "{leagueId}")
    public void updateLeaguePassword(@PathVariable("leagueId") Long leagueId,
                                       @RequestParam String password){
        leagueService.updateLeaguePassword(leagueId, password);
    }





}
