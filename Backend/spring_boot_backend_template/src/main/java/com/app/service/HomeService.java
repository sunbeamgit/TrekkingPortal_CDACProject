package com.app.service;

import java.io.IOException;
import java.util.List;
import com.app.dto.GetCityResponse;
import com.app.dto.GetCountryResponse;
import com.app.dto.GetPartialTrekResponse;
import com.app.dto.GetStateResponse;

public interface HomeService {
	List<GetPartialTrekResponse> getPartialTrekDetails() throws IOException;
	
	List<GetCityResponse> getCityDetails();
	List<GetStateResponse> getStateDetails();
	List<GetCountryResponse> getCountryDetails();
}