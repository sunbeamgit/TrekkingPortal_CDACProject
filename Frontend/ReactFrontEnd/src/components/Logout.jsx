import React from 'react';

const Logout = () => {
  const divStyle = {
    textAlign: 'center',
    margin: '100px',
    padding: '10px',
    border: '1px solid #ccc',
    borderRadius: '4px',
    backgroundColor: '#f09',
  };

  return (
    <div style={divStyle}>
      <p>Logged Out Successfully</p>
    </div>
  );
};

export default Logout;
