import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
const AdminFixtureList = (props) => {
  const [week, setWeek] = useState(1);
  const [fixtures, setFixtures] = useState(null);
  const token = props.token;
  console.log(token);

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
  const handleClickLockFixtures = async (e) => {
    e.preventDefault();
    fetch("http://localhost:8080/api/v1/fixture/lock/" + week, {
      method: "PUT",
      headers: {
        username: token.username,
        password: token.password,
      },
    }).then(console.log(week + "fixtures have been locked"));
  };
  const handleClickAwardPoints = async (e) => {
    e.preventDefault();
    fetch(
      "http://localhost:8080/api/v1/userprediction/admin/awardPoints/" + week,
      {
        method: "POST",
        headers: {
          username: token.username,
          password: token.password,
        },
      }
    ).then(console.log(week + "'s points have been awarded for predictions"));
    fetch("http://localhost:8080/api/v1/user/admin/add50points", {
      method: "POST",
      headers: {
        username: token.username,
        password: token.password,
      },
    }).then(console.log(week + " 300 points have been awarded for each user"));
  };

  return (
    fixtures && (
      <div className="fixture-Wrapper">
        <button onClick={handleClickPreviousWeek}>Last week</button>
        <button onClick={handleClickNextWeek}>Next week</button>

        <h4>Fixtures, week {week}</h4>
        {fixtures &&
          fixtures.map((fixture) => (
            <div key={fixture.fixtureId}>
              {fixture.homeTeam} vs {fixture.awayTeam} &nbsp; &nbsp; &nbsp;
              <Link to={`/admin/updateOutcome/${fixture.fixtureId}`}>
                {" "}
                Update Winner
              </Link>
            </div>
          ))}
        <button onClick={handleClickLockFixtures}>
          Lock all fixtures for the week
        </button>
        <button onClick={handleClickAwardPoints}>
          {" "}
          Award points for predictions
        </button>
      </div>
    )
  );
};

export default AdminFixtureList;
