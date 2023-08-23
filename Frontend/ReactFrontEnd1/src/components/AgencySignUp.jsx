import React from 'react';
import axios from 'axios';

function AgencySignUp() {
  return (
    <div>
      <form style={{width:"50%",left:"450px",position:"absolute",boxShadow:"10px 10px 10px 5px grey",padding:"20px",marginTop:"10px"}}>
        <div className="form-row">
          <div className="form-group col-md-6">
            <label htmlFor="inputAgencyName">Agency Name</label>
            <input type="text" className="form-control" id="inputAgencyName" placeholder="Name" />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputAgencyOwner">Agency Owner Name</label>
            <input type="text" className="form-control" id="inputAgencyOwner" placeholder="Owner Name" />
          </div>
        
          <div className="form-group col-md-6">
            <label htmlFor="inputEmail4">Email</label>
            <input type="email" className="form-control" id="inputEmail4" placeholder="Email" />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputPassword4">Password</label>
            <input type="password" className="form-control" id="inputPassword4" placeholder="Password" />
          </div>
        </div>

        <div className="form-group">
          <label htmlFor="inputAddress">Address</label>
          <input type="text" className="form-control" id="inputAddress" placeholder="1234 Main St" />
        </div>

        <div className="form-group">
          <label htmlFor="inputAddress2">Address 2</label>
          <input type="text" className="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" />
        </div>

        <div className="form-row">
          <div className="form-group col-md-4">
            <label htmlFor="inputState">City</label>
            <select id="inputCity" className="form-control">
              <option selected>Choose...</option>
              <option>...</option>
            </select>
          </div>

          <div className="form-group col-md-4">
            <label htmlFor="inputState">State</label>
            <select id="inputState" className="form-control">
              <option selected>Choose...</option>
              <option>...</option>
            </select>
          </div>

          <div className="form-group col-md-2">
            <label htmlFor="inputZip">Zip</label>
            <input type="text" className="form-control" id="inputZip" />
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
        <button type="submit" className="btn btn-primary">Sign up</button>
      </form>
    </div>
  );
}

export default AgencySignUp;
