package com.PremiershipPredictorGame.userPrediction;

import com.PremiershipPredictorGame.fixture.Fixture;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PredictionRequest {
    private Long id;
    private String predictedWinner;
    private Integer wager;

}
