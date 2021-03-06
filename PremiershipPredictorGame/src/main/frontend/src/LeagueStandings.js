import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";

const LeagueStandings = () => {
  const { leagueName } = useParams();
  const [standings, setStandings] = useState(null);

  const url =
    "http://localhost:8080/api/v1/league/" + leagueName + "/standings";

  useEffect(() => {
    fetch(url)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setStandings(data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, [url]);

  return (
    <div className="centredWrapper">
      <h2>Current Standings for {leagueName} </h2>
      <table>
        <thead>
          <tr>
            <th>Username</th>
            <th>Score</th>
          </tr>
        </thead>
        <tbody>
          {standings &&
            standings.map((standing) => (
              <tr key={standing.username}>
                <td>
                  <Link to={`/viewPredictions/${standing.username}/${1}`}>
                    {standing.username}
                  </Link>
                </td>
                <td>{standing.score}</td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default LeagueStandings;
