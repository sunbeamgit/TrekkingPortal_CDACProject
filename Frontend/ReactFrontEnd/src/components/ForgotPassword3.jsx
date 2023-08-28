import { useLocation } from 'react-router-dom';
import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom'; 

function ForgotPassword3(){
    const [newPassword, setNewPassword] = useState('');
    const location = useLocation();
    const { email } = location.state;
    const history = useHistory();
    const [message, setMessage] = useState('');
    const [showMessage, setShowMessage] = useState(false);


    const handleForgotPassword3 = () => {
        debugger;
        const data = {
          email: email,
          newPassword: newPassword
        };
    
        axios.post('http://localhost:7070/api/update-password', data)
          .then(response => {
            console.log(response.data.message);
            console.log(email);
            console.log('Password updated successfully');
            debugger;
            if (response.data === 'Password updated successfully') {
                debugger;
                history.push('/alltreks');
                debugger;
            } else {
              setMessage('Password not updated successfully');
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
        }
    return(
        <div>
      <div>
        <table className='my-table1'>         
          <tr>
            <td>
                 New Password <br />
                    <input
                    type="text"
                    id="newPassword"
                    value={newPassword}
                    onChange={e => setNewPassword(e.target.value)}
    />
              </td>
          </tr>
          <tr>
            <td><input type="submit" onClick={handleForgotPassword3}/></td>
          </tr>
        </table>
         {/* Conditional rendering of the message */}
         {showMessage && <div style={{ marginTop: '10px' }}>{message}</div>}
      </div>
    </div>
    )

}
export default ForgotPassword3;