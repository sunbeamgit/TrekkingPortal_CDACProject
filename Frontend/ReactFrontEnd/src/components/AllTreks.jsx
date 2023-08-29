import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

function AllTreks() {
  const [trekdetails, setTrekdetails] = useState([]);
  const [trekimages, setTrekimages] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:7070/home/getpartialdata")
      .then(response => {
        console.log(response.data);
        setTrekdetails(response.data);
      })
      .catch(error => {
        console.error(error);
      });

    axios.get("http://localhost:7070/home/gettrekimages")
      .then(response => {
        console.log(response.data);
        setTrekimages(response.data); // Assuming the response.data is an array of image data
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  return (
    <div className="container my-4">
      <h2>-------------------Trek Details----------------</h2>
      {trekdetails.map((trek, index) => (
        <div key={trek.trekName} className="row" style={{ border: "2px solid #b4b4b4", padding: "10px", borderRadius: "10px", width: "50%" }}>
          <div className="col-md-6">
            <h2>{trek.trekName}</h2>
            <p>Location: {trek.location}</p>
            <p>Approx: {trek.trekKilometer}</p>
            <Link to={`/viewdetails/${index}`} className="dropdown-item">View Details</Link>
          </div>
          <div className="col-md-6">
            {/* Check if the image data exists before rendering */}
            {trekimages[index] ? (
              <img src={`data:image/jpeg;base64,${trekimages[index]}`} alt={`Trek Image ${index}`} className="img-fluid" />
            ) : (
              <p>No Image Available</p>
            )}
          </div>
        </div>
      ))}
    </div>
  );
}

export default AllTreks;
