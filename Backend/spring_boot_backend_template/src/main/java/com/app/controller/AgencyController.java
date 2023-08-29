package com.app.controller;

import com.app.dto.AddPackageRequest;
import com.app.dto.AgencyListResponse;
import com.app.dto.ApiResponse;
import com.app.dto.GetBookingDetailsResponse;
import com.app.dto.GetPackageResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.UpdatePackageRequest;
import com.app.dto.ViewPackageForNameDTO;
import com.app.pojos.Guide;
import com.app.service.AgencyService;
import com.app.service.BookingService;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/agency")
@CrossOrigin(origins = "http://localhost:3004")
public class AgencyController {
    @Autowired 
    private AgencyService agencyService;
    
    @Autowired
    private BookingService bookingService;

    public AgencyController() {
        System.out.println("in ctor of " + getClass());
    }

    //Add New Agency
    @PostMapping("/register")
    public ResponseEntity<?> addNewAgency(@RequestBody SignUpRequest agencyDTO) {
        return ResponseEntity.
        		status(HttpStatus.CREATED).
        	    body(agencyService.addAgencyDetails(agencyDTO));
    }
    
    //Agency Sign In
    @PostMapping("/signin")
	public ResponseEntity<?> signInAgency(@RequestBody @Valid SignInRequest agencyDTO) {
		System.out.println("auth req " + agencyDTO);
		SignInResponse resp = agencyService.singInAgency(agencyDTO);
		return ResponseEntity.ok(resp);
	}
    
    //List All Agencies
    @GetMapping("/listagency")
    public ResponseEntity<?> listAllAgency() {
        List<AgencyListResponse> agencies = agencyService.getAllAgency();
        return ResponseEntity.ok(agencies);
    }
    
    //List All Guides
    @GetMapping("/listguide")
    public ResponseEntity<?> listAllGuide() {
        List<Guide> agencies = agencyService.getAllGuide();
        return ResponseEntity.ok(agencies);
    }
    
    //Returning All TrekNames and ID's
    @GetMapping("/gettrekname")
    public ResponseEntity<?> listTrekName() {
        List<GetTrekNameResponse> trekNames = agencyService.getTrekName();
        return ResponseEntity.ok(trekNames);
    }
    
    //Add new package
    @PostMapping("/addpackage")
    public ResponseEntity<?> addPackage(@RequestBody AddPackageRequest addPackageDTO){
        return ResponseEntity.
        		status(HttpStatus.CREATED).
        	    body(agencyService.addPackageDetails(addPackageDTO));
    }
    
    //Delete Package by Id
    @DeleteMapping("deletepackage/{packageId}")
    public ApiResponse deletePackage(@PathVariable Long packageId) {
		System.out.println("in delete emp details " + packageId);
		return new ApiResponse(agencyService.deletePackageDetails(packageId));
	}
    
    //Get Package Details By Id
    @GetMapping("getpackage/{packageId}")
    public GetPackageResponse getPackageDetails(@PathVariable Long packageId) {
    	return agencyService.getPackageById(packageId);
    }
    
    //Update Package of Id received from frontend
    @PutMapping("updatepackage/{packageId}")
    public ResponseEntity<?> updatePackage(@RequestBody UpdatePackageRequest packageDTO,
    								 @PathVariable Long packageId) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(agencyService.updatePackageDetails(packageDTO,packageId));
    }  
    
    @GetMapping("/bookingdetails")
    public List<GetBookingDetailsResponse> getBookingDetails() {
    	return bookingService.listBookingDetails();
    }
    
    //harshada
    @GetMapping("/viewpackage")
	public ResponseEntity<List<ViewPackageForNameDTO>> getAllPackages() {
        List<ViewPackageForNameDTO> packages = agencyService.getAllPackageByName();
        return ResponseEntity.ok(packages);
    }
}
