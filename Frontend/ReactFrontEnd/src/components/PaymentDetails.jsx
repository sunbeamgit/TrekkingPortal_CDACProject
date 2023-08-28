import { useParams } from 'react-router-dom';

function PaymentDetails() {
  const { payment, no } = useParams();
  const totalPayment = parseInt(payment) * parseInt(no);

  const handleClick=()=>{
    console.log("Inside payment onlick");

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
