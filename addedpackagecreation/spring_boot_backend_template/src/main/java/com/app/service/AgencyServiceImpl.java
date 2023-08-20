package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AgencyRepository;
import com.app.dao.CityRepository;
import com.app.dao.CountryRepository;
import com.app.dao.GuideRepository;
import com.app.dao.PackageRepository;
import com.app.dao.StateRepository;
import com.app.dao.TrekDetailsRepository;
import com.app.dto.AddPackageRequest;
import com.app.dto.AddPackageResponse;
import com.app.dto.AgencyListResponse;
import com.app.dto.ApiResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.SignUpResponse;
import com.app.entities.Employee;
import com.app.enum_classes.ServiceType;
import com.app.enum_classes.Status;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.app.pojos.Agency;
import com.app.pojos.City;
import com.app.pojos.Country;
import com.app.pojos.Guide;
import com.app.pojos.State;
import com.app.pojos.TrekDetails;
import com.app.pojos.TrekPackage;


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
    private GuideRepository guideRepo;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private TrekDetailsRepository trekDetailsRepo;
    
    @Autowired
    private PackageRepository packageRepo;

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

	@Override
	public List<Guide> getAllGuide() {
		List<Guide> guides = guideRepo.findAll();
		return guides;
	}

	@Override
	public List<GetTrekNameResponse> getTrekName() {
		List<TrekDetails> trekDetails = trekDetailsRepo.findAll();
		return trekDetails.stream().
				map(trek->{
					GetTrekNameResponse trekName = mapper.map(trek,GetTrekNameResponse.class);
					return trekName;
				}).collect(Collectors.toList());
	}

	@Override
	public AddPackageResponse addPackageDetails(AddPackageRequest addPackageDTO) {
		 Guide guide = guideRepo.findByGuideName(addPackageDTO.getGuide());
		    if (guide == null) {
		        throw new ResourceNotFoundException("Guide not found");
		    }
		    
		    TrekDetails trekDetails = trekDetailsRepo.findByTrekName(addPackageDTO.getTrekDetails());
		    if (trekDetails == null) {
		        throw new ResourceNotFoundException("Trek details not found");
		    }

		// Create a new TrekPackage instance and set its properties
	    TrekPackage trekPackage = new TrekPackage();
	    trekPackage.setPackageName(addPackageDTO.getPackageName());
	    trekPackage.setPriceperPerson(addPackageDTO.getPriceperPerson());
	    trekPackage.setDuration(addPackageDTO.getDuration());
	    trekPackage.setMeals(addPackageDTO.getMeals());
	    trekPackage.setStay(addPackageDTO.getStay());
	    trekPackage.setDate(addPackageDTO.getDate());
	    trekPackage.setItinerary(addPackageDTO.getItinerary());
	    trekPackage.setStatus(Status.valueOf(addPackageDTO.getStatus().toUpperCase()));
	    trekPackage.setServiceType(ServiceType.valueOf(addPackageDTO.getServiceType().toUpperCase()));
	    // Set the Guide for the TrekPackage
	    trekPackage.setGuide(guide);
	    trekPackage.setTrekDetails(trekDetails);

	    // Save the TrekPackage entity
	    TrekPackage persistentPackage = packageRepo.save(trekPackage);

	    // Map and return the response
		return(mapper.map(persistentPackage, AddPackageResponse.class));
	}

	@Override
	public ApiResponse deletePackageDetails(Long packageId) {
		TrekPackage foundEmp = .findById()
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID !!!!!"));
		// emp exists !, foundEmp : persistent
		empDao.deleteById(empId);
		return "Emp details deleted successfully!";
		return null;
	}
}