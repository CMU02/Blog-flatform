"use client";
import React from "react";
import "./Sidebar.css";

const Sidebar = ({ toggleNavbar }) => {
  return (
    <>
      <section className="sidebar">
        <div className="logo-container">
          <button onClick={toggleNavbar}><h1>test</h1></button>
        </div>
      </section>
    </>
  );
};

export default Sidebar;
