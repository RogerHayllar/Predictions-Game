import React from "react";
import { useState } from "react";
import PropTypes from "prop-types";
const Register = ({ setToken }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [registerError, setRegisterError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const credentials = { username, password };
    fetch("http://localhost:8080/api/v1/registration", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(credentials),
    }).then((res) => {
      const isJson = res.headers
        .get("content-type")
        ?.includes("application/json");
      if (isJson && !res.ok) {
        setRegisterError("Username is already taken");
      } else {
        const token = {
          username: credentials.username,
          password: credentials.password,
        };
        setToken(token);
      }
    });
  };

  return (
    <div className="register-wrapper">
      <form onSubmit={handleSubmit}>
        <label>Username:</label>
        <input
          type="text"
          required
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br></br>
        <label>Password:</label>
        <input
          type="password"
          required
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Register</button>
      </form>
      {registerError && <p>{registerError}</p>}
    </div>
  );
};
Register.propTypes = {
  setToken: PropTypes.func.isRequired,
};
export default Register;
