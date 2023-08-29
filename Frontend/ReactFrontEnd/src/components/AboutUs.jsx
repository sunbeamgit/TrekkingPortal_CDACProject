import React, { useState, useEffect } from "react";
import axios from "axios";
import gmailimag from "../images/gmail.png";
import instaimag from "../images/insta.png";
import shweta from "../images/shwetac.jpeg";
import shreya from "../images/shreyas.jpeg";
import harshada from "../images/harshadam.jpeg";

function AboutUs() {
  const [packageData, setPackageData] = useState({ teams: [] });
  
  useEffect(() => {
    axios
      .get('http://localhost:7070/home/about')
      .then(response => {
        setPackageData(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching package details:', error);
      });
  }, []);

  return (
    <div>
      <center>
      <div className="aboutus-img">
      <center> <br /><br /><br /><br /> <br />
          <div className="main-heading">About Us</div>
          </center>
      </div>
      </center>

      <div align="center">
        <div className="my-div1">
          <div className="redish-heading">Maharashtra Trekking</div><br />
          <div className="justify-paragraph">
            {packageData.corevalue}
          </div>
        </div>

        <div style={{ display: 'inline-block', padding: '50px 100px', marginRight: '50px'}}>
          <img className="div-imagepngteam" style={{margin: '40px'}} src={harshada} alt="Harshada" />
          <img className="div-imagepngteam" style={{margin: '40px'}} src={shweta} alt="Shweta" />
          <img className="div-imagepngteam" style={{margin: '40px'}} src={shreya} alt="Shreya" />
        </div><br />
        
        <div align="center">
          {packageData.teams.map((item, index) => (
            <div key={index} style={{ display: 'inline-block', padding: '10px 20px' }}>
              <table className="my_table" align="center">
                <tbody>
                  <tr>
                    <td align="center" colSpan={2}><b>{item.name}</b></td>
                  </tr>
                  <tr>
                    <td><img className="my-image1" src={gmailimag} alt="Gmail" /></td>
                    <td align="center">{item.email}</td>
                  </tr>
                  <tr>
                    <td><img className="div-imagepng" src={instaimag} alt="Instagram" /></td>
                    <td align="center">{item.insta}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          ))}
        </div>

        <div className="my-div1">
          <div className="redish-heading"><b>Core Value</b></div><br />
          <div className="justify-paragraph">
          {packageData.info} 
          </div> 
        </div>
      </div>
    </div>
  );
}

export default AboutUs;
