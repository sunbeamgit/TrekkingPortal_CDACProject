import { useState, useEffect } from "react";
import React from "react";
import axios from "axios";

function ContactUs() {
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
    <div className="container">
      <div className="contactus-img ">
          <center> <br /><br /><br /><br />
          <div className="main-heading">Contact Us</div>
          </center>
        </div>
        <br /><br /><br /><br />
      <div >
        <center>
        <table className="my-table1">
            <tr>
              <td>
                <div>
                  <center>
                    <b className="sub-heading">Mobile</b>:{packageData.mobile} <br />
                    <b className="sub-heading">Email</b>:{packageData.email} <br />
                    <b className="sub-heading">Instagram</b>:{packageData.insta} <br />
                  </center>
                </div>
              </td>
              <td> <center>
              <div className="contactus-img2">
                </div>
              </center>
                
              
              </td>
            </tr>
          </table>
        </center>
      </div>
    </div>
  );
}

export default ContactUs;
