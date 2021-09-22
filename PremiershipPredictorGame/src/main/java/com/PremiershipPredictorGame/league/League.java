package com.PremiershipPredictorGame.league;


import com.PremiershipPredictorGame.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor

public class League {

    @Id
    private String leagueName;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "league_member",
            joinColumns = @JoinColumn(name="leagueName"),
            inverseJoinColumns =  @JoinColumn(name = "username")
    )
    Set<User> leagueMembers;



    public League(String leagueName, String password) {

        this.leagueName = leagueName;
        this.password = password;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<User> getLeagueMembers() {
        return leagueMembers;
    }

    public void setLeagueMembers(Set<User> leagueMembers) {
        this.leagueMembers = leagueMembers;
    }
}
