package com.PremiershipPredictorGame.fixture;

import com.PremiershipPredictorGame.user.User;
import com.PremiershipPredictorGame.userPrediction.UserPrediction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor

public class Fixture {

//    public enum Team{
//        BAT("Bath Rugby"),
//        BRI("Bristol Bears"),
//        EXE("Exeter Chiefs"),
//        GLO("Gloucester Rugby"),
//        HAR("Harlequins"),
//        LEI("Leicester Rugby"),
//        LIR("London Irish"),
//        NEW("Newcastle Falcons"),
//        NOR("Northampton Saints"),
//        SAL("Sale Sharks"),
//        SAR("Saracens"),
//        WAS("Wasps"),
//        WOR("Worcester Warriors");
//
//        public final String teamName;
//
//        Team(String teamName){
//            this.teamName = teamName;
//        }
//
//
//
//    }


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
    @OneToMany(mappedBy = "fixture")
    @JsonIgnore
    private Set<UserPrediction> predictions;

    private String homeTeam;
    @Column(
            nullable = false
    )
    private String awayTeam;
    private String winner;
    @Column(
            nullable = false
    )
    private Integer week;



    public Fixture(String homeTeam, String awayTeam, Integer week) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.week = week;

    }
    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }


    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public Set<UserPrediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(Set<UserPrediction> predictions) {
        this.predictions = predictions;
    }
}
