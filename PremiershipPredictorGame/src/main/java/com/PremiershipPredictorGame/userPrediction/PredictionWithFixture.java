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
public class PredictionWithFixture {
   private UserPrediction prediction;
   private Fixture fixture;



}
