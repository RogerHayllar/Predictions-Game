package com.PremiershipPredictorGame.leagueMember;

import com.PremiershipPredictorGame.league.League;
import com.PremiershipPredictorGame.league.LeagueRepository;
import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueMemberService {
    private final UserRepository userRepository;

    private final LeagueRepository leagueRepository;

    private final LeagueMemberRepository leagueMemberRepository;
    @Autowired
    public LeagueMemberService(UserRepository userRepository, LeagueRepository leagueRepository, LeagueMemberRepository leagueMemberRepository) {
        this.userRepository = userRepository;
        this.leagueRepository = leagueRepository;
        this.leagueMemberRepository = leagueMemberRepository;
    }

    public void addNewLeagueMember(LeagueMember leagueMember) {
        User user = userRepository.findById(leagueMember.getUser().getId()).orElseThrow(()-> new IllegalStateException("user does not exist"));
        League league = leagueRepository.findById(leagueMember.getLeague().getLeagueId()).orElseThrow(()-> new IllegalStateException("league does not exist"));
        String password = leagueMember.getLeaguePassword();
        if(!password.equals(league.getPassword())){
            throw new IllegalStateException("incorrect password for league");
        }
        leagueMemberRepository.save(leagueMember);

    }


    public List<League> getLeaguesByMemberId(Long userId) {
        Boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id" + userId + "does not exist");
        }
        List<Long> leagueIds = leagueMemberRepository.findLeagueIdsByMemberId(userId).orElseThrow(() -> new IllegalStateException("User is not a member of any leagues"));

        List<League> leagues = new ArrayList<League>();
        for(Long leagueId: leagueIds ){
            leagues.add(leagueRepository.findById(leagueId).orElse(null));
        }
        return leagues;

        }

}
