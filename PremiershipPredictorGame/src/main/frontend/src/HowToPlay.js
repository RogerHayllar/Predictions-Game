import React, from "react";
const HowToPlay = () => {
  return (
    <div className="HowToPlayWrapper">
      <br />

      <p>
        Welcome to the Premiership rugby predictor, a game where you can compete
        against friends by predicting winners of each fixture.
      </p>
      <br />
      <p>
        {" "}
        To play, click on the fixtures to see the games this week. Navigate to
        the week of games you would like to predict, and click on the predict
        button for the corresponding fixture. You can wager as many points as
        you have on any outcome. Once the prediction deadline has passed, which
        will be 19:45 on each Friday evening, you will not be able to submit any
        more predictions for any games on that weekend.
      </p>
      <br />
      <p>
        At the end of the weekend each player will receive 300 points no matter
        how your predictions did. Even if you lose all of your points on a few
        bad predictions, you will be able to keep playing. The scoring system
        will assign odds to each game, although these will not be shown. This
        should influence your picks for each game. The more points wagered on
        one team, the smaller your return will be if they win. If a game ends in
        a draw, everybody's wagers will be returned.
      </p>
      <br />
      <p>
        Unless you want to compete alone, click on the "Your Leagues" tab to
        join a league. Here you can also create your own league, after which you
        will need to join it yourself.
      </p>
    </div>
  );
};

export default HowToPlay;
