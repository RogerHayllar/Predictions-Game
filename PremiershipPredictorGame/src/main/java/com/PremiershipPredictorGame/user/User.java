package com.PremiershipPredictorGame.user;

import com.PremiershipPredictorGame.league.League;

import com.PremiershipPredictorGame.userPrediction.UserPrediction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javassist.Loader;
import jdk.dynalink.linker.support.SimpleLinkRequest;
import lombok.*;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name="users")
@NoArgsConstructor

@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    private String username;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    private Integer score;

    @ManyToMany(mappedBy = "leagueMembers")
    @JsonIgnore
    Set<League> leaguesJoined;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<UserPrediction> predictions;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.score = 600;
        this.leaguesJoined = Collections.EMPTY_SET;
        this.predictions = Collections.EMPTY_SET;
    }


};