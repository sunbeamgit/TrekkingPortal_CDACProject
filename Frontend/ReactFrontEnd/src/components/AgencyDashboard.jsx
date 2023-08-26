import { Link} from 'react-router-dom';

function AgencyDashboard(){
    const divStyle = {
        padding: '20px',
        backgroundColor: '#b4b4b4',
        boxShadow: '10px 10px 10px 5px grey',
        marginTop: '10px'
    };

    return (
    <div>
        <div style={divStyle}>
            <Link to="/addpackage" className="nav-link">Insert Package</Link>
        </div>

        <div style={divStyle}>
            <Link to="/viewpackages" className="nav-link">View Packages</Link>
        </div>
    </div>
    );
}

export default AgencyDashboard;