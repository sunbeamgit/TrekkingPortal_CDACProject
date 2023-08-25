import React, { useEffect, useState } from 'react';
import { Link, Route } from 'react-router-dom'
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
        setTrekimages(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  return (
    <div className="container my-4">
      {trekdetails.map((trek, index) => (
        <div key={trek.trekName} className="row" style={{ border: "2px solid #b4b4b4", padding: "10px", borderRadius: "10px", width: "50%" }}>
          <div className="col-md-6">
            <h2>{trek.trekName}</h2>
            <p>Location: {trek.location}</p>
            <p>Appox: {trek.trekKilometer}</p>
            <Link to="/viewdetails/1" class="dropdown-item">View Details</Link>
          </div>
          <div className="col-md-6">
            {/* Render images separately using another map loop */}
            {trekimages[index] && (
              <img src={`data:image/jpg;base64,${trekimages[index]}`} alt={`Trek Image ${index}`} className="img-fluid" />
            )}
          </div>
        </div>
      ))}
    </div>
  );
}

export default AllTreks;
