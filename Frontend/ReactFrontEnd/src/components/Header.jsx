import React from 'react';
import logoImage from '../images/logo1.png';

function Header() {
  return (
    <div className="container-fluid">
      <img
        src={logoImage} 
        alt="Logo"
        style={{ width: '150px', height: 'auto' }} 
      />
    </div>
  );
}

export default Header;