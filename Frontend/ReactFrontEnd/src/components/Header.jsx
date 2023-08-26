import React from 'react';
import logoImage from '../images/logo-png.png';

function Header() {
  return (
    <div className="container-fluid">
      <img
        src={logoImage} 
        alt="Logo"
        style={{ width: '170px', height: '50px' }} 
      />
    </div>
  );
}

export default Header;