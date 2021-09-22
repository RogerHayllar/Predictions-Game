import { useState } from "react";
import PropTypes from "prop-types";

export default function Login({ setToken }) {
  const [loginError, setLoginError] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  async function loginUser(credentials) {
    return fetch("http://localhost:8080/api/v1/login", {
      method: "GET",
      headers: {
        username: credentials.username,
        password: credentials.password,
      },
    }).then((data) => data.json());
  }
  const handleSubmit = async (e) => {
    e.preventDefault();

    const loginResponse = await loginUser({ username, password });
    if (loginResponse.error) {
      setLoginError("Invalid Details, please try again");
      setToken(null);
    } else {
      console.log(loginResponse);
      setLoginError(null);
      setToken(loginResponse);
    }
  };
  return (
    <div className="login-wrapper">
      <h2></h2>
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
        <button type="submit">login</button>
      </form>
      {loginError && <p>{loginError}</p>}
    </div>
  );
}
Login.propTypes = {
  setToken: PropTypes.func.isRequired,
};
