package com.app.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exceptions.PasswordUpdateException;
import com.app.dao.OtpRepository;
import com.app.dao.TrekkerRespository;
import com.app.dto.VerifyOTPResponse;
import com.app.pojos.OtpRecord;
import com.app.pojos.Trekker;

@Service
public class OtpServiceImpl implements OtpService {
	
	 private static final String DIGITS = "0123456789";
	    private static final int OTP_LENGTH = 4;
	    
	    @Autowired
	    private final OtpRepository otpDao;
	    
	    @Autowired
	     private final  JavaMailSender javaMailSender;
	    
	    @Autowired
	    private TrekkerRespository trekkerRepo;
	    
	    @Autowired
	    public OtpServiceImpl(JavaMailSender javaMailSender,OtpRepository otpDao) {
	        this.javaMailSender = javaMailSender;
	        this.otpDao = otpDao;
	    }
	    
	    @Override
		public String generateOTP() {
		   
	    	 Random random = new Random();// Implement OTP generation logic  using a random number generator.
	         StringBuilder otp = new StringBuilder(OTP_LENGTH);// Return the generated OTP
	         
	         for (int i = 0; i < OTP_LENGTH; i++) {
	             otp.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
	         }
	         System.out.println(otp.toString());
	         return otp.toString();
		}
	    
	    @Override
		public void sendOTPByEmail(String email, String otp) {
			
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Your OTP Code");
	        message.setText("Your OTP code is: " + otp);

	        javaMailSender.send(message);
			
		}
	    @Override
	    @Transactional
	    public void storeOTPInBackend(String email, String otp) {
	    	 OtpRecord otpRecord = new OtpRecord();
	         otpRecord.setEmail(email);
	         otpRecord.setOtp(otp);
	         otpRecord.setTimestamp(LocalDateTime.now().plusMinutes(5)); // OTP validity period

	         otpDao.save(otpRecord);
	    }
	    

	    
	    
	    @Override
	    @Transactional(readOnly = true)
	    public VerifyOTPResponse verifyOTP(String email, String enteredOTP) {
	        Optional<OtpRecord> otpRecordOptional = otpDao.findLatestValidOTPByEmail(email);

	        VerifyOTPResponse response = new VerifyOTPResponse();
	        response.setEmail(email);

	      if (otpRecordOptional.isPresent()) {
	            OtpRecord otpRecord = otpRecordOptional.get();

	          if (otpRecord.getOtp().equals(enteredOTP)) {
	               if (otpRecord.getTimestamp().isAfter(LocalDateTime.now())) {
	                    response.setSuccess(true);
	                    response.setMessage("OTP verification succeeded!");
	                } 
	          
	               else {
	                    response.setSuccess(false);
	                    response.setMessage("OTP has expired.");	
	                }
	           } 
	         else {
	                response.setSuccess(false);
	                response.setMessage("Invalid OTP entered.");
	            }
	        } 
	      else {
	            response.setSuccess(false);
	            response.setMessage("No valid OTP found for this email.");
	        }

	        return response;
	    }
	    
	    @Override
	    public void updatePassword(String email, String newPassword) throws PasswordUpdateException {
	        Trekker trkker = trekkerRepo.findByEmail(email);
	        if (trkker == null) {
	            throw new PasswordUpdateException("User not found");
	        }

	        trkker.setPassword(newPassword);
	        trekkerRepo.save(trkker);
	    }

		
}
