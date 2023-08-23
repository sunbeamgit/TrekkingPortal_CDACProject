package com.app.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.GetCityResponse;
import com.app.dto.GetCountryResponse;
import com.app.dto.GetPartialTrekResponse;
import com.app.dto.GetStateResponse;
import com.app.pojos.BaseEntity;
import com.app.service.HomeService;


@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3004")
public class HomeController extends BaseEntity{
	@Autowired 
    private HomeService homeService;

	@GetMapping("/getpartialtrek")
	public ResponseEntity<?> getPartialTrek() throws IOException{
		System.out.println("inside get partial trek details");
		List<GetPartialTrekResponse> treks = homeService.getPartialTrekDetails();
	    return ResponseEntity.ok(treks);
	}
	
	@GetMapping("/getcities")
	public ResponseEntity<?> getCity(){
		System.out.println("inside get partial trek details");
		List<GetCityResponse> citites = homeService.getCityDetails();
	    return ResponseEntity.ok(citites);
	}
	
	@GetMapping("/getstates")
	public ResponseEntity<?> getState(){
		System.out.println("inside get partial trek details");
		List<GetStateResponse> citites = homeService.getStateDetails();
	    return ResponseEntity.ok(citites);
	}
	
	@GetMapping("/getcountries")
	public ResponseEntity<?> getCountry(){
		System.out.println("inside get partial trek details");
		List<GetCountryResponse> countries = homeService.getCountryDetails();
	    return ResponseEntity.ok(countries);
	}
}
