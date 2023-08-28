package com.app.service;

import java.util.List;

import com.app.dto.AddPackageRequest;
import com.app.dto.AddPackageResponse;
import com.app.dto.AgencyListResponse;
import com.app.dto.ApiResponse;
import com.app.dto.GetPackageResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.SignUpResponse;
import com.app.dto.UpdatePackageRequest;
import com.app.dto.UpdatePackageResponse;
import com.app.dto.ViewPackageForNameDTO;
import com.app.pojos.Guide;


public interface AgencyService {
	//regsiter
	SignUpResponse addAgencyDetails(SignUpRequest agencyDTO);
	
	//sign-in
	SignInResponse singInAgency(SignInRequest agencyDTO);
	
	//list all agencies
	List<AgencyListResponse> getAllAgency();
	
	//list all guide
	List<Guide> getAllGuide();
	
	//list trek by names
	List<GetTrekNameResponse> getTrekName();
	
	//Insert new package
	AddPackageResponse addPackageDetails(AddPackageRequest addPackageDTO);
	
	//Delete Package
	String deletePackageDetails(Long packageId);
	
	//Edit Package Request
	GetPackageResponse getPackageById(Long packageId);
	
	//update package
	UpdatePackageResponse updatePackageDetails(UpdatePackageRequest packageDTO,Long packageId);
	
	//View Packages
	List<ViewPackageForNameDTO> getAllPackageByName();
}
