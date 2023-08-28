import { useLocation } from 'react-router-dom';
import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom'; 

function ForgotPassword2() {
  const [enteredOTP, setEnteredOTP] = useState('');
  const location = useLocation();
  const { email } = location.state;
  const history = useHistory();
  const [message, setMessage] = useState('');
  const [showMessage, setShowMessage] = useState(false);

  const handleForgotPassword2 = () => {
    const data = {
      email: email,
      enteredOTP: enteredOTP
    };

    axios.post('http://localhost:7070/api/verify-otp', data)
      .then(response => {
        console.log(response.data.message);
        console.log(email);
        console.log('Password updated successfully');

        if (response.data.message === 'OTP verification succeeded!') {
            debugger;
            history.push('/forgotpassword3', { email: email });
            debugger;
        } else {
          setMessage('Invalid OTP detected');
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
    <div>
      <div>
        <table className='my-table1'>
          <tr>
            <td>
              Email <br />
              {email}
            </td>
          </tr>
          <tr>
            <td>
              Otp <br />
              <input type="number" id="enteredOTP" value={enteredOTP} onChange={e => setEnteredOTP(e.target.value)} />
            </td>
          </tr>
          <tr>
            <td><input type="submit" onClick={handleForgotPassword2}/></td>
          </tr>
        </table>
         {/* Conditional rendering of the message */}
         {showMessage && <div style={{ marginTop: '10px' }}>{message}</div>}
      </div>
    </div>
  );
}

export default ForgotPassword2;
