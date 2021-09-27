import React from "react";
import { Link } from "react-router-dom";
const Navbar = (props) => {
  return (
    <nav className="navbar">
      <h1>Premiership Rugby Prediction Game</h1>
      <div className="links">
        <Link to="/leagues">Your Leagues</Link>
        <Link to="/fixtures">Fixtures</Link>
        <Link to={`/viewpredictions/${props.token.username}/1`}>
          Your Predictions
        </Link>
        <Link to="/HowToPlay">How to Play</Link>
      </div>
    </nav>
  );
};

export default Navbar;
