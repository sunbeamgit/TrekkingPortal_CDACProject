import React from 'react';
import Header from './Header';
import Footer from './Footer';
import Navbar from './Navbar';

function Landing() {
  return (
    <div className='main-container'>
      <Header/>
      <Navbar></Navbar>
      <Footer/>
    </div>
  );
}
export default Landing;
