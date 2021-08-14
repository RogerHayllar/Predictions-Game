package com.PremiershipPredictorGame.league;

import com.PremiershipPredictorGame.leagueMember.LeagueMember;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor

public class League {

    @Id
    @SequenceGenerator(
            name = "league_sequence",
            sequenceName = "league_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "league_sequence"
    )

    private Long leagueId;
    @Column(
            nullable = false
    )

    private String leagueName;
    private String password;

    @OneToMany(mappedBy = "league")
    Set<LeagueMember> membership;

    public League( String leagueName, String password) {

        this.leagueName = leagueName;
        this.password = password;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
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

    public void setPassword(String inviteCode) {
        this.password = inviteCode;
    }

}
