package com.app.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.GetAirportResponse;
import com.app.dto.GetAllPackageResponse;
import com.app.dto.GetCityResponse;
import com.app.dto.GetCountryResponse;
import com.app.dto.GetGradeResponse;
import com.app.dto.GetGuideResponse;
import com.app.dto.GetPartialTrekResponse;
import com.app.dto.GetSeasonResponse;
import com.app.dto.GetStateResponse;
import com.app.dto.GetStationResponse;
import com.app.dto.GetTrekImageResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.ViewPackageForNameDTO;
import com.app.pojos.BaseEntity;
import com.app.pojos.Home;
import com.app.pojos.Team;
import com.app.service.HomeService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3004")
public class HomeController extends BaseEntity{
	@Autowired 
    private HomeService homeService;
	
	//Get Partial Trek Data
	@GetMapping(value = "/getpartialdata")
	public ResponseEntity<?> getPartialTrek() throws IOException{
		List<GetPartialTrekResponse> treks = homeService.getPartialTrekDetails();
	    return ResponseEntity.ok(treks);
	}
	
	@GetMapping(value = "/getallpackage")
	public ResponseEntity<?> getAllPackage() throws IOException{
		List<GetAllPackageResponse> treks = homeService.getAllPackageDetails();
	    return ResponseEntity.ok(treks);
	}
	
	//Fetch All trek images
	@GetMapping(value = "/gettrekimages")
	public ResponseEntity<List<byte[]>> getTrekImages() {
		List<byte[]> imageBytesList = homeService.downloadTrekImage();
	    return ResponseEntity.ok(imageBytesList);
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
	
	@GetMapping("/getairport")
	public ResponseEntity<?> getAirport(){
		GetAirportResponse airport = homeService.getAirpotDetails();
	    return ResponseEntity.ok(airport);
	}
	
	@GetMapping("/getstation")
	public ResponseEntity<?> getRailwayStationt(){
		GetStationResponse station = homeService.getStationDetails();
	    return ResponseEntity.ok(station);
	}
	
	@GetMapping("/getseason")
	public ResponseEntity<?> getSeason(){
		GetSeasonResponse season= homeService.getSeasonDetails();
	    return ResponseEntity.ok(season);
	}
	
	@GetMapping("/getgrade")
	public ResponseEntity<?> getGrade(){
		GetGradeResponse grade= homeService.getGradeDetails();
	    return ResponseEntity.ok(grade);
	}
	
	@GetMapping("/getpackagename")
	public ResponseEntity<?> getPackageName()
	{
		List<ViewPackageForNameDTO> packageName = homeService.getPackageByName();
		return ResponseEntity.ok(packageName);
	}
	
	@GetMapping("/getTrek")
	public ResponseEntity<?> getTrekName()
	{
		List<GetTrekNameResponse> trekName = homeService.getTrekbyName();
		return ResponseEntity.ok(trekName);
	}
	
	@GetMapping("/getGuide")
	public ResponseEntity<?> getGuideName()
	{
		List<GetGuideResponse> guideName = homeService.getGuideName();
		return ResponseEntity.ok(guideName);
	}
	
	@GetMapping("/team")
	public List<Team> showallteam() {
		System.out.println("in new admin");
		return homeService.showAllTeamInfo();
	}
	
	@GetMapping("/about")
	public Home showAboutPage(Long val) {
		System.out.println("in new admin");
		return homeService.showAdminDetails();
	}
}
