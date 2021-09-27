package com.PremiershipPredictorGame.league;

import java.util.Comparator;
//this comparator class allows us to rank the members of a league
public class LeagueStandingSorter implements Comparator<LeagueStanding> {
    @Override
    public int compare(LeagueStanding a, LeagueStanding b){
        return b.getScore() - a.getScore();

    }
}

