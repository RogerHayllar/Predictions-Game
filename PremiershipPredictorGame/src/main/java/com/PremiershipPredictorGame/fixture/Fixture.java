package com.PremiershipPredictorGame.fixture;

import com.PremiershipPredictorGame.userPrediction.UserPrediction;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor

public class Fixture {

    public enum Team{
        BAT("Bath Rugby"),
        BRI("Bristol Bears"),
        EXE("Exeter Chiefs"),
        GLO("Gloucester Rugby"),
        HAR("Harlequins"),
        LEI("Leicester Rugby"),
        LIR("London Irish"),
        NEW("Newcastle Falcons"),
        NOR("Northampton Saints"),
        SAL("Sale Sharks"),
        SAR("Saracens"),
        WAS("Wasps"),
        WOR("Worcester Warriors");

        public final String teamName;

        private Team(String teamName){
            this.teamName = teamName;
        }



    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getOutcome() {
        return outcome;
    }

    public void setOutcome(Integer outcome) {
        this.outcome = outcome;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
    @Getter
    @Setter
    @Id
    @SequenceGenerator(
            name = "fixture_sequence",
            sequenceName = "fixture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fixture_sequence"
    )




    private Long fixtureId;
    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Team homeTeam;
    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Team awayTeam;
    private Integer outcome;
    @Column(
            nullable = false
    )
    private Integer week;
    @OneToMany(mappedBy = "fixture")
    Set<UserPrediction> prediction;

    public Fixture(Team homeTeam, Team awayTeam, Integer outcome, Integer week) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.outcome = outcome;
        this.week = week;
    }
}
