import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

function PaymentDetails() {
  const { payment, no ,packageDetailsId} = useParams();
  const history = useHistory();
  const [trekkerid,setTrekkerid] = useState();
  const [isTrekkerLoggedIn,setIsTrekkerLoggedIn]=useState(false);
  const totalPayment = parseInt(payment) * parseInt(no);

  useEffect(()=>{
    const checkTrekkerLoggedIn = window.sessionStorage.getItem("isTrekkerLoggedIn");
    setIsTrekkerLoggedIn(checkTrekkerLoggedIn === 'true');
    if(isTrekkerLoggedIn == true){
      const trekkerEmail = window.sessionStorage.getItem("trekkeremail");

      const requestData = {
        email: trekkerEmail,
      };

      axios.post('http://localhost:7070/trekker/gettrekkerid', requestData, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((response) => {
          const result = response.data;
          console.log(result)
          setTrekkerid(result);
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    }
  })

  const handleClick=()=>{
    console.log("Inside payment onlick");

    axios.put(`http://localhost:7070/trekker/insertbooking/${trekkerid}/${packageDetailsId}`, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((response) => {
          const result = response.data;
          console.log(result);
          history.push("/allpackages")
        })
        .catch((error) => {
          console.error('Error:', error);
        });
  }

  return (
    <div style={{ textAlign: 'center' }}>
      <h1><b><span style={{ color: 'red' }}>Payment Details</span></b></h1>
      <img src={require('../images/paymentdetails.jpg')} alt="Payment" style={{ maxWidth: '30%', height: 'auto' }} />

      <div style={{ textAlign: 'center', padding: '20px' }}>
        <table className='my-table' style={{ width: '70%', margin: '0 auto', borderCollapse: 'collapse', backgroundColor: '#f9f9f9', border: '1px solid #ddd', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)' }}>
          <tbody>
            <tr>
              <td style={{ padding: '10px', borderBottom: '1px solid #ddd', fontFamily: 'Arial, sans-serif', fontSize: '16px' }}>Payment Per Person: ₹ {payment}</td>
            </tr>
            <tr>
              <td style={{ padding: '10px', borderBottom: '1px solid #ddd', fontFamily: 'Arial, sans-serif', fontSize: '16px' }}>No of Participants: {no}</td>
            </tr>
            <tr>
              <td style={{ padding: '10px', borderBottom: '1px solid #ddd', fontFamily: 'Arial, sans-serif', fontSize: '18px', fontWeight: 'bold' }}>
                <b>Total Payment: ₹ {totalPayment}</b>
              </td>

              <td>
                <button type="button" onClick={handleClick}>Confirm Booking</button>
              </td>
            </tr>

            <tr>
              <td style={{ padding: '10px', fontFamily: 'Arial, sans-serif', fontSize: '18px', fontWeight: 'bold' }}>
                <b>Thank You<br /> Have a nice Trek!!!</b>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default PaymentDetails;
