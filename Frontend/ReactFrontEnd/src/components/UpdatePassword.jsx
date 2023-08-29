import React, { useState } from 'react';
import axios from 'axios';
function UpdatePassword(){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [message, setMessage]=useState('');
    const [showMessage, setShowMessage] = useState(false); // New state for showing/hiding message
    const updatePassword = () => {
      const data = {
        email: email,
        password: password,
        newPassword: newPassword
      };
      
      axios.post('http://localhost:7070/trekker/changepassword', data)
        .then(response => {
            console.log(response.data);
          console.log(data);
          console.log('Password updated successfully');
          // Reset the form fields after successful update
            setEmail('');
            setPassword('');
            setNewPassword('');
            if(response.data===('Old password is incorrect')){
                setMessage('Old password is incorrect');
            }
            else{
            setMessage('Password updated successfully.');
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


    return(
        <div>
            <center>
            <div className="updatepass-img ">
          <center> <br /><br /><br /><br /> <br />
          <div className="main-heading">Change Password</div>
          </center>
          </div>
          <br />
          <hr />
          <br />
            </center>
             
            <center>
            <div className='my-div1'>
                <table align='center'>
                    <tr>
                        <td>
                            <b style={{ color: 'red', fontWeight: 'bold' }}> Email</b> <br />
                            <input type="text" value={email} id="email" onChange={e => setEmail(e.target.value)}/>
                        </td>
                     </tr>
                     <br />
                     <tr>
                        <td>
                            <b style={{ color: 'red', fontWeight: 'bold' }}>Old Password</b> <br />
                            <input type="text" value={password} id="password" onChange={e => setPassword(e.target.value)}/>
                        </td>
                    </tr>
                    <br />
                    <tr>
                        <td>
                            <b style={{ color: 'red', fontWeight: 'bold' }}>New Password</b> <br />
                            <input type="text" value={newPassword} id="newPassword" onChange={e => setNewPassword(e.target.value)}/>
                        </td>
                    </tr>
                    <br />
                    <tr>
                       
                       <center> <td>
                            <input  className='btn-primary'type="submit" onClick={updatePassword} value="Update Password"/>
                        </td></center>
                    </tr>
                </table>
                </div>
            {/* Conditional rendering of the message */}
            {showMessage && <div style={{ marginTop: '10px' }}>{message}</div>}
            </center>
                      
        </div>
    );
}
export default UpdatePassword;