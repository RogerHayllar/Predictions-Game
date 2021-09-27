import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
const Leagues = ({ token }) => {
  const username = token.username;
  const [leagues, setLeagues] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/user/" + username + "/leagues")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setLeagues(data);
      });
  }, [username]);
  return (
    <div className="centredWrapper">
      <div className={"LeagueList"}>
        <h2>Your Leagues</h2>

        {leagues &&
          leagues.map((league) => (
            <div key={league}>
              <Link to={`/standings/${league.leagueName}`}>
                {league.leagueName}
              </Link>
              <p>Password is {league.password}</p>
            </div>
          ))}
      </div>
      <Link to="/Joinleague"> Join a new League</Link>
      <br></br>
      <Link to="/Createleague"> Create a new League</Link>
    </div>
  );
};

export default Leagues;
