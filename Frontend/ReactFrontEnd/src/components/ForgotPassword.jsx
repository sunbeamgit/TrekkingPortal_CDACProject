import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

function ForgotPassword() {
  const [email, setEmail] = useState('');
  const history = useHistory();
  const [message, setMessage] = useState('');
  const [showMessage, setShowMessage] = useState(false);

  const ForgotPassword = () => {
    const data = {
      email: email,
    };

    axios
      .post('http://localhost:7070/api/send-otp', data)
      .then(response => {
        console.log(response.data.message);
        console.log(data);
        console.log('Password updated successfully');
        // Reset the form fields after successful update
        setEmail('');

        if (response.data.message === 'OTP sent successfully!') {
          history.push('/forgotpassword2', { email: response.data.email });
        } else {
          setMessage('Error Detected!');
        }
        setShowMessage(true); // Show the message
        setTimeout(() => {
          setShowMessage(false); // Hide the message after 5 seconds
        }, 5000);
      })
      .catch(error => {
        if (error.response && error.response.status === 500) {
          // Handle 500 error
          setMessage('An internal server error occurred. Please try again later.');
        } else {
          setMessage('Error updating password. Please check your input and try again.');
        }
        console.error('Error updating password:', error);
      });
  };

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
      }}
    >
      <div
        style={{
          fontSize: '24px',
          marginBottom: '20px',
        }}
      >
        Forgot Password
      </div>
      <div
        style={{
          textAlign: 'center',
          border: '1px solid #ccc',
          padding: '20px',
          borderRadius: '8px',
          width: '50%',
          boxShadow: '10px 10px 10px 5px grey',
        }}
      >
        <br />
        <br />
        Email <br />
        <input
          type="text"
          id="email"
          value={email}
          onChange={e => setEmail(e.target.value)}
          style={{
            margin: '10px',
            padding: '8px',
            border: '1px solid #ccc',
            borderRadius: '4px',
          }}
        />
        <input
          type="submit"
          onClick={ForgotPassword}
          style={{
            cursor: 'pointer',
          }}
        />

        {showMessage && (
          <div
            style={{
              marginTop: '10px',
            }}
          >
            {message}
          </div>
        )}
      </div>
    </div>
  );
}

export default ForgotPassword;
