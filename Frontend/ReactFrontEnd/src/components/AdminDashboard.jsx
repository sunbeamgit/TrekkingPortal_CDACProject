import React from 'react';
import { Link } from 'react-router-dom';

function AdminDashboard() {
    const containerStyle = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center', // Center items horizontally
        justifyContent: 'center', // Center items vertically
        height: '100vh', // Make the container full height
    };

    const divStyle = {
        padding: '20px',
        backgroundColor: '#b4b4b4',
        boxShadow: '10px 10px 10px 5px grey',
        marginTop: '10px',
        borderRadius: '8px',
        width: '50%',
        display: 'flex',
        justifyContent: 'space-around',
        alignItems: 'center',
    };

    const linkStyle = {
        textDecoration: 'none',
        color: '#333',
        fontSize: '20px',
        fontWeight: 'bold',
    };

    return (
        <div style={containerStyle}>
            <div style={divStyle}>
                <Link to="/addtrek" className="nav-link" style={linkStyle}>
                    Add Trek Details
                </Link>
            </div>
            <br />
            <div style={divStyle}>
                <Link to="/deleteagency" className="nav-link" style={linkStyle}>
                    Delete Agency
                </Link>
            </div>
            <br />
            <div style={divStyle}>
                <Link to="/alltreks" className="nav-link" style={linkStyle}>
                    Trek Details
                </Link>
            </div>
        </div>
    );
}

export default AdminDashboard;
