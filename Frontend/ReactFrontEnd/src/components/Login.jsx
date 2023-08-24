import { useHistory } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

function Login()
{
    const history = useHistory();
    const [credentials, setCredentials] = useState({email:"", password:""});
    const [message, setmessage] = useState("");
        
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
      console.log("inside sign in")
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
            window.sessionStorage.setItem('isUserLoggedIn', 'true');
            window.sessionStorage.setItem('email', credentials.email);
            //ShowMessage('Logged in successfully');
            history.push("/home")
            
          } else {
            //ShowMessage('Invalid credentials!');
            setCredentials({ email: '', password: '' });
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    };
  
      return (
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-md-6">
              <form>
                <div className="form-outline mb-4">
                  <input 
                  type="email" 
                  id="form2Example1"
                  className="form-control" 
                  value={credentials.email}
                  name="email"
                  onChange={OnTextChanged}
                  />
                  <label className="form-label" htmlFor="form2Example1">Email address</label>
                </div>
    
                <div className="form-outline mb-4">
                  <input 
                  type="password" 
                  id="form2Example2" 
                  className="form-control" 
                  value={credentials.password}
                  name="password"
                  onChange={OnTextChanged}
                  />
                  <label className="form-label" htmlFor="form2Example2">Password</label>
                </div>
    
                <div className="row mb-4">
                  <div className="col d-flex justify-content-center">
                    <div className="form-check">
                      <input className="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                      <label className="form-check-label" htmlFor="form2Example31"> Remember me </label>
                    </div>
                  </div>
    
                  <div className="col">
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
    
    export default Login;