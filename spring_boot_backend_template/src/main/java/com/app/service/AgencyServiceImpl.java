package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AgencyRepository;
import com.app.dao.CityRepository;
import com.app.dao.CountryRepository;
import com.app.dao.StateRepository;
import com.app.dto.AgencyListResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.SignUpResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import com.app.pojos.Agency;
import com.app.pojos.City;
import com.app.pojos.Country;
import com.app.pojos.State;


@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyRepository agencyRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private CountryRepository countryRepo;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public SignUpResponse addAgencyDetails(SignUpRequest agencyDTO) {
        // Retrieve City by name from the repository
        City city = cityRepo.findByCityName(agencyDTO.getCity().getCityName());
        agencyDTO.setCity(city);

        // Retrieve State by name from the repository
        State state = stateRepo.findByStateName(agencyDTO.getState().getStateName());
        agencyDTO.setState(state);

        // Retrieve Country by name from the repository
        Country country = countryRepo.findByCountryName(agencyDTO.getCountry().getCountryName());
        agencyDTO.setCountry(country);

        Agency persistentAgency = agencyRepo.save(mapper.map(agencyDTO, Agency.class));
        return mapper.map(persistentAgency, SignUpResponse.class);
    }

    @Override
    public SignInResponse singInAgency(SignInRequest agencyDTO) {
        Agency agency = 
            agencyRepo.findByEmailAndPassword
            (agencyDTO.getEmail(), agencyDTO.getPassword()).
            orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password"));
        return mapper.map(agency, SignInResponse.class);
    }

	@Override
	public List<AgencyListResponse> getAllAgency() {
		 List<Agency> agencies = agencyRepo.findAll();
		
		  return agencies.stream()
		  .map(agency -> {
			  AgencyListResponse response = mapper.map(agency, AgencyListResponse.class);
			  response.setCityName(agency.getCity().getCityName());
			  response.setStateName(agency.getState().getStateName());
			  response.setCountryName(agency.getCountry().getCountryName());
			  return response;
		  })
		  .collect(Collectors.toList());
	}
}
