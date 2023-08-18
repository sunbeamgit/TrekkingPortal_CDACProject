package com.app.controller;
import com.app.dto.AgencyListResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.pojos.Agency;
import com.app.service.AgencyService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/agency")
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
    
    @GetMapping("/list")
    public ResponseEntity<?> listAllAgency() {
        List<AgencyListResponse> agencies = agencyService.getAllAgency();
        return ResponseEntity.ok(agencies);
    }
}
