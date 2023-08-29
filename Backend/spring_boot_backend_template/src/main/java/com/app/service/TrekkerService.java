package com.app.service;

import com.app.dto.BookPack;
import com.app.dto.ChangePasswordResponse;
import com.app.dto.GetTrekkerIdByEmailRequest;
import com.app.dto.TrekkerSignUpRequest;
import com.app.dto.TrekkerSignUpResponse;
import com.app.dto.TrekkerSigninRequest;
import com.app.dto.TrekkerSigninResp;
import com.app.dto.ViewDetailsResponse;
import com.app.pojos.TrekPackage;
import com.app.pojos.Trekker;

public interface TrekkerService {
	//Trekker Sign Up
	TrekkerSignUpResponse addTrekkerDetails(TrekkerSignUpRequest trekkerDTO);

	//add a method for signin
	TrekkerSigninResp singInTrekker(TrekkerSigninRequest request);
	
	//Update profile
	Trekker updateTrekerDetails(Long id,Trekker trekker);

	//view details
	//TrekPackage showPackageDetails(Long id);
	ViewDetailsResponse showPackageDetails(Long id);
	
	//view Booked package info
	BookPack showBookedPack(Long id);
	
	//view payment details
	int showPaymentDetails(String payment,String noofparticipent);
	
	//change password 
	String changepassword(ChangePasswordResponse response);
	
	Long findTrekkerIdByEmail(GetTrekkerIdByEmailRequest dtoobj);
	
}
