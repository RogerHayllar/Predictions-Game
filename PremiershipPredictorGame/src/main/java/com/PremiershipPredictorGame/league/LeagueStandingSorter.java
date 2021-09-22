package com.PremiershipPredictorGame.league;

import java.util.Comparator;

public class LeagueStandingSorter implements Comparator<LeagueStanding> {
    @Override
    public int compare(LeagueStanding a, LeagueStanding b){
        return b.getScore() - a.getScore();

    }
}

