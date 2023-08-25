import { Link, Route } from 'react-router-dom';
import { Switch } from 'react-router-dom';
import AgencyLogin from './AgencyLogin';
import ContactUs from './ContactUs'
import React from 'react'; 
import AgencySignUp from './AgencySignUp';
import AdminLogin from './AdminLogin';
import TrekkerSignUp from './TrekkerSignUp';
import AllTreks from './AllTreks';
import AddTrek from './AddTrek'
import AdminDashboard from './AdminDashboard';
import DeleteAgency from './DeleteAgency'
import AddPackage from './AddPackage';
import ViewPackage from './ViewPackage';
import AboutUs from './AboutUs';
import Details from './Details'
import Booking from './Booking';
import PaymentDetails from './PaymentDetails';
import 'bootstrap/dist/css/bootstrap.min.css';
import AgencyDashboard from './AgencyDashboard';

function Navbar() {
  return (
    <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
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
                <Link to="/aboutus" className="nav-link">About Us</Link>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Login
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <Link to="/agencylogin" class="dropdown-item">Agency Login</Link>
                <Link to="/trekkerlogin" class="dropdown-item">Trekker Login</Link>
                </div>
            </li>

            <li class="nav-item">
                <Link to="/adminlogin" className="nav-link">Admin</Link>
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
       <Route exact path="/agency" component={AgencySignUp} />
        <Route exact path="/agencylogin" component={AgencyLogin}/>
        <Route exact path="/agencydashboard" component={AgencyDashboard}/>

        <Route exact path="/contactus" component={ContactUs} />
        <Route exact path="/admin" component={AdminLogin} />
        <Route exact path="/trekker" component={TrekkerSignUp} />
        <Route exact path="/alltreks" component={AllTreks} />
        <Route exact path="/adminlogin" component={AdminLogin} />
        <Route exact path="/addtrek" component={AddTrek} />
        <Route exact path="/admindashboard" component={AdminDashboard} />
        <Route exact path="/deleteagency" component={DeleteAgency} />
        <Route exact path="/addpackage" component={AddPackage} />
        <Route exact path="/viewpackages" component={ViewPackage} />   
        <Route exact path="/aboutus" component={AboutUs} /> 
        <Route exact path="/viewdetails/:id" component={Details} />
        <Route exact path="/getbookings/:id" component={Booking} />
        <Route exact path="/paymentdetails/:payment/:no" component={PaymentDetails} />
      </Switch>
    </div>
  );
}

export default Navbar;
