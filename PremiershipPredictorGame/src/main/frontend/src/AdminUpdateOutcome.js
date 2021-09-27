import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
const AdminUpdateOutcome = (props) => {
  const [fixture, setFixture] = useState(null);
  const id = useParams();
  console.log(id.id);
  const token = props.token;
  const [winner, setWinner] = useState(null);
  const handleClick = async (e) => {
    fetch(
      "http://localhost:8080/api/v1/fixture/admin/updateScore/" +
        id.id +
        "?winner=" +
        winner,
      {
        method: "PUT",
        headers: {
          username: token.username,
          password: token.password,
        },
      }
    );
  };
  useEffect(() => {
    fetch("http://localhost:8080/api/v1/fixture/" + id.id)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setFixture(data);
        console.log("fixture fetched");
      });
  }, []);

  return (
    fixture && (
      <div>
        <button onClick={() => setWinner(fixture.homeTeam)}>
          <p>{fixture.homeTeam}</p>
        </button>
        <p> vs </p>
        <button onClick={() => setWinner(fixture.awayTeam)}>
          {fixture.awayTeam}
        </button>
        <button onClick={() => setWinner("DRAW")}>{"Draw"}</button>
        {winner && (
          <button onClick={handleClick}>update winner to {winner}</button>
        )}
      </div>
    )
  );
};

export default AdminUpdateOutcome;
