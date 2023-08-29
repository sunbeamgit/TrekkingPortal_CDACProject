import React, { useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { useState, useRef } from 'react';
import axios from 'axios';

function AddTrek() {
  const [formdata, setFormdata] = useState({
    trekName: "", maxAltitude: "",
    trekKilometer: "", region: "",
    location: "", baseCamp: "",
    suitableFor: "", airportName: "",
    railwayStationName: "", seasonName: "",
    gradeCategory: "", history: ""
  });

  const [airport, setAirport] = useState([]);
  const [station, setStation] = useState([]);
  const [season, setSeason] = useState([]);
  const [grade, setGrade] = useState([]);
  const history = useHistory();
  const customFileInputRef = useRef(null);

  useEffect(() => {
      axios.get('http://localhost:7070/home/getairport')
      .then(response => {
        setAirport(response.data.airportName);
      })
      .catch(error => {
        console.error(error);
      });

      axios.get('http://localhost:7070/home/getstation')
      .then(response => {
        setStation(response.data.railwayStation);
      })
      .catch(error => {
        console.error(error);
      });

      axios.get('http://localhost:7070/home/getseason')
      .then(response => {
        setSeason(response.data.seasonName);
      })
      .catch(error => {
        console.error(error);
      });

      axios.get('http://localhost:7070/home/getgrade')
      .then(response => {
        setGrade(response.data.gradeName);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormdata(prevData => ({
      ...prevData,
      [name]: value
    }));
  }

  const handleSubmit = async () => {
    const addtrekDTO = { ...formdata }; // Create a copy of formdata
    const formData = new FormData();
    formData.append('addtrekDTO', new Blob([JSON.stringify(addtrekDTO)], { type: 'application/json' }));
    formData.append('imageFile', customFileInputRef.current.files[0]);

    try {
      const response = await axios.post(
        'http://localhost:7070/admin/addtrek',
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      );

      if (response.data.status) {
        history.push('/AdminDashboard');
      }
    } catch (error) {
      console.log(error);
    }
  }

    return (
      <form style={{width:"50%",left:"450px",boxShadow:"10px 10px 10px 5px grey",padding:"20px",marginTop:"10px"}}>
        <div className="form-row">
          <div className="form-group col-md-6">
            <label htmlFor="inputTrekName">Trek Name</label>
            <input type="text" 
            className="form-control" 
            id="trekName"
            name="trekName"
            value={formdata.trekName} 
            onChange={handleChange}
            placeholder="Trek Name" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputTrekAltitude">Altitude</label>
            <input type="text" 
            className="form-control" 
            id="maxAltitude" 
            name="maxAltitude"
            value={formdata.maxAltitude} 
            onChange={handleChange}
            placeholder="Altitude" 
            />
          </div>
        
          <div className="form-group col-md-6">
            <label htmlFor="inputEmail4">Trekking Kms</label>
            <input type="text" 
            className="form-control" 
            id="trekKilometer" 
            name="trekKilometer"
            value={formdata.trekKilometer} 
            onChange={handleChange}
            placeholder="Kilometers"/>
          </div>

        <div className="form-group col-md-6">
            <label htmlFor="inputPassword4">Region</label>
            <input type="text" 
            className="form-control"
            id="region" 
            name="region"
            value={formdata.region} 
            onChange={handleChange}
            placeholder="Region" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputPassword4">Location</label>
            <input type="text" 
            className="form-control"
            id="location" 
            name="location"
            value={formdata.location} 
            onChange={handleChange}
            placeholder="Location" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputBaseCamp">Base Camp</label>
            <input type="text" 
            className="form-control"
            id="baseCamp" 
            name="baseCamp"
            value={formdata.baseCamp} 
            onChange={handleChange}
            placeholder="Base Camp" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputSuitableFor">Suitable For</label>
            <input type="text" 
            className="form-control"
            id="suitableFor" 
            name="suitableFor"
            value={formdata.suitableFor} 
            onChange={handleChange}
            placeholder="Suitable For" 
            />
          </div>

          <div className="form-group col-md-6">
            <label htmlFor="inputhistory">History</label>
            <input type="text" 
            className="form-control"
            id="history" 
            name="history"
            value={formdata.history} 
            onChange={handleChange}
            placeholder="History" 
            />
          </div>
        </div>

        <div className="form-row">
            <div className="form-group col-md-4">
            <label htmlFor="inputCity">Airport</label>
            <select
                id="inputAirport"
                name="airportName"
                className="form-control"
                value={formdata.airportName} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {airport && airport.map((airportObj, index) => (
            <option key={index} value={airportObj}>{airportObj}</option>
            ))}
            </select>
            </div>

            <div className="form-group col-md-4">
            <label htmlFor="inputStation">Station</label>
            <select
                id="inputStation"
                name="railwayStationName"
                className="form-control"
                value={formdata.railwayStationName} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {station && station.map((stateObj, index) => (
            <option key={index} value={stateObj}>{stateObj}</option>
            ))}
            </select>
            </div>


            <div className="form-group col-md-4">
            <label htmlFor="inputSeason">Season</label>
            <select
                id="inputSeason"
                name="seasonName"
                className="form-control"
                value={formdata.seasonName} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {season && season.map((seasonObj, index) => (
            <option key={index} value={seasonObj}>{seasonObj}</option>
            ))}
            </select>
            </div>

            <div className="form-group col-md-4">
            <label htmlFor="inputGrade">Grade</label>
            <select
                id="inputGrade"
                name="gradeCategory"
                className="form-control"
                value={formdata.gradeCategory} 
                onChange={handleChange}
             >
            <option value="">Choose...</option>
            {grade && grade.map((gradeObj, index) => (
            <option key={index} value={gradeObj}>{gradeObj}</option>
            ))}
            </select>
            </div>
        </div>

        <div>
        <label className="form-label" htmlFor="imageFile">
            Upload an Image
        </label>
        <input type="file" className="form-control" id="customFile" ref={customFileInputRef} />
        </div>
        <button type="button" onClick={handleSubmit} className="btn btn-primary">Add Trek</button>
      </form>
  );
}

export default AddTrek;
