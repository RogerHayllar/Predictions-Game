package com.PremiershipPredictorGame.userPrediction;
import com.PremiershipPredictorGame.fixture.Fixture;
import com.PremiershipPredictorGame.user.User;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "fixtureId" }) })
@NoArgsConstructor
@AllArgsConstructor

public class UserPrediction {

    @Getter
    @Setter
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
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fixtureId")

    private Fixture fixture;
    private Integer outcome;
    private Integer awardedScore;

}
