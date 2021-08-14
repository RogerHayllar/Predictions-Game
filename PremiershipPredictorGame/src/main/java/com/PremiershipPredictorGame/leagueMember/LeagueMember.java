package com.PremiershipPredictorGame.leagueMember;

import com.PremiershipPredictorGame.league.League;
import com.PremiershipPredictorGame.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "leagueId"}) })
@NoArgsConstructor
@AllArgsConstructor

public class LeagueMember {
    @Id
    @SequenceGenerator(
            name = "leaguemember_sequence",
            sequenceName = "leaguemember_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "leaguemember_sequence"
    )
    private Long leagueMembershipId;

    private String leaguePassword;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "leagueId")
    private League league;

    public String getLeaguePassword() {
        return leaguePassword;
    }

    public void setLeaguePassword(String leaguePassword) {
        this.leaguePassword = leaguePassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
