package com.PremiershipPredictorGame.user;

import com.PremiershipPredictorGame.leagueMember.LeagueMember;
import com.PremiershipPredictorGame.userPrediction.UserPrediction;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@NoArgsConstructor

public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Getter
    @Setter
    private Long id;
    @Column(

            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String username;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;
    @Transient
    private Integer score;
    @OneToMany(mappedBy = "user")
    Set<LeagueMember> membership;

    @OneToMany(mappedBy = "user")
    Set<UserPrediction> prediction;

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
