package com.app.controller;
import com.app.dto.AddTrekRequest;
import com.app.service.AdminService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	 public AdminController() {
	        // Print a message to the console when an instance of the controller is created
	        System.out.println("in ctor of " + getClass());
	    }

	@PostMapping(value = "/addtrek", consumes = "multipart/form-data")
	public ResponseEntity<?> addNewTrek(@RequestPart("addtrekDTO") AddTrekRequest addtrekDTO,
	        @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
		System.out.println("in add new trek"+addtrekDTO);
		
		return ResponseEntity.
			   status(HttpStatus.CREATED).
			   body(adminService.addTrekDetails(addtrekDTO,imageFile));
	}
}
