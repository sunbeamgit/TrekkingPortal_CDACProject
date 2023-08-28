import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

function ViewPackage({ onEdit, onDelete }) {
  const [packages, setPackages] = useState([]);
  const history = useHistory();

  const handleChange = (e) => {
    var packagescopy = { ...packages };
    packagescopy[e.target.name] = e.target.value;
    setPackages(packagescopy);
  };

  const fetchPackages = async () => {
    try {
      const response = await axios.get('http://localhost:7070/agency/viewpackage');
      setPackages(response.data);
    } catch (error) {
      console.error('Error fetching packages:', error);
    }
  };

  useEffect(() => {
    fetchPackages();
  }, []);

  const handleDelete = async (packageId) => {
    try {
      // Send delete request to the server
      await axios.delete(`http://localhost:7070/agency/deletepackage/${packageId}`);
      // Update the packages state by filtering out the deleted package
      setPackages(prevPackages => prevPackages.filter(pkg => pkg.id !== packageId));
    } catch (error) {
      console.error(error);
    }
  };

  const handleEdit = (packageId) => {
    history.push(`/agency/updatepackage/${packageId}`);
  };

  return (
    <div className="container">
      <h2>Package List</h2>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Package Name</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {packages.map(singlePackage => (
            <tr key={singlePackage.packageId}>
              <td>{singlePackage.packageName}</td>
              <td>
                <button
                  onClick={() => handleEdit(singlePackage.id)}
                  className="btn btn-primary"
                >
                  Edit
                </button>
              </td>
              <td>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDelete(singlePackage.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewPackage;
