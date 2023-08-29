import React from 'react';
import { Link } from 'react-router-dom';

function AgencyDashboard() {
  const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center', // Center items horizontally
    justifyContent: 'center', // Center items vertically
    height: '100vh', // Make the container full height
  };

  const divStyle = {
    padding: '20px',
    backgroundColor: '#b8b2b2',
    boxShadow: '10px 10px 10px 5px grey',
    marginTop: '10px',
    borderRadius: '8px',
    width: '50%',
    display: 'flex',
    justifyContent: 'space-around',
    alignItems: 'center',
  };

  const linkStyle = {
    textDecoration: 'none',
    color: '#333',
    fontSize: '20px',
    fontWeight: 'bold',
  };

  return (
    <div style={containerStyle}>
      <div style={divStyle}>
        <Link to="/addpackage" className="nav-link" style={linkStyle}>
          Insert Package
        </Link>
      </div>
      <br />
      <div style={divStyle}>
        <Link to="/viewpackages" className="nav-link" style={linkStyle}>
          View Packages
        </Link>
      </div>

      <br />
      <div style={divStyle}>
        <Link to="/viewbookings" className="nav-link" style={linkStyle}>
          View Bookings
        </Link>
      </div>
    </div>
  );
}

export default AgencyDashboard;
