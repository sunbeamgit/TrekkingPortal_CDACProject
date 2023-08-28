package com.app.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.BookPack;
import com.app.dto.ChangePasswordResponse;
import com.app.dto.TrekkerSignUpRequest;
import com.app.dto.TrekkerSigninRequest;
import com.app.dto.TrekkerSigninResp;
import com.app.dto.ViewDetailsResponse;
import com.app.service.BookingService;
import com.app.service.TrekkerService;

@RestController
@RequestMapping("/trekker")
@CrossOrigin(origins = "http://localhost:3008") 
public class TrekkerController {
	@Autowired
	private TrekkerService trekkerservice;
	
	@Autowired
    private BookingService bookingService;
	
	
	public TrekkerController() {
		System.out.println("Inside Trekker Controller");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> addTrekkerDetails(@RequestBody TrekkerSignUpRequest trekkerDTO) {
		System.out.println("inside trekker sign up " + trekkerDTO);
		return  ResponseEntity.status(HttpStatus.CREATED).body(trekkerservice.addTrekkerDetails(trekkerDTO));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInTrekker(@RequestBody @Valid TrekkerSigninRequest request) {
		System.out.println("auth req " + request);
		TrekkerSigninResp resp = trekkerservice.singInTrekker(request);
		return ResponseEntity.ok(resp);	
	}
	
//	@PutMapping("/{trekkerId}") 
//	Trekker updateTrekkerProfile(@PathVariable ("trekkerId") Long trekkerId,@RequestBody Trekker updatedTrekker) {
//		Trekker updateTrekker=trekkerservice.updateTrekerDetails(trekkerId,updatedTrekker);
//		return updatedTrekker;
//    }

	@PutMapping("/viewDetails/{packageDetailsId}")
	public ViewDetailsResponse viewTrekDetails(@PathVariable Long packageDetailsId) {
		return trekkerservice.showPackageDetails(packageDetailsId);
	}
	
	@PostMapping("/bookedpack/{packageDetailsId}")
	public BookPack bookedpack(@PathVariable Long packageDetailsId) {
		return trekkerservice.showBookedPack(packageDetailsId);
	}
	
	@PostMapping("/book-package")
    public ResponseEntity<String> bookPackage(@RequestParam Long packageId, @RequestParam Long trekkerId) {
        bookingService.bookPackageForTrekker(packageId, trekkerId);
        return ResponseEntity.ok("Booking successful");
    }
	
	@PostMapping("/changepassword")
    public String changePassword(@RequestBody ChangePasswordResponse  chngpwd) {
        return trekkerservice.changepassword(chngpwd);
    }
}
