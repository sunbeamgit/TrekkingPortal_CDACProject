package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.custom_exceptions.PasswordUpdateException;
import com.app.dto.EmailRequest;
import com.app.dto.EmailResponse;
import com.app.dto.PasswordUpdateRequest;
import com.app.dto.VerifyOTPRequest;
import com.app.dto.VerifyOTPResponse;
import com.app.service.OtpService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3004") 
public class OTPController {
	@Autowired
    private OtpService otpService;
	
	String storedOtp;
    
    public OTPController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<EmailResponse> sendOTP(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();

        // Generate OTP
        String otp = otpService.generateOTP();

        // Store OTP in cache with the email
        otpService.storeOTPInBackend(email, otp);

        // Send OTP to the user's email
        otpService.sendOTPByEmail(email, otp);

        String responseMessage = "OTP sent successfully!";
        EmailResponse responseDTO = new EmailResponse(email, responseMessage);
        return ResponseEntity.ok(responseDTO);
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<VerifyOTPResponse> verifyOTP(@RequestBody VerifyOTPRequest request) {
    	String email = request.getEmail();
        String enteredOTP = request.getEnteredOTP();
        VerifyOTPResponse response = otpService.verifyOTP(email, enteredOTP);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
        try {
        	otpService.updatePassword(request.getEmail(), request.getNewPassword());
            return ResponseEntity.ok("Password updated successfully");
        } catch (PasswordUpdateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
