"use client";
import React from "react";
import "./Nav.css";

const Nav = ({ handleInputChange, query }) => {
  return (
    <nav>
      <div className="nav-container">
        <input
          className="search-input"
          type="text"
          onChange={handleInputChange}
          value={query}
          placeholder="Search..."
        />
      </div>
      <div className="profile-container">
        <a href="#">test</a>
        <a href="#">test</a>
        <a href="#">test</a>
      </div>
    </nav>
  );
};

export default Nav;
