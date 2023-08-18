package com.app.service;

import java.util.List;
import com.app.dto.AgencyListResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.SignUpResponse;


public interface AgencyService {
	//regsiter
	SignUpResponse addAgencyDetails(SignUpRequest agencyDTO);
	
	//sign-in
	SignInResponse singInAgency(SignInRequest agencyDTO);
	
	//list all agencies
	List<AgencyListResponse> getAllAgency();
}
