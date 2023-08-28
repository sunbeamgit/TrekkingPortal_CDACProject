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
        <div>
            <div className="container my-4">
                {packages.map((pkg, index) => (
                    <div key={pkg.packageName} className="row mb-4 p-2 border rounded" style={{ maxWidth: "700px" }}>
                        <div className="col-md-6">
                            <img src={`data:image/jpg;base64,${pkg.packageImage}`} alt={`Trek Image`} className="img-fluid" />
                        </div>
                        <div className="col-md-6">
                            <div className="d-flex flex-column justify-content-center h-100">
                                <h2 style={{ fontSize: "24px", fontWeight: "bold", color: "#333" }}>{pkg.packageName}</h2>
                                <p style={{ fontSize: "16px", marginBottom: "5px", color: "#666" }}>Location: {pkg.location}</p>
                                <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Approx: {pkg.distance} km</p>
                                <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Duration: {pkg.duration}</p>
                                <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Grade: {pkg.grade}</p>
                                <p style={{ fontSize: "14px", marginBottom: "5px", color: "#888" }}>Altitude: {pkg.altitude}</p>
                                <Link to={`/viewdetails/${pkg.id}`} className="btn btn-primary">View Details</Link>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default AllPackages;
