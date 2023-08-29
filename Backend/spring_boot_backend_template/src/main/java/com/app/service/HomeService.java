package com.app.service;

import java.io.IOException;
import java.util.List;
import com.app.dto.GetAirportResponse;
import com.app.dto.GetAllPackageResponse;
import com.app.dto.GetCityResponse;
import com.app.dto.GetCountryResponse;
import com.app.dto.GetGradeResponse;
import com.app.dto.GetGuideResponse;
import com.app.dto.GetPartialTrekResponse;
import com.app.dto.GetSeasonResponse;
import com.app.dto.GetStateResponse;
import com.app.dto.GetStationResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.ViewPackageForNameDTO;
import com.app.pojos.Home;
import com.app.pojos.Team;

public interface HomeService {
	List<GetPartialTrekResponse> getPartialTrekDetails() throws IOException;
	List<GetCityResponse> getCityDetails();
	List<GetStateResponse> getStateDetails();
	List<GetCountryResponse> getCountryDetails();
	List<byte[]> downloadTrekImage();
	GetAirportResponse getAirpotDetails();
	GetStationResponse getStationDetails();
	GetSeasonResponse getSeasonDetails();
	GetGradeResponse getGradeDetails();
	List<GetAllPackageResponse> getAllPackageDetails() throws IOException;
	
	//harshda
	List<ViewPackageForNameDTO> getPackageByName();
	List<GetTrekNameResponse> getTrekbyName();
	List<GetGuideResponse> getGuideName();
	
	
	//show all teams info
		List<Team> showAllTeamInfo();
		Home showAdminDetails();
}