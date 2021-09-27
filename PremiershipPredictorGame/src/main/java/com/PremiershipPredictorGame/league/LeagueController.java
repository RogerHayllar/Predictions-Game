package com.PremiershipPredictorGame.league;

import com.PremiershipPredictorGame.login.Token;
import com.PremiershipPredictorGame.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/league")
public class LeagueController {
    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping(path = "/create/{leagueName}")
    public void createNewLeague(@PathVariable("leagueName")String leagueName,
                                @RequestParam("password")String password)
    {
        leagueService.addNewLeague(leagueName, password);
    }

    @PutMapping(path= "{leagueName}")
    public void updateLeaguePassword(@PathVariable("leagueName") String leagueName,
                                       @RequestParam String password){
        leagueService.updateLeaguePassword(leagueName, password);
    }
    @PutMapping(path = "join/{leagueName}/{username}")
    public void addLeagueMember(@PathVariable("leagueName") String leagueName,
                                @PathVariable("username") String username,
                                @RequestParam String password){
        leagueService.addLeagueMember(leagueName, username, password);
    }

    @GetMapping(path = "{leagueName}")
    public League getLeagueByName(@PathVariable("leagueName") String leagueName){
        return leagueService.getLeagueByName(leagueName);
    }
    @GetMapping(path = "{leagueName}/standings")
    public List<LeagueStanding> getUsernameAndScoresOfLeague(@PathVariable("leagueName") String leagueName){
        return leagueService.getUsernameAndScoresOfLeague(leagueName);
    }






}
