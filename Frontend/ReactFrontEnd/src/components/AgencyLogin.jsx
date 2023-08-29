import { useHistory } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';
import React, { useEffect } from 'react';

function AgencyLogin()
{
    const history = useHistory();
    const [credentials, setCredentials] = useState({email:"", password:""});
    const [message, setmessage] = useState("");
    const [isAgencyLoggedIn, setIsAgencyLoggedIn] = useState(false);
    
    useEffect(()=>{
      const checkAgencyLoggedIn = window.sessionStorage.getItem("isAgencyLoggedIn");
      setIsAgencyLoggedIn(checkAgencyLoggedIn === 'true');
    },[])

    var OnTextChanged = (args)=>{
        var copyOfcredentials = {...credentials};
        copyOfcredentials[args.target.name] = args.target.value;
        setCredentials(copyOfcredentials);
     }   

    // var ShowMessage = (msg)=>{
    //     setmessage(msg);
    //     setTimeout(() => {
    //                         setmessage("")
    //                      }, 3000);
    // }

    const signIn = () => {
      console.log("inside agency sign in")
      // const dataToSend = {
      //   credentials: btoa(JSON.stringify(credentials)),
      // };
  
      axios.post('http://localhost:7070/agency/signin', credentials, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((response) => {
          const result = response.data;
          if (result.email != null) {
            window.sessionStorage.setItem('isAgencyLoggedIn', 'true');
            window.sessionStorage.setItem('agencyemail', credentials.email);
            //ShowMessage('Logged in successfully');
            history.push("/agencydashboard")
            
          } else {
            //ShowMessage('Invalid credentials!');
            setCredentials({ email: '', password: '' });
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    };
  
    if(!isAgencyLoggedIn){
    return (
      <div className="container">
        <div className="agencylogin-img ">
        <center> <br /><br /><br /><br /> <br />
        <div className="main-heading">Agency Login</div>
        </center>
        </div>
        <br />
        <hr />
        <div className="row justify-content-center">
          <div className="col-md-6"> <br /><br />
            <form>
              <div className="form-outline mb-4">
              <label className="form-label" htmlFor="form2Example2" style={{ color: 'black', fontWeight: 'bold' }}>Email address</label>

                <input 
                type="email" 
                id="form2Example1"
                className="form-control" 
                value={credentials.email}
                name="email"
                onChange={OnTextChanged}
                />
              </div>
  
              <div className="form-outline mb-4">
              <label className="form-label" htmlFor="form2Example2" style={{ color: 'black', fontWeight: 'bold' }}>Password</label>

                <input 
                type="password" 
                id="form2Example2" 
                className="form-control" 
                value={credentials.password}
                name="password"
                onChange={OnTextChanged}
                />
              </div>
  
              <div className="row mb-4" >
              <div className="col d-flex justify-content-center">
                
                </div>
  
                  
                <div className="col" >
                 <a href="#!">Forgot password?</a>
                </div>
              </div>
  
              <button type="button" 
              className="btn btn-primary btn-block mb-4"
              onClick={signIn}
              >Sign in</button>
  
              <div className="text-center">
                <p>Not a member? <a href="#!">Register</a></p>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
    }
    else{
      history.push("/AgencyDashboard")
    }
}
    
    export default AgencyLogin;