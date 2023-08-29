import { Link, Route } from 'react-router-dom';
import { Switch } from 'react-router-dom';
import { useHistory } from 'react-router-dom';
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
import AllPackages from './AllPackages'
import ViewPackage from './ViewPackage';
import AboutUs from './AboutUs';
import Booking from './Booking';
import PaymentDetails from './PaymentDetails';
import AgencyDashboard from './AgencyDashboard';
import ViewDetails from './ViewDetails';
import TrekkerLogin from './TrekkerLogin';
import UpdatePackage from './UpdatePackage';
import UpdatePassword from './UpdatePassword';
import ForgotPassword from './ForgotPassword';
import ForgotPassword2 from './ForgotPassword2';
import ForgotPassword3 from './ForgotPassword3'
import ViewBookings from './ViewBookings';
import Logout from './Logout';
import "./navbar.css"
import 'bootstrap/dist/css/bootstrap.min.css';

function Navbar() {
  const history = useHistory();
  const handleLogout=()=>{
    console.log("logout handler");
    sessionStorage.clear();
    history.push("/logout")
  }

  return (
    <div>
        <nav class="navbar navbar-expand-lg navbar-custom">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav" className="navbar-nav ml-auto">
            <li class="nav-item">
                <Link to="/allpackages" className="nav-link">All Treks</Link>
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

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Profile
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <Link to="/updatepassword" class="dropdown-item">Change Password</Link>
                </div>
            </li>

            <li class="nav-item">
                <Link to="/logout" className="nav-link"
                onClick={handleLogout}>Logout</Link>
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
        <Route exact path="/allpackages" component={AllPackages} />
        <Route exact path="/adminlogin" component={AdminLogin} />
        <Route exact path="/addtrek" component={AddTrek} />
        <Route exact path="/admindashboard" component={AdminDashboard} />
        <Route exact path="/deleteagency" component={DeleteAgency} />
        <Route exact path="/addpackage" component={AddPackage} />
        <Route exact path="/viewpackages" component={ViewPackage} />   
        <Route exact path="/aboutus" component={AboutUs} /> 
        <Route exact path="/viewdetails/:id" component={ViewDetails} />
        <Route exact path="/getbookings/:id" component={Booking} />
        <Route exact path="/trekkerlogin" component={TrekkerLogin} />
        <Route path="/agency/updatepackage/:packageId" component={UpdatePackage} />
        <Route exact path="/paymentdetails/:payment/:no/:packageDetailsId" component={PaymentDetails} />
        
        <Route exact path="/updatepassword" component={UpdatePassword}/>
        <Route exact path="/forgotpassword" component={ForgotPassword}/>
        <Route exact path="/forgotpassword2" component={ForgotPassword2}/>
        <Route exact path="/forgotpassword3" component={ForgotPassword3}/>
        <Route exact path="/viewbookings" component={ViewBookings}/>

        <Route exact path="/logout" component={Logout}/>
      </Switch>
    </div>
  );
}

export default Navbar;
