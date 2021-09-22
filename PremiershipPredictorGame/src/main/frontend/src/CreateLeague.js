import React, { useState } from "react";

const CreateLeague = (props) => {
  const token = props.token;
  const [leagueName, setLeagueName] = useState(null);
  const [leaguePassword, setLeaguePassword] = useState(null);
  const [createError, setCreateError] = useState(null);
  const handleSubmit = async (e) => {
    e.preventDefault();
    setCreateError(null);
    fetch(
      "http://localhost:8080/api/v1/league/create/" +
        leagueName +
        "/?password=" +
        leaguePassword,
      {
        method: "POST",
      }
    ).then((res) => {
      const isJson = res.headers
        .get("content-type")
        ?.includes("application/json");
      if (isJson && !res.ok) {
        setCreateError("League with name already exists");
      }
    });
  };

  return (
    <div className="wrapper">
      <form onSubmit={handleSubmit}>
        <h2>Create a new league:</h2>
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
        <button type="submit">Create League</button>
      </form>
      {createError && <p>{createError}</p>}
    </div>
  );
};

export default CreateLeague;
