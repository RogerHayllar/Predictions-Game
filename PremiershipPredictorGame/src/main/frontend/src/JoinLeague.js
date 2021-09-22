import React, { useState } from "react";

const JoinLeague = (props) => {
  const token = props.token;
  const [leagueName, setLeagueName] = useState(null);
  const [leaguePassword, setLeaguePassword] = useState(null);
  const [joinError, setJoinError] = useState(null);
  const handleSubmitJoin = async (e) => {
    e.preventDefault();
    setJoinError(null);
    fetch(
      "http://localhost:8080/api/v1/league/join/" +
        leagueName +
        "/" +
        token.username +
        "?password=" +
        leaguePassword,
      {
        method: "PUT",
      }
    ).then((res) => {
      const isJson = res.headers
        .get("content-type")
        ?.includes("application/json");
      if (isJson && !res.ok) {
        setJoinError("League name or pass is incorrect");
      }
    });
  };

  return (
    <div className="join-wrapper">
      <form onSubmit={handleSubmitJoin}>
        <h2>Join a league:</h2>
        <label>League Name:</label>
        <input
          type="text"
          required
          value={leagueName}
          onChange={(e) => setLeagueName(e.target.value)}
        />
        <br></br>
        <label>Password:</label>
        <input
          type="text"
          required
          value={leaguePassword}
          onChange={(e) => setLeaguePassword(e.target.value)}
        />
        <button type="submit">Join League</button>
      </form>
      {joinError && <p>{joinError}</p>}
    </div>
  );
};

export default JoinLeague;
