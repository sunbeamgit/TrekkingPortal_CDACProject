import { useHistory } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

function AdminLogin()
{
    const history = useHistory();
    const [credentials, setCredentials] = useState({email:"", password:""});
    const [message, setmessage] = useState("");
    const [isAdminLoggedIn, setIsAdminLoggedIn] = useState(false);
        
    useEffect(()=>{
      const checkAdminLoggedIn = window.sessionStorage.getItem("isAdminLoggedIn");
      setIsAdminLoggedIn(checkAdminLoggedIn === 'true');;
    },[])

    var OnTextChanged = (args)=>{
        var copyOfcredentials = {...credentials};
        copyOfcredentials[args.target.name] = args.target.value;
        setCredentials(copyOfcredentials);
    }   

    const signIn = () => {
      // const dataToSend = {
      //   credentials: btoa(JSON.stringify(credentials)),
      // };
      axios.post('http://localhost:7070/admin/signin', credentials, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((response) => {
          const result = response.data;
          if (result.email != null) {
            window.sessionStorage.setItem('isAdminLoggedIn', 'true');
            window.sessionStorage.setItem('adminemail', credentials.email);
            history.push("/admindashboard")
          } else {
            setCredentials({ email: '', password: '' });
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    };
  
    if(!isAdminLoggedIn){
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
              <button type="button" 
                className="btn btn-primary btn-block mb-4"
                onClick={signIn}
              >Sign in</button>
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
    
export default AdminLogin;