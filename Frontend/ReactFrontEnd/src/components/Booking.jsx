import { useHistory } from 'react-router-dom'; 
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function Booking({ match }) {
    debugger;
        const { packageId } = useParams();
        const [packageData, setPackageData] = useState({});
        const [no, setNo] = useState(0); // Initialize 'no' state
        const history = useHistory(); // Initialize useHistory
        const packageDetailsId = match.params.id; // Use 'id' instead of 'packageDetailsId'
    useEffect(() => {
        debugger;
      
        // const [packageData, setPackageData] = useState({});
        axios
          .post(`http://localhost:7070/trekker/viewDetails/${packageDetailsId}`)
          .then(response => {
            setPackageData(response.data);
            console.log(response.data);
          })
          .catch(error => {
            console.error('Error fetching package details:', error);
          });
      }, [packageId]);

      const handlingpayment=(payment) =>{
        debugger
        console.log(payment)
        console.log(no)
        history.push(`/paymentdetails/${payment}/${no}`);
      }

      const handleNoChange=(event)=>{
        setNo(event.target.value);
      }
     return (
        <div align="center" className='background-image1' >
            <div className="my-div">
            <b><span style={{ color: 'red' }}>You Booked<br></br> (Trek Name) </span></b>:<br></br> {packageData.trekDetails?.trekName}<br></br><br></br>
            <b><span style={{ color: 'red' }}>Cost Per Person</span> </b> :<br></br> {packageData?.priceperPerson}
            </div>

            <div className="my-div">
              <p>Enter Nunber Of Participent :</p>
            <input type='Number' id='no' value={no} onChange={handleNoChange} placeholder="Enter no of participant"></input>
            </div>

            <div className="my-div">
            <button type="submit" className="btn btn-danger" onClick={() => handlingpayment(packageData?.priceperPerson)}>Proceed</button>
            </div>
                    
    
              
        </div>
      );
}

export default Booking;