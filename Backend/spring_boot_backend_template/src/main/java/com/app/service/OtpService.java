package com.app.service;

import com.app.custom_exceptions.PasswordUpdateException;
import com.app.dto.VerifyOTPResponse;

public interface OtpService {
	String generateOTP();
	    void sendOTPByEmail(String email, String otp);
		void storeOTPInBackend(String email, String otp);
		VerifyOTPResponse verifyOTP(String email, String enteredOTP);
		void updatePassword(String email, String newPassword) throws PasswordUpdateException;
}
