import { useHistory } from 'react-router-dom';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import React, { useEffect } from 'react';

function TrekkerLogin()
{
    const history = useHistory();
    const [credentials, setCredentials] = useState({email:"", password:""});
    const [message, setmessage] = useState("");
    const [isTrekkerLoggedIn, setIsTrekkerLoggedIn] = useState(false);
      
    useEffect(()=>{
      const checkTrekkerLoggedIn = window.sessionStorage.getItem("isTrekkerLoggedIn");
      setIsTrekkerLoggedIn(checkTrekkerLoggedIn === 'true');
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
      console.log("inside trekker sign in")
      // const dataToSend = {
      //   credentials: btoa(JSON.stringify(credentials)),
      // };
  
      axios.post('http://localhost:7070/trekker/signin', credentials, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((response) => {
          const result = response.data;
          if (result.email != null) {
            window.sessionStorage.setItem('isTrekkerLoggedIn', 'true');
            window.sessionStorage.setItem('trekkeremail', credentials.email);
            //ShowMessage('Logged in successfully');
            history.push("/allpackages")
            
          } else {
            //ShowMessage('Invalid credentials!');
            setCredentials({ email: '', password: '' });
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    };
  
    if(!isTrekkerLoggedIn){
    return (
      <div className="container">
        <div className="trekkerlogin-img ">
        <center> <br /><br /><br /><br /> <br />
        <div className="main-heading">Trekker Login</div>
        </center>
        </div>
        <br />
        <hr />
        <div className="row justify-content-center">
          <div className="col-md-6">
            <form>
              <div className="form-outline mb-4">
              <label className="form-label" htmlFor="form2Example1" style={{ color: 'black', fontWeight: 'bold' }}>Email address</label>
             
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
  
              <div className="row mb-4">
                <div className="col d-flex justify-content-center">
                
                </div>
  
                <div className="col">
                <Link to="/forgotpassword">ForgetPassword</Link>
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
    history.push("/AdminDashboard")
  }
}
    export default TrekkerLogin;