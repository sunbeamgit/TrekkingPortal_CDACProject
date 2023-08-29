import { useState, useEffect } from "react";
import React from "react";
import axios from "axios";
import instaimag from "../images/insta.png"
function Footer(){
    const [packageData,setPackageData]=useState({ teams: [] });;
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
          return(
            
                <div className="parallelogram-footer">
                <br /><br />
                <table width={'100%'}>
                    <tr >
                        <td  align="center">
                            <div className="sub-heading"><b>Contact Us</b></div>
                            <div className="footer-heading">mobile:</div>{packageData.mobile} <br />
                        <div className="footer-heading">Email:</div>{packageData.email}</td>
                        <td  align="center"><div className="sub-heading">Visit us on<br /> Social WebSite</div> 
                        <div className="footer-heading"><img  className="div-imagepng" src={instaimag}/>Instagram:</div>{packageData.insta}
                        </td>
                    </tr>
                    
                </table>
            </div>
            
            
          )
}

export default Footer;