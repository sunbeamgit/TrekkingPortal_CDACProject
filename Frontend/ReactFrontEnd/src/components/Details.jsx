import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom'; 

function Details({ match }) {
    const [packageData, setPackageData] = useState({});
    const history = useHistory(); // Get the history object
    useEffect(() => {
      const packageDetailsId = match.params.id; // Use 'id' instead of 'packageDetailsId'
  
      axios
        .post(`http://localhost:7070/trekker/viewDetails/${packageDetailsId}`)
        .then(response => {
          setPackageData(response.data);
          console.log(response.data);
        })
        .catch(error => {
          console.error('Error fetching package details:', error);
        });
    }, [match.params.id]);

    const handlebooking = packageId => {
        debugger;
        history.push(`/getbookings/${packageData?.id}`);
      };

    return (
      <div align="center">
        
          <div className="my-div">
            image

          </div>
          <div >           
  
            <div className="form-group col-md-6">
              <table className="my-table">
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Trek Name </span></b>:<br></br> {packageData.trekDetails?.trekName}</td>
                </tr>
                <tr>
                    <td colSpan={2}><b><span style={{ color: 'red' }}>Location  </span></b>:<br></br> {packageData.trekDetails ?.location}</td>
                    <td colSpan={2}><b><span style={{ color: 'red' }}>Region </span></b>:<br></br> {packageData.trekDetails ?. region}</td>
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Max Altitude</span></b> :<br></br> {packageData .trekDetails?.maxAltitude}</td>
                    <td><b><span style={{ color: 'red' }}>Grade </span></b> :<br></br> {packageData .trekDetails?.grade.gradeCategory}</td>
                    <td colSpan={2}><b><span style={{ color: 'red' }}>Cost Per Person</span> </b> :<br></br> {packageData?.priceperPerson}</td>
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Duration </span> </b> :<br></br> {packageData.trekDetails?.season.seasonDuration}</td>
                    <td><b><span style={{ color: 'red' }}>Trekking (km) </span></b> :<br></br> {packageData.trekDetails?.trekKilometer}</td>
                    <td colSpan={2}><button type="submit" className="btn btn-danger" onClick={() => handlebooking(packageData?.id)}>Book Now</button></td>
                </tr>
              </table>
            </div>
          
            <div className="form-group col-md-6">
            <table className="my-table">
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Overview: </span></b></td>
                </tr>
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Trek Information :</span></b></td>
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Base Camp </span></b> :<br></br> {packageData.trekDetails?.baseCamp}</td>
                    <td><b><span style={{ color: 'red' }}>Guide Name </span></b> :<br></br> {packageData.guide?.guideName}</td>
                    <td><b><span style={{ color: 'red' }}>Airport </span></b> :<br></br> {packageData.trekDetails?.airport?.airportName}</td>
                    <td><b><span style={{ color: 'red' }}>Railway Station </span></b> :<br></br> {packageData.trekDetails?.railwayStation?.railwaystationName}</td>                     
                </tr>
                <tr>
                    <td><b><span style={{ color: 'red' }}>Sutaible For </span></b> :<br></br> {packageData.trekDetails?.suitableFor}</td>
                    <td><b><span style={{ color: 'red' }}>Best Season</span></b> :<br></br> {packageData.trekDetails?.season?.seasonName}</td>
                    <td><b><span style={{ color: 'red' }}>Stay </span></b> :<br></br> {packageData?.stay}</td>
                    <td><b><span style={{ color: 'red' }}>Meals  </span></b>:<br></br> {packageData?.meals}</td> 
                </tr>
                <tr>
                    <td colSpan={4}><b><span style={{ color: 'red' }}>Date: {packageData.date}</span></b></td>
                </tr>
              </table>
            </div>

          </div>
  
          <div className="my-div"><b><span style={{ color: 'red' }}>Itinerary </span></b> <br></br>
          {packageData.itinerary}
          </div>
  
          </div>
    );
  }
  
  export default Details;
  