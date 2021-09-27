import React from "react";
import { useState, useEffect } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
const Fixtures = (props) => {
  const [week, setWeek] = useState(1);

  const [fixtures, setFixtures] = useState(null);

  useEffect(() => {
    console.log("use run");
    fetch("http://localhost:8080/api/v1/fixture/week/" + week)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setFixtures(data);
        console.log(data);
      })
      .catch((err) => {
        console.log(err.message);
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
  if (fixtures === undefined) {
    return (
      <div>
        <p> No fixtures</p>;
        <button onClick={handleClickNextWeek}>Next week</button>
        <button onClick={handleClickPreviousWeek}>Previous week</button>
      </div>
    );
  }
  if (fixtures && fixtures[0].winner === null) {
    // At the prediction deadline, the winner of all of week's games will be set to a placeholder value (locked), so if the winner value is null, the user can make a prediction
    return (
      <div className="fixtureWrapper">
        <div>
          <button onClick={handleClickPreviousWeek}>Previous week</button>
          <button onClick={handleClickNextWeek}>Next week</button>
        </div>
        <h4>Fixtures for week {week}</h4>
        {fixtures &&
          fixtures.map((fixture) => (
            <div key={fixture.fixtureId}>
              {fixture.homeTeam} vs {fixture.awayTeam} &nbsp; &nbsp; &nbsp;
              <Link to={`/predict/${fixture.fixtureId}`}>Predict </Link>
            </div>
          ))}
      </div>
    );
  }
  if (fixtures && fixtures[0].winner === "locked") {
    return (
      <div className="fixtureWrapper">
        <div>
          <button onClick={handleClickPreviousWeek}>Previous week</button>
          <button onClick={handleClickNextWeek}>Next week</button>
        </div>
        <h4>Fixtures, week {week}</h4>
        <p>Prediction deadline has passed, games are underway</p>
        {fixtures &&
          fixtures.map((fixture) => (
            <div key={fixture.fixtureId}>
              {fixture.homeTeam} vs {fixture.awayTeam}
            </div>
          ))}
      </div>
    );
  }
  if (fixtures && fixtures[0] && fixtures[0].winner !== "locked") {
    return (
      <div className="fixtureWrapper">
        <div>
          <button onClick={handleClickPreviousWeek}>Previous week</button>
          <button onClick={handleClickNextWeek}>Next week</button>
        </div>
        <h4>Results, week {week}</h4>

        {fixtures &&
          fixtures.map((fixture) => (
            <div key={fixture.fixtureId}>
              {fixture.homeTeam} vs {fixture.awayTeam} Winner was{" "}
              {fixture.winner}
            </div>
          ))}
      </div>
    );
  } else {
    return <p>this hasn't worked</p>;
  }
};
export default Fixtures;
