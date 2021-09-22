import React, { useState } from "react";

import Login from "./Login";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Leagues from "./Leagues";
import UserInfo from "./UserInfo";
import Register from "./Register";
import LeagueStandings from "./LeagueStandings";
import Fixtures from "./Fixtures";
import CreatePrediction from "./CreatePrediction";
import ViewPredictions from "./ViewPredictions";
import Navbar from "./Navbar";
import JoinLeague from "./JoinLeague";
import CreateLeague from "./CreateLeague";
import AdminFixtureList from "./AdminFixtureList";
import AdminUpdateOutcome from "./AdminUpdateOutcome";
function App() {
  const [token, setToken] = useState();
  if (!token) {
    return (
      <div className="loginRegisterWrapper">
        <Login setToken={setToken} />
        <Register setToken={setToken} />
      </div>
    );
  }
  return (
    <div className="wrapper">
      <p>home</p>
      <Router>
        <Navbar token={token} />
        <Switch>
          <Route exact path="/leagues">
            <Leagues token={token} />
          </Route>
          <Route exact path="/userinfo">
            <UserInfo />
          </Route>
          <Route exact path="/standings/:leagueName">
            <LeagueStandings />
          </Route>
          <Route exact path="/fixtures">
            <Fixtures token={token} />
          </Route>
          <Route exact path="/predict/:id">
            <CreatePrediction token={token} />
          </Route>
          <Route exact path="/Joinleague">
            <JoinLeague token={token} />
          </Route>
          <Route exact path="/Createleague">
            <CreateLeague token={token} />
          </Route>
          <Route exact path="/viewPredictions/:username/:week">
            <ViewPredictions token={token} />
          </Route>
          <Route exact path="/admin/updateOutcome/:id">
            <AdminUpdateOutcome token={token} />
          </Route>
          <Route exact path="/admin/fixtures">
            <AdminFixtureList token={token} />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
