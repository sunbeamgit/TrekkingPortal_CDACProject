package com.app.service;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.BookPack;
import com.app.dto.TrekkerSignUpRequest;
import com.app.dto.TrekkerSignUpResponse;
import com.app.pojos.TrekPackage;
import com.app.pojos.Trekker;

public interface TrekkerService {
	//Trekker Sign Up
	TrekkerSignUpResponse addTrekkerDetails(TrekkerSignUpRequest trekkerDTO);

	//add a method for signin
	AuthResp singInEmployee(AuthRequest request);
	
	//Update profile
	Trekker updateTrekerDetails(Long id,Trekker trekker);

	//view details
	TrekPackage showPackageDetails(Long id);
	
	//view Booked package info
	BookPack shoeBookedPack(Long id);
	
	//view payment details
	int showPaymentDetails(String payment,String noofparticipent);
	
}
