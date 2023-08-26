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
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.BookPack;
import com.app.dto.TrekkerSignUpRequest;
import com.app.pojos.TrekPackage;
import com.app.pojos.Trekker;
import com.app.service.BookingService;
import com.app.service.TrekkerService;

@RestController
@RequestMapping("/trekker")
@CrossOrigin(origins = "http://localhost:3004") 
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
		System.out.println("in add new agency " + trekkerDTO);
				
		return  ResponseEntity.status(HttpStatus.CREATED).body(trekkerservice.addTrekkerDetails(trekkerDTO));
	}
	
	
	@PutMapping("/{trekkerId}") 
	Trekker updateTrekkerProfile(@PathVariable ("trekkerId") Long trekkerId,@RequestBody Trekker updatedTrekker) {
		Trekker updateTrekker=trekkerservice.updateTrekerDetails(trekkerId,updatedTrekker);
		return updatedTrekker;
    }

	@PostMapping("/signin")
	public ResponseEntity<?> signInTrkker(@RequestBody @Valid AuthRequest request) {
		System.out.println("auth req " + request);
		// try {
		AuthResp resp = trekkerservice.singInEmployee(request);
		return ResponseEntity.ok(resp);
//		} catch (RuntimeException e) {
//			System.out.println(e);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
//		}
		
	}
	
	@PostMapping("/viewDetails/{packageDetailsId}")
	public TrekPackage viewTrekDetails(@PathVariable Long packageDetailsId) {
		return trekkerservice.showPackageDetails(packageDetailsId);
	}
	
	@PostMapping("/bookedpack/{packageDetailsId}")
	public BookPack bookedpack(@PathVariable Long packageDetailsId) {
		return trekkerservice.shoeBookedPack(packageDetailsId);
	}
	
	@PostMapping("/book-package")
    public ResponseEntity<String> bookPackage(@RequestParam Long packageId, @RequestParam Long trekkerId) {
        bookingService.bookPackageForTrekker(packageId, trekkerId);
        return ResponseEntity.ok("Booking successful");
    }
}
