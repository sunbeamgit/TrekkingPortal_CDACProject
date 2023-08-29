import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

function AllPackages() {
    const [packages, setPackages] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:7070/home/getallpackage")
            .then(response => {
                console.log(response.data);
                setPackages(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    return (
        <div className="allpackages-container">
            <div className='allpackages-img'>
                <h3 style={{ fontSize: "30px", fontWeight: "bold", color: "#FFFFFF", textAlign: "center", margin: "20px 0" }}>
                    प्रतिपच्चंद्रलेखेव वर्धिष्णुर्विश्ववन्दिता। शाहसुनो शिवस्यैषा मुद्रा भद्राय राजते॥
                </h3>
            </div>
            <div className="container my-4">
                <div className="d-flex flex-wrap justify-content-between">
                    {packages.map((pkg, index) => (
                        <div
                            key={pkg.packageName}
                            className="mb-4 p-2 border rounded"
                            style={{
                                flex: "0 0 calc(33.33% - 20px)", // Adjust width and margin
                                boxSizing: "border-box", // Include padding and border in width
                                padding: "10px",
                                backgroundColor: "#fff",
                            }}
                        >
                            <img src={`data:image/jpg;base64,${pkg.packageImage}`} alt={`Trek Image`} className="img-fluid" />
                            <h2 style={{ fontSize: "24px", fontWeight: "bold", color: "#333", margin: "10px 0" }}>{pkg.packageName}</h2>
                            <p style={{ fontSize: "16px", marginBottom: "5px", color: "#666" }}>Location: {pkg.location}</p>
                            <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Approx: {pkg.distance} km</p>
                            <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Duration: {pkg.duration}</p>
                            <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Grade: {pkg.grade}</p>
                            <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Altitude: {pkg.altitude}</p>
                            <Link to={`/viewdetails/${pkg.id}`} className="btn btn-primary">View Details</Link>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
    
}

export default AllPackages;
