import { Link } from 'react-router-dom';

function AdminDashboard() {
    const divStyle = {
        padding: '20px',
        backgroundColor: '#b4b4b4',
        boxShadow: '10px 10px 10px 5px grey',
        marginTop: '10px'
    };

    return (
    <div>
        <div style={divStyle}>
            <Link to="/addtrek" className="nav-link">Add Trek Details</Link>
        </div>

        <div style={divStyle}>
            <Link to="/deleteagency" className="nav-link">Delete Agency</Link>
        </div>

        <div style={divStyle}>
            <Link to="/alltreks" className="nav-link">Trek Details</Link>
        </div>
    </div>
    );
}

export default AdminDashboard;
