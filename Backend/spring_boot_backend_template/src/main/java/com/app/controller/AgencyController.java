package com.app.controller;

import com.app.dto.AddPackageRequest;
import com.app.dto.AgencyListResponse;
import com.app.dto.ApiResponse;
import com.app.dto.GetPackageResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.UpdatePackageRequest;
import com.app.pojos.Guide;
import com.app.service.AgencyService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/agency")
@CrossOrigin(origins = "http://localhost:3004")
public class AgencyController {
    @Autowired 
    private AgencyService agencyService;

    public AgencyController() {
        // Print a message to the console when an instance of the controller is created
        System.out.println("in ctor of " + getClass());
    }

    // Endpoint for adding agency details using POST method
    @PostMapping("/register")
    public ResponseEntity<?> addNewAgency(@RequestBody SignUpRequest agencyDTO) {
        System.out.println("in add new agency " + agencyDTO);
        
        // Call the service to add agency details
        return ResponseEntity.
        		status(HttpStatus.CREATED).
        	    body(agencyService.addAgencyDetails(agencyDTO));
    }
    
    @PostMapping("/signin")
	public ResponseEntity<?> signInAgency(@RequestBody @Valid SignInRequest agencyDTO) {
		System.out.println("auth req " + agencyDTO);
		SignInResponse resp = agencyService.singInAgency(agencyDTO);
		return ResponseEntity.ok(resp);
	}
    
    @GetMapping("/listagency")
    public ResponseEntity<?> listAllAgency() {
        List<AgencyListResponse> agencies = agencyService.getAllAgency();
        return ResponseEntity.ok(agencies);
    }
    
    @GetMapping("/listguide")
    public ResponseEntity<?> listAllGuide() {
        List<Guide> agencies = agencyService.getAllGuide();
        return ResponseEntity.ok(agencies);
    }
    
    @GetMapping("/gettrekname")
    public ResponseEntity<?> listTrekName() {
        List<GetTrekNameResponse> trekNames = agencyService.getTrekName();
        return ResponseEntity.ok(trekNames);
    }
    
    @PostMapping("/addpackage")
    public ResponseEntity<?> addPackage(@RequestBody AddPackageRequest addPackageDTO){
    	//Call the service to add package details
        return ResponseEntity.
        		status(HttpStatus.CREATED).
        	    body(agencyService.addPackageDetails(addPackageDTO));
    }
    
    @DeleteMapping("deletepackage/{packageId}")
    public ApiResponse deletePackage(@PathVariable Long packageId) {
		System.out.println("in delete emp details " + packageId);
		return new ApiResponse(agencyService.deletePackageDetails(packageId));
	}
    
    @GetMapping("getpackage/{packageId}")
    public GetPackageResponse getPackageDetails(@PathVariable Long packageId) {
    	return agencyService.getPackageById(packageId);
    }
    
    @PutMapping("updatepackage/{packageId}")
    public ApiResponse updatePackage(@RequestBody UpdatePackageRequest packageDTO,
    								 @PathVariable Long packageId) {
    	agencyService.updatePackageDetails(packageDTO,packageId);
    	return new ApiResponse("package updated successfully");
    }  
}
