import React, { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';

function AgencySignUp() {
    const [formdata,setFormdata] =useState({
        agencyName :"",agencyOwner:"",
        email:"",password:"",
        cityName:"",stateName:"",
        countryName:""
    })
  const [city,setCity]=useState();
  const [state,setState]=useState();
  const [country,setCountry]=useState();
  const [serverresp,setserverresp] = useState(false);
  
  const handleChange = (e)=>{
    var formdatacopy = {...formdata}
    formdatacopy[e.target.name] = e.target.value;
    setFormdata(formdatacopy);
  }

  useEffect(() => {
    axios.get('http://localhost:7070/home/getcities')
      .then(response => {
        setCity(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });

      axios.get('http://localhost:7070/home/getstates')
      .then(response => {
        setState(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });

      axios.get('http://localhost:7070/home/getcountries')
      .then(response => {
        setCountry(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  const handleSubmit=()=>{
    console.log("Inside handle submit");
    console.log(formdata);
    axios.post("http://localhost:7070/agency/register",formdata)
    .then((response)=>{setserverresp(response.data.agencyName)})
    .catch((error=>{console.log(error)}))
  }

  return (
    <div>
      <form style={{width:"50%",left:"450px",position:"absolute",boxShadow:"10px 10px 10px 5px grey",padding:"20px",marginTop:"10px"}}>
        <div className="form-row">
          <div className="form-group col-md-6">
            <label htmlFor="inputAgencyName">Agency Name</label>
            <input type="text" 
            className="form-control" 
            id="agencyName"
            name="agencyName"
            value={formdata.agencyName} 
            onChange={handleChange}
            placeholder="Name" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputAgencyOwner">Agency Owner Name</label>
            <input type="text" 
            className="form-control" 
            id="agencyOwner" 
            name="agencyOwner"
            value={formdata.agencyOwner} 
            onChange={handleChange}
            placeholder="Owner Name" 
            />
          </div>
        
          <div className="form-group col-md-6">
            <label htmlFor="inputEmail4">Email</label>
            <input type="email" 
            className="form-control" 
            id="email" 
            name="email"
            value={formdata.email} 
            onChange={handleChange}
            placeholder="Email" />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputPassword4">Password</label>
            <input type="password" 
            className="form-control"
            id="password" 
            name="password"
            value={formdata.password} 
            onChange={handleChange}
            placeholder="Password" 
            />
          </div>
        </div>

        <div className="form-row">
            <div className="form-group col-md-4">
            <label htmlFor="inputCity">City</label>
            <select
                id="inputCity"
                name="cityName"
                className="form-control"
                value={formdata.cityName} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {city && city.map((cityObj, index) => (
            <option key={index} value={cityObj.cityName}>{cityObj.cityName}</option>
            ))}
            </select>
            </div>

            <div className="form-group col-md-4">
            <label htmlFor="inputState">State</label>
            <select
                id="inputState"
                name="stateName"
                className="form-control"
                value={formdata.stateName} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {state && state.map((stateObj, index) => (
            <option key={index} value={stateObj.stateName}>{stateObj.stateName}</option>
            ))}
            </select>
            </div>


            <div className="form-group col-md-4">
            <label htmlFor="inputState">Country</label>
            <select
                id="inputCountry"
                name="countryName"
                className="form-control"
                value={formdata.countryName} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {country && country.map((countryObj, index) => (
            <option key={index} value={countryObj.countryName}>{countryObj.countryName}</option>
            ))}
            </select>
            </div>


          {/* <div className="form-group col-md-2">
            <label htmlFor="inputZip">Zip</label>
            <input type="text" className="form-control" id="inputZip" />
          </div> */}
        </div>

        <div className="form-group">
          <div className="form-check">
            <input className="form-check-input" type="checkbox" id="gridCheck" />
            <label className="form-check-label" htmlFor="gridCheck">
              Check me out
            </label>
          </div>
        </div>
        <button type="button" onClick={handleSubmit} className="btn btn-primary">Sign up</button>
      </form>
    </div>
  );
}

export default AgencySignUp;




        {/* <div className="form-group">
          <label htmlFor="inputAddress">Address</label>
          <input type="text" className="form-control" id="inputAddress" placeholder="1234 Main St" />
        </div>

        <div className="form-group">
          <label htmlFor="inputAddress2">Address 2</label>
          <input type="text" className="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" />
        </div> */}