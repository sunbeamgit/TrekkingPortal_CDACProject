import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

function AddPackage() {
  const history = useHistory();

  const [formData, setFormData] = useState({
    packageName: '',
    priceperPerson: '',
    duration: '',
    meals: '',
    stay: '',
    date: '',
    guide: '',
    trekDetails: '',
    itinerary: '',
    status: '',
    serviceType: ''
  });

  const [guideName, setGuideName] = useState([]);
  const [trekName, setTrekName] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:7070/home/getGuide')
      .then(response => {
        setGuideName(response.data);
      })
      .catch(error => {
        console.error(error);
      });

    axios.get('http://localhost:7070/home/getTrek')
      .then(response => {
        setTrekName(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        'http://localhost:7070/agency/addpackage',
        formData,
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );

      if (response.data.packageName) {
        history.push('/AgencyDashboard'); // Redirect on success
      }
    } catch (error) {
      console.error('Error adding package:', error);
      // Handle error here (e.g., show an error message to the user)
    }
  };

  return (
    <div>
      <form style={{ width: "50%", left: "450px", boxShadow: "10px 10px 10px 5px grey", padding: "20px", marginTop: "10px" }}>
        <div className="form-row">
          <div className="form-group col-md-6">
            <label htmlFor="packageName">Package Name</label>
            <input type="text"
              className="form-control"
              id="packageName"
              name="packageName"
              value={formData.packageName}
              onChange={handleInputChange}
              placeholder="Package Name"
            />
          </div>
          <div className="form-group col-md-6">
            <label htmlFor="priceperPerson">Price per Person</label>
            <input type="number"
              className="form-control"
              id="priceperPerson"
              name="priceperPerson"
              value={formData.priceperPerson}
              onChange={handleInputChange}
              placeholder="Price per Person"
            />
          </div>
          <div className="form-group col-md-6">
            <label htmlFor="duration">Duration</label>
            <input type="number"
              className="form-control"
              id="duration"
              name="duration"
              value={formData.duration}
              onChange={handleInputChange}
              placeholder="Duration"
            />
          </div>
          <div className="form-group col-md-6">
            <label htmlFor="meals">Meals</label>
            <input type="text"
              className="form-control"
              id="meals"
              name="meals"
              value={formData.meals}
              onChange={handleInputChange}
              placeholder="Meals"
            />
          </div>
          <div className="form-group col-md-6">
            <label htmlFor="stay">Stay</label>
            <input type="text"
              className="form-control"
              id="stay"
              name="stay"
              value={formData.stay}
              onChange={handleInputChange}
              placeholder="Stay"
            />
          </div>
          <div className="form-group col-md-6">
            <label htmlFor="date">Date</label>
            <input type="date"
              className="form-control"
              id="date"
              name="date"
              value={formData.date}
              onChange={handleInputChange}
              placeholder="Date"
            />
          </div>
          <div className="form-group col-md-6">
            <label htmlFor="itinerary">Itinerary</label>
            <input type="text"
              className="form-control"
              id="itinerary"
              name="itinerary"
              value={formData.itinerary}
              onChange={handleInputChange}
              placeholder="Itinerary"
            />
          </div>
          <div className="form-group col-md-4">
            <label htmlFor="status">Status</label>
            <select className="form-control"
              id="status"
              name="status"
              value={formData.status}
              onChange={handleInputChange}
            >
              <option value="">SELECT</option>
              <option value="OPEN">OPEN</option>
              <option value="CLOSE">CLOSE</option>
            </select>
          </div>
          <div className="form-group col-md-4">
            <label htmlFor="serviceType">Service Type</label>
            <select className="form-control"
              id="serviceType"
              name="serviceType"
              value={formData.serviceType}
              onChange={handleInputChange}
            >
              <option value="">SELECT</option>
              <option value="STANDARD">STANDARD</option>
              <option value="PREMIUM">PREMIUM</option>
            </select>
          </div>
          <div className="form-group col-md-4">
            <label htmlFor="guide">Guide</label>
            <select
              id="guide"
              name="guide"
              className="form-control"
              value={formData.guide}
              onChange={handleInputChange}
            >
              <option value="">Choose...</option>
              {guideName.map((guideObj, index) => (
                <option key={index} value={guideObj.guideName}>
                  {guideObj.guideName}
                </option>
              ))}
            </select>
          </div>
          <div className="form-group col-md-4">
            <label htmlFor="trekDetails">Trek Details</label>
            <select
              id="trekDetails"
              name="trekDetails"
              className="form-control"
              value={formData.trekDetails}
              onChange={handleInputChange}
            >
              <option value="">Choose...</option>
              {trekName.map((trekDetailsObj, index) => (
                <option key={index} value={trekDetailsObj.trekName}>
                  {trekDetailsObj.trekName}
                </option>
              ))}
            </select>
          </div>
        </div>
        <button type="button" onClick={handleSubmit} className="btn btn-primary">Add Package</button>
      </form>
    </div>
  );
}

export default AddPackage;