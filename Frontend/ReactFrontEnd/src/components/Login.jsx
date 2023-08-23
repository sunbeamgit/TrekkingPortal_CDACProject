//import { useHistory } from 'react-router-dom';

//import '../node_modules/bootstrap/dist/css/bootstrap.css';
import { useState } from 'react';
//import Test from './Test' 

function Login()
{
    //var history = useHistory();
 
     const [credentials, setCredentials] =
            useState({email:"", password:""});
    const [message, setmessage] = useState("");
        
     var OnTextChanged = (args)=>{
        var copyOfcredentials = {...credentials};
        copyOfcredentials[args.target.name] =
            args.target.value;
        setCredentials(copyOfcredentials);
     }   

     var SignIn = ()=>
     {
      var helper = new XMLHttpRequest();
      helper.onreadystatechange = ()=>{

          if(helper.readyState ==4 && 
              helper.status == 200)
              {
                 // debugger;
                  var result = 
                  JSON.parse(helper.responseText);
               
                  if(result[0].count > 0)
                  {
                       // result[0] bcoz select query returns array in node
                     //set session storage
                    window.sessionStorage.setItem("isUserLoggedIn" , "true");
                    window.sessionStorage.setItem("email" , credentials.email);
                    ShowMessage("Successfull.........!");
                    //navigate to somewhere
                   // history.push("/Test")
                  }
                  else
                  {
                    ShowMessage("Credentials Invalid!");
                    setCredentials({email:"", password:""})
                  }
              }
      }
      
      helper.open("POST", 
                   "http://127.0.0.1:5000/login");
      helper.setRequestHeader("content-type","application/json");
      //debugger;

      var entireCredentialsInString = 
        JSON.stringify(credentials);

      var encoded = window.btoa(entireCredentialsInString);
      
      var dataToSendTServer = 
        {"credentials": encoded};

      helper.send(JSON.stringify(dataToSendTServer));
     }

     var ShowMessage = (msg)=>{
        setmessage(msg);
        setTimeout(() => {
                            setmessage("")
                         }, 3000);
     }
  
    return (<div className="container">
    <form className="form-signin">
      <h2 className="form-signin-heading">
        Please sign in
        </h2>
      
      <input type="email" id="inputEmail" className="form-control" placeholder="Email address" required autofocus name="email"
      value={credentials.email} 
      onChange={OnTextChanged}/>

      <input type="password" id="inputPassword" className="form-control" placeholder="Password" required value={credentials.password}
      name='password' 
      onChange={OnTextChanged}/>

      <input type="button" className='className="btn btn-lg btn-primary btn-block' value="Sign in"
      onClick={SignIn}>
      </input>
      <br></br>
      <div className='alert alert-info' >
        {message}
      </div>
    </form>
  </div>);
}

export default Login;