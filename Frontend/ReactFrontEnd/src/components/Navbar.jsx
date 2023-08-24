import { Link, Route, NavLink } from 'react-router-dom';
import { Switch } from 'react-router-dom';
import Login from './Login';
import ContactUs from './ContactUs'
import React from 'react'; 
import AgencySignUp from './AgencySignUp';
import TrekkerSignUp from './TrekkerSignUp';
import 'bootstrap/dist/css/bootstrap.min.css';

function Navbar() {
  return (
    <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            
            <li class="nav-item">
                <Link to="/alltreks" className="nav-link">All Treks</Link>
            </li>
            
            <li class="nav-item">
                <Link to="/contactus" className="nav-link">Contact Us</Link>
            </li>

            <li class="nav-item">
                <Link to="/login" className="nav-link">Login</Link>
            </li>
            
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Sign Up
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <Link to="/agency" class="dropdown-item">Agency</Link>
                <Link to="/trekker" class="dropdown-item">Trekker</Link>
                </div>
            </li>
        </ul>
        </div>
        </nav>
      
      <Switch>
        <Route exact path="/login" component={Login} />
        <Route exact path="/contactus" component={ContactUs} />
        <Route exact path="/admin" component={Login} />
        <Route exact path="/agency" component={AgencySignUp} />
        <Route exact path="/trekker" component={TrekkerSignUp} />
      </Switch>
    </div>
  );
}

export default Navbar;
