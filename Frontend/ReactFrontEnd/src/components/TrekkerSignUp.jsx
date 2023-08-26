import React, { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';

function TrekkerSignUp() {
    const [formdata,setFormdata] =useState({
        firstname:"",lastname:"",
        email:"",birthDate:"",mobileno:"",
        gender:"",
        cityName:"",stateName:"",
        countryName:"",password:"",
       
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
    axios.post("http://localhost:7070/trekker/register",formdata)
    .then((response)=>{setserverresp(response.data.firstname)})
    .catch((error=>{console.log(error)}))
  }

  return (
    <div>
      <form style={{width:"50%",left:"450px",boxShadow:"10px 10px 10px 5px grey",padding:"20px",marginTop:"10px"}}>
        <div className="form-row">
          <div className="form-group col-md-6">
            <label htmlFor="inputfirstname">First Name</label>
            <input type="text" 
            className="form-control" 
            id="firstname"
            name="firstname"
            value={formdata.firstname} 
            onChange={handleChange}
            placeholder="First Name" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputlastname">Last Name</label>
            <input type="text" 
            className="form-control" 
            id="lastname" 
            name="lastname"
            value={formdata.lastname} 
            onChange={handleChange}
            placeholder="Last Name" 
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
            <label htmlFor="inputbirthDate">DOB</label>
            <input type="date" 
            className="form-control" 
            id="birthDate" 
            name="birthDate"
            value={formdata.birthDate} 
            onChange={handleChange}
            placeholder="DOB" />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputmobileno">Phone No.</label>
            <input type="number" 
            className="form-control" 
            id="mobileno" 
            name="mobileno"
            value={formdata.mobileno} 
            onChange={handleChange}
            placeholder="Phone No." />
          </div>


          {/* <div className="form-group col-md-6">
            <label htmlFor="inputAltNo">Alternate No.</label>
            <input type="number" 
            className="form-control" 
            id="altNo" 
            name="altNo"
            value={formdata.altNo} 
            onChange={handleChange}
            placeholder="Alternate No." />
          </div> */}

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
            
         </div>


         <div class="form-group">
          <label for="inputGender">Gender</label>
  
          <select className="form-control"
            id="gender"
            name="gender"
            value={formdata.gender}
            onChange={handleChange}
          >
            <option value="" key="0">SELECT</option>
            <option value="M" key="1">MALE</option>
            <option value="F" key="2">FEMALE</option>
            <option value="O" key="3">OTHER</option>
          </select>
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

export default TrekkerSignUp;