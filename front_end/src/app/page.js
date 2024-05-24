"use client";
import React, { useState } from "react";
import Sidebar from "@/Sidebar/Sidebar";
import Nav from "@/Navigation/Nav";
import "./page.css";
import Component1 from "@/components/page1/Component";


function Home() {
  const [isNavVisible, setIsNavVisible] = useState(false);

  const toggleNavbar = () => {
    setIsNavVisible(!isNavVisible);
  };

  return (
    <div>
      <Sidebar toggleNavbar={toggleNavbar} />
      {isNavVisible && <Nav />}
      <Component1 />

    </div>
  );
}

export default Home;
