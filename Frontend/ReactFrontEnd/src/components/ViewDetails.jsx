import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom'; 

function ViewDetails({ match }) {
    const [packageData, setPackageData] = useState({});
    const history = useHistory(); 
    useEffect(() => {
      const packageDetailsId = match.params.id; 
  
      axios
        .put(`http://localhost:7070/trekker/viewDetails/${packageDetailsId}`)
        .then(response => {
          setPackageData(response.data);
          console.log(response.data);
        })
        .catch(error => {
          console.error('Error fetching package details:', error);
        });
    }, [match.params.id]);

    const handlebooking = packageId => {
        history.push(`/getbookings/${packageData?.id}`);
      };

    return (
      <div align="center">
        <div style={{ width: '600px', border: '1px solid red', padding: '10px' }}>
          <img src={`data:image/jpg;base64,${packageData.imagePath}`} alt={`Trek Image`} className="img-fluid" />
        </div>
          <div >           
  
            <div className="form-group col-md-6">
              <table className="my-table">
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Package Name:</span></b>:<br></br> {packageData.packageName}</td>
                </tr>
                <tr>
                    <td colSpan={2}><b><span style={{ color: 'red' }}>Location  </span></b>:<br></br> {packageData.location}</td>
                    <td colSpan={2}><b><span style={{ color: 'red' }}>Region </span></b>:<br></br> {packageData.region}</td>
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Max Altitude</span></b> :<br></br> {packageData.maxAltitude} FT</td>
                    <td><b><span style={{ color: 'red' }}>Grade </span></b> :<br></br> {packageData.grade}</td>
                    <td colSpan={2}><b><span style={{ color: 'red' }}>Cost Per Person</span> </b> :<br></br> ₹ {packageData?.priceperPerson} /person</td>
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Duration </span> </b> :<br></br> {packageData.duration}</td>
                    <td><b><span style={{ color: 'red' }}>Trekking (km) </span></b> :<br></br> {packageData.trekKilometer} Km.</td>
                    <td colSpan={2}><button type="submit" className="btn btn-danger" onClick={() => handlebooking(packageData?.id)}>Book Now</button></td>
                </tr>
              </table>
            </div>
          
            <div className="form-group col-md-6">
            <table className="my-table">
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Overview : Trek Information</span></b></td>
                </tr>
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Trek Name </span></b>:<br></br> {packageData.trekName}</td>
                </tr>
                
                <tr>
                    <td><b><span style={{ color: 'red' }}>Base Camp </span></b> :<br></br> {packageData.baseCamp}</td>
                    <td><b><span style={{ color: 'red' }}>Guide Name </span></b> :<br></br> {packageData.guide?.guideName}</td>
                    <td><b><span style={{ color: 'red' }}>Airport </span></b> :<br></br> {packageData.airport}</td>
                    <td><b><span style={{ color: 'red' }}>Railway Station </span></b> :<br></br> {packageData.railwayStation}</td>                     
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Sutaible For </span></b> :<br></br> {packageData.suitableFor}</td>
                    <td><b><span style={{ color: 'red' }}>Best Season</span></b> :<br></br> {packageData.season}</td>
                    <td><b><span style={{ color: 'red' }}>Stay </span></b> :<br></br> {packageData?.stay}</td>
                    <td><b><span style={{ color: 'red' }}>Meals  </span></b>:<br></br> {packageData?.meals}</td> 
                </tr>
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Date: {packageData.date}</span></b></td>
                </tr>
              </table>
            </div>

          </div>
  
          <div className="my-div" style={{ backgroundColor: 'rgba(255, 0, 0, 0.3)', overflow: 'auto', paddingLeft: '20px' }}>
              <b><span style={{ color: 'red' }}>Itinerary </span></b> <br />
              <p style={{ textAlign: 'left', fontSize: '16px', lineHeight: '1.5' }}>{packageData.itinerary}</p>
          </div>
          </div>
    );
}
  
export default ViewDetails;
  