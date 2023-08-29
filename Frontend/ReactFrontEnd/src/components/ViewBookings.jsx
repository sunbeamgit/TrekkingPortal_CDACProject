import React, { useState, useEffect } from 'react';
import axios from 'axios';

const tableStyle = {
  width: '100%',
  borderCollapse: 'collapse',
  border: '1px solid #ddd',
  boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
};

const thStyle = {
  padding: '10px',
  textAlign: 'left',
  borderBottom: '1px solid #ddd',
  backgroundColor: '#f9f9f9',
};

const tdStyle = {
  padding: '10px',
  textAlign: 'left',
  borderBottom: '1px solid #ddd',
};

const evenRowStyle = {
  backgroundColor: '#f2f2f2',
};

function ViewBookings() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:7070/agency/bookingdetails")
      .then(response => {
        setBookings(response.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <div style={{ margin: '20px' }}>
      <h1 style={{ textAlign: 'center', marginBottom: '20px', fontSize: '24px', color: '#333' }}>Booking List</h1>
      <table style={tableStyle}>
        <thead>
          <tr>
            <th style={thStyle}>Booking ID</th>
            <th style={thStyle}>Trekker First Name</th>
            <th style={thStyle}>Trekker Last Name</th>
            <th style={thStyle}>Booked Package Name</th>
            <th style={thStyle}>Date</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((booking, index) => (
            <tr key={booking.id} style={index % 2 === 0 ? evenRowStyle : {}}>
              <td style={tdStyle}>{booking.id}</td>
              <td style={tdStyle}>{booking.firstname}</td>
              <td style={tdStyle}>{booking.lastname}</td>
              <td style={tdStyle}>{booking.packageName}</td>
              <td style={tdStyle}>{booking.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewBookings;
