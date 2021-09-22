import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";
const CreatePrediction = (props) => {
  const { id } = useParams();
  console.log(id);
  const token = props.token;
  const username = props.token.username;

  const [predictedWinner, setPredictedWinner] = useState(null);
  const [wager, setWager] = useState(0);
  const [availablePoints, setAvailablePoints] = useState(null);
  const [fixture, setFixture] = useState(null);
  const [existingPrediction, setExistingPredicton] = useState(null);
  const history = useHistory();
  useEffect(() => {
    fetch("http://localhost:8080/api/v1/user/" + username + "/score")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setAvailablePoints(data);
      });

    fetch(
      "http://localhost:8080/api/v1/userprediction/existingPredictionCheck/" +
        id +
        "/" +
        username
    )
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setExistingPredicton(data);
      });

    fetch("http://localhost:8080/api/v1/fixture/" + id)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setFixture(data);
        console.log("fixture fetched");
      });
  }, [id, username]);
  console.log(fixture);
  const handlePredictClick = async (e) => {
    e.preventDefault();
    const predictionRequest = { id, predictedWinner, wager };
    var sufficientPoints = Boolean(availablePoints - wager >= 0);
    if (sufficientPoints) {
      fetch("http://localhost:8080/api/v1/userprediction/predict", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          username: token.username,
          password: token.password,
        },
        body: JSON.stringify(predictionRequest),
      }).then((res) => {
        const isJson = res.headers
          .get("content-type")
          ?.includes("application/json");
        if (isJson && !res.ok) {
          console.log("error");
        }
      });
      setExistingPredicton(true);
    }
  };
  const handleDeleteClick = async (e) => {
    e.preventDefault();

    fetch(
      "http://localhost:8080/api/v1/userprediction/deletePrediction/" +
        id +
        "/" +
        username,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          password: token.password,
        },
      }
    ).then((res) => {
      const isJson = res.headers
        .get("content-type")
        ?.includes("application/json");
      if (isJson && !res.ok) {
        console.log("error");
      }
      setExistingPredicton(false);
    });
  };
  if (existingPrediction && fixture) {
    return (
      <div className="predictionAlreadMadeWrapper">
        <button onClick={handleDeleteClick}>
          You have already predicted this {fixture.homeTeam} vs
          {fixture.awayTeam}, to delete the prediction and submit a new one,
          click here
        </button>
      </div>
    );
  } else {
    return (
      fixture && (
        <div className="wrapper">
          <p>
            You have {availablePoints} points to wager, select the team you
            think will win
          </p>
          <br />
          <button onClick={() => setPredictedWinner(fixture.homeTeam)}>
            <p>{fixture.homeTeam}</p>
          </button>
          vs
          <button onClick={() => setPredictedWinner(fixture.awayTeam)}>
            {fixture.awayTeam}
          </button>
          <input
            type="number"
            required
            value={wager}
            onChange={(e) => setWager(Math.floor(e.target.value))}
          />
          {predictedWinner && wager && (
            <button onClick={handlePredictClick}>
              Wager {wager} points on a win for {predictedWinner}
            </button>
          )}
        </div>
      )
    );
  }
};

export default CreatePrediction;
