import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ViewPredictions = (props) => {
  const token = props.token;

  var { username } = useParams();
  const [week, setWeek] = useState(1);
  const [predictionsWithFixture, setPredictionsWithFixture] = useState(null);

  useEffect(() => {
    fetch(
      "http://localhost:8080/api/v1/userprediction/" +
        username +
        "/predictions/" +
        week
    )
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setPredictionsWithFixture(data);
        console.log(data);
      });
  }, [week]);
  const handleClickNextWeek = async (e) => {
    e.preventDefault();
    if (week < 3) {
      setWeek(week + 1);
    }
  };
  const handleClickPreviousWeek = async (e) => {
    e.preventDefault();
    if (parseInt(week) > 1) {
      setWeek(week - 1);
    }
  };

  return (
    <div className="prediction-wrapper">
      <button onClick={handleClickPreviousWeek}>Previous week</button>
      <button onClick={handleClickNextWeek}>Next week</button>
      <h1>
        {username}'s Predictions for week {week}
      </h1>
      <table className="predictionsTable">
        <thead>
          <tr>
            <th>Fixture</th>
            <th>Predicted Winner</th>
            <th>Winner</th>
            <th>Wager</th>
            <th>Points Awarded</th>
          </tr>
        </thead>
        <tbody>
          {predictionsWithFixture &&
            predictionsWithFixture.map((p) => (
              <tr key={p.prediction.predictionId}>
                <td>
                  {p.fixture.homeTeam} vs {p.fixture.awayTeam}
                </td>
                <td>{p.prediction.predictedWinner}</td>
                <td>{p.fixture.winner}</td>
                <td>{p.prediction.wager}</td>
                <td>{p.prediction.awardedScore}</td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewPredictions;
