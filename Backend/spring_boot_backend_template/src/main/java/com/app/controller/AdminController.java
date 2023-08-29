package com.app.controller;
import com.app.dto.AddTrekRequest;
import com.app.dto.AdminSignInRequest;
import com.app.dto.AdminSignInResponse;
import com.app.service.AdminService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3004")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	public AdminController() {
		System.out.println("in ctor of " + getClass());
	}

	//Admin Sign In
	@PostMapping("/signin")
	public ResponseEntity<?> signInAgency(@RequestBody AdminSignInRequest adminDTO) {
		 System.out.println("auth req " + adminDTO);
		 AdminSignInResponse resp = adminService.singInAdmin(adminDTO);
		 return ResponseEntity.ok(resp);
	}
	 
	//Add new trek
	@PostMapping(value = "/addtrek", consumes = "multipart/form-data")
	public ResponseEntity<?> addNewTrek(@RequestPart("addtrekDTO") AddTrekRequest addtrekDTO,
	        @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
		System.out.println("in add new trek"+addtrekDTO);
		
		return ResponseEntity.
			   status(HttpStatus.CREATED).
			   body(adminService.addTrekDetails(addtrekDTO,imageFile));
	}
}
