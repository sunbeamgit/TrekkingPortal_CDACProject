import { useState, useEffect } from "react";
import React from "react";
import axios from "axios";
import gmailimag from "../images/gmail.png"
import instaimag from "../images/insta.png"
import shweta from "../images/shwetac.jpeg"
import shreya from "../images/shreyas.jpeg"
import harshada from "../images/harshadam.jpeg"
function AboutUs(){
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

      <div >
        <div className="content">
        <div align="center">
        <div className="my-div1">
          <div className="redish-heading ">Maharashtra Trekking</div><br />
          <div className="justify-paragraph" >
          {packageData.info} Maharashtra, India, is a paradise for fort trekking aficionados. 
        Its diverse landscape offers an array of historic and scenic forts to explore. The Sahyadri
         mountain range hosts iconic treks like Harishchandragad, famous for its ancient caves, and 
         Rajmachi with its twin forts, showcasing architectural marvels. In the Western Ghats,
          Bhimashankar offers a spiritual trek amidst lush forests, while Kalsubai, Maharashtra's 
          highest peak, promises breathtaking views. The Konkan coast reveals Torna Fort's historical charm 
          and Ratangad's captivating beauty. Kokan's Tung Fort entices with misty vistas, and Pratapgad resonates 
          with Maratha history. Lonavala-Khandala boasts Lohagad and Visapur Forts for history buffs. Adventure seekers
           find solace at Duke's Nose for rock climbing. Matheran unfolds serene treks to Panorama Point. Prepare for diverse 
           terrain, varying difficulty levels, and changing weather conditions. Respect nature, stay hydrated, and 
           adhere to guidelines. Local guides or trekking groups enhance safety and provide insights into 
           Maharashtra's history and culture. Maharashtra's forts stand as testimonials to its rich heritage, 
           inviting trekkers to embark on unforgettable journeys.

       </div>
    </div>
    
    <div style={{ display: 'inline-block', padding: '50px 100px',marginRight: '50px'}}>
      <img className="div-imagepngteam" style={{margin:'40px' }} src={harshada}></img>
      <img className="div-imagepngteam" style={{margin:'40px' }} src={shweta}></img>
      <img className="div-imagepngteam" style={{margin:'40px' }}src={shreya}></img>
    </div><br></br>
    <div align="center">
  {packageData.teams.map((item, index) => (
    <div key={index } style={{ display: 'inline-block', padding: '10 20px' }}>
      <table className="my_table" align="center">
          <tbody>
            <tr>
              <td align="center" colSpan={2} ><b>{item.name}</b></td>
            </tr>
            <tr>
             <td><img className="my-image1" src={gmailimag}/></td>
              <td align="center">{item.email}</td>
            </tr>
            <tr>
              <td><img  className="div-imagepng" src={instaimag}/></td>
              <td align="center">{item.insta}</td>
            </tr>
          </tbody>
        </table>
    </div>
  ))}
</div>
   <div className="my-div1">
    <div className="redish-heading "><b>Core Value</b></div><br />
   <div className="justify-paragraph">
    {packageData.corevalue}We are committed to providing thrilling and memorable adventures while 
    prioritizing the safety of our clients and the environment. Sustainability is integral to our 
    operations as we strive to minimize our impact on nature. We uphold a standard of excellence in 
    service and experiences, ensuring every trek is exceptional. Fostering a sense of community, we 
    create connections among trekkers, guides, and local communities, promoting cultural understanding 
    and mutual respect. These values drive us to deliver exceptional treks that combine adventure, 
    safety, sustainability, excellence, and community engagement.
    </div> 
   </div>
         


</div>
        </div>
        
      </div>
      
)

}

export default AboutUs;