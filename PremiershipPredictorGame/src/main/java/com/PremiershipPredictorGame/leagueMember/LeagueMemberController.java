package com.PremiershipPredictorGame.leagueMember;

import com.PremiershipPredictorGame.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/leaguemember")
public class LeagueMemberController {
    private final LeagueMemberService leagueMemberService;
    @Autowired
    public LeagueMemberController(LeagueMemberService leagueMemberService) {
        this.leagueMemberService = leagueMemberService;
    }
    @PostMapping
    public void addNewLeagueMember(@RequestBody LeagueMember leagueMember){
        leagueMemberService.addNewLeagueMember(leagueMember);
    }
    @GetMapping("/member")
    public List<League> getLeaguesByMemberId(@RequestParam Long userId){
        return  leagueMemberService.getLeaguesByMemberId(userId);
    }



}
