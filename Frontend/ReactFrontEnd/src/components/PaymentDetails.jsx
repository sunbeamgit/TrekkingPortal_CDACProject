import { useHistory } from 'react-router-dom'; 
import { useParams } from 'react-router-dom';

function PaymentDetails(){
    const { payment, no } = useParams();

    const totalPayment = parseInt(payment) * parseInt(no);

    return (
        <div>
             <div className="my-div" >
             <center><h1><b><span style={{ color: 'red' }}>Payment Details</span></b></h1></center>
             </div>
             <div><center>
             <table className='my-table'>
                    <tr>
                        <td><center>Payment Per Person: {payment}</center></td>
                    </tr>
                    <tr>
                        <td><center>No of Participants: {no}</center></td>
                    </tr>
                    <tr>
                        <td><center><b>Total Payment: {totalPayment}</b></center></td>
                    </tr>
                    <tr>
                        <td><center><b>Thank You<br></br> Have a nice Trek!!!</b></center></td>
                    </tr>
                </table>
             </center>
                
             </div>
            
        </div>
    );
    
}

export default PaymentDetails