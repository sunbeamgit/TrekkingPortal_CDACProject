import { useState, useEffect } from "react";
import React from "react";
import axios from "axios";
import contactus from "../images/contactus.jpeg"

function ContactUs(){
    
        const [packageData,setPackageData]=useState({ teams: [] });;
        useEffect(() => {
            axios
              .get('http://localhost:8080/admin/about')
              .then(response => {
                setPackageData(response.data);
                console.log(response.data);
              })
              .catch(error => {
                console.error('Error fetching package details:', error);
              });
          }, []);
          return(
            <div className="container" >
              <div className="background-image2" style={{height:'700px'}}>
              <center>
                <div className="main-heading">Trek Maharashtra</div><br /><br />
                <div className="sub-heading">Contact Us</div><br />
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
                        <td>
                            <div><img className="my-image2" src={contactus}/></div>
                        </td>
                    </tr>
                </table>
            </center>
              </div>
              
                
            </div>
          )

}

export default ContactUs;