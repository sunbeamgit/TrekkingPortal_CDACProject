package com.app.service;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import com.app.dao.CityRepository;
import com.app.dao.CountryRepository;
import com.app.dao.StateRepository;
import com.app.dao.TrekDetailsRepository;
import com.app.dto.GetCityResponse;
import com.app.dto.GetCountryResponse;
import com.app.dto.GetPartialTrekResponse;
import com.app.dto.GetStateResponse;
import com.app.pojos.City;
import com.app.pojos.Country;
import com.app.pojos.State;
import com.app.pojos.TrekDetails;

@Service
@Transactional
public class HomeServiceImpl implements HomeService{
	@Autowired
    private TrekDetailsRepository trekDetailsRepo;
	
    @Autowired
    private ModelMapper mapper;
    
    @Autowired 
    private CityRepository cityRepo;
    
    @Autowired 
    private StateRepository stateRepo;
	
    @Autowired 
    private CountryRepository countryRepo;
	
	
	@Override
	public List<GetPartialTrekResponse> getPartialTrekDetails() throws IOException{
		List<TrekDetails> trekDetails = trekDetailsRepo.findAll();
		return trekDetails.stream()
			.map(trek -> {
				GetPartialTrekResponse response = mapper.map(trek, GetPartialTrekResponse.class);
					  response.setAirport(trek.getAirport().getAirportName());
					  response.setRailwayStation(trek.getRailwayStation().getRailwaystationName());
					  response.setSeason(trek.getSeason().getSeasonName());
					  response.setGrade(trek.getGrade().getGradeCategory());
					  
					  if (trek.getImagePath() != null) {
						  byte[] imageBytes;
						try {
							imageBytes = FileUtils.readFileToByteArray(new File(trek.getImagePath()));
							response.setImage(imageBytes);
						} catch (IOException e) {
							e.printStackTrace();
						}  
					  }
					  return response;
				  })
				  .collect(Collectors.toList());
	}

	@Override
	public List<GetCityResponse> getCityDetails() {
		List<City> cityDetails = cityRepo.findAll();
		return cityDetails.stream()
				.map(city->{
					GetCityResponse response = mapper.map(city, GetCityResponse.class);
					return response;
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<GetStateResponse> getStateDetails() {
		List<State> stateDetails = stateRepo.findAll();
		return stateDetails.stream()
				.map(state->{
					GetStateResponse response = mapper.map(state, GetStateResponse.class);
					return response;
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<GetCountryResponse> getCountryDetails() {
	    List<Country> countryDetails = countryRepo.findAll();
	    return countryDetails.stream()
	        .map(country -> {
	            GetCountryResponse response = mapper.map(country, GetCountryResponse.class);
	            return response;
	        })
	        .collect(Collectors.toList());
	}
}
