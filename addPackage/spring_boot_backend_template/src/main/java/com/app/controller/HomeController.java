package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.GetPartialTrekResponse;
import com.app.pojos.BaseEntity;
import com.app.service.HomeService;


@RestController
@RequestMapping("/home")
public class HomeController extends BaseEntity{
	@Autowired 
    private HomeService homeService;

	@GetMapping("/getpartialtrek")
	public ResponseEntity<?> getPartialTrek(){
		List<GetPartialTrekResponse> treks = homeService.getPartialTrekDetails();
	    return ResponseEntity.ok(treks);
	}
}
