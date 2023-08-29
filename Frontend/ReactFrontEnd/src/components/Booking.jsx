import { useHistory } from 'react-router-dom'; 
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function Booking({ match }) {
    //debugger;
    const { packageId } = useParams();
    const [packageData, setPackageData] = useState({});
    const [no, setNo] = useState(0); 
    const history = useHistory();
    const packageDetailsId = match.params.id; 
    
    useEffect(() => {
        //debugger;
        axios
          .put(`http://localhost:7070/trekker/viewDetails/${packageDetailsId}`)
          .then(response => {
            setPackageData(response.data);
            console.log(response.data);
          })
          .catch(error => {
            console.error('Error fetching package details:', error);
          });
      }, [packageId]);

      const handlingpayment=(payment) =>{
        //debugger
        console.log(payment)
        console.log(no)
        history.push(`/paymentdetails/${payment}/${no}/${packageDetailsId}`);
      }

      const handleNoChange=(event)=>{
        setNo(event.target.value);
      }
     return (
      <div>
      <center>
      <div className="booknow-img">
      <center> <br /><br /><br /><br /> <br />
          <div className="main-heading">Book Now</div>
          </center>
      </div>
      </center>

      <div style={{ display: 'flex', justifyContent: 'center', height: '100vh' }}>
      <div className="my-div" style={{ backgroundColor: 'rgba(255, 0, 0, 0.3)', padding: '20px', textAlign: 'center', borderRadius: '10px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.2)' }}>
        <b><span style={{ color: 'red' }}>You Booked<br /></span></b><br /> {packageData.packageName}<br /><br />
        <b><span style={{ color: 'red' }}>Cost Per Person</span></b>:<br /> ₹ {packageData?.priceperPerson}/person
        <p>Enter Number Of Participants:</p>
        <input type='number' id='no' value={no} onChange={handleNoChange} placeholder="Enter no of participants" style={{ padding: '5px', width: '100%', border: '1px solid #ccc', borderRadius: '5px' }} />
        <button type="submit" className="btn btn-danger" onClick={() => handlingpayment(packageData?.priceperPerson)}>Proceed</button>
      </div>
    </div>
    </div>
    );
}

export default Booking;