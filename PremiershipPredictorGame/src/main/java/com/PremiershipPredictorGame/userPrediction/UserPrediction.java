package com.PremiershipPredictorGame.userPrediction;
import com.PremiershipPredictorGame.fixture.Fixture;
import com.PremiershipPredictorGame.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userName", "fixtureId" }) })
@NoArgsConstructor
@AllArgsConstructor

public class UserPrediction {


    @Id
    @SequenceGenerator(
            name = "user_prediction_sequence",
            sequenceName = "user_prediction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_prediction_sequence"
    )
    private Long predictionId;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "fixtureId")
    @JsonIgnore
    private Fixture fixture;
    @NotNull
    private Integer wager;
    private Integer awardedScore;
    private String predictedWinner;

    public String getPredictedWinner() {
        return predictedWinner;
    }

    public void setPredictedWinner(String winner) {
        this.predictedWinner = winner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Integer getWager() {
        return wager;
    }

    public Long getPredictionId() {
        return predictionId;
    }

    public void setWager(Integer wager) {
        this.wager = wager;
    }

    public Integer getAwardedScore() {
        return awardedScore;
    }

    public void setAwardedScore(Integer awardedScore) {
        this.awardedScore = awardedScore;
    }

    public UserPrediction(User user, Fixture fixture, Integer wager, String predictedWinner) {
        this.user = user;
        this.fixture = fixture;
        this.wager = wager;
        this.predictedWinner = predictedWinner;
    }
}
