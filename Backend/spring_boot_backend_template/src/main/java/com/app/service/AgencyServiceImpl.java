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
import com.app.dto.GetPackageResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.SignInRequest;
import com.app.dto.SignInResponse;
import com.app.dto.SignUpRequest;
import com.app.dto.SignUpResponse;
import com.app.dto.UpdatePackageRequest;
import com.app.dto.UpdatePackageResponse;
import com.app.dto.ViewPackageForNameDTO;
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
    	System.out.println(agencyDTO.getCityName());
        // Retrieve City by name from the repository
        City city = cityRepo.findByCityName(agencyDTO.getCityName());
        State state = stateRepo.findByStateName(agencyDTO.getStateName());
        Country country = countryRepo.findByCountryName(agencyDTO.getCountryName());
        Agency agencyToAdd = new Agency();
        mapper.map(agencyDTO, agencyToAdd);
        agencyToAdd.setCity(city);
        agencyToAdd.setState(state);
        agencyToAdd.setCountry(country);
        Agency persistentAgency = agencyRepo.save(agencyToAdd);
        //return mapper.map(persistentAgency, SignUpResponse.class);
        SignUpResponse signupresp = new SignUpResponse();
        if(persistentAgency!= null) {
        	signupresp.setStatus(true);
        }
        else {
        	signupresp.setStatus(false);
        }
        return signupresp;
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
	public String deletePackageDetails(Long packageId) {
		TrekPackage trekPackage = packageRepo.findById(packageId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Package ID !!!!!"));
		// package exists !, foundPackage : persistent
		packageRepo.deleteById(packageId);
		return "Package details deleted successfully!";
	}

	@Override
	public GetPackageResponse getPackageById(Long packageId) {
		TrekPackage trekPackage = packageRepo.findById(packageId).
				orElseThrow(() -> new ResourceNotFoundException("Invalid Package ID !!!!!"));
		return mapper.map(trekPackage,GetPackageResponse.class);
	}

	@Override
	public UpdatePackageResponse updatePackageDetails(UpdatePackageRequest packageDTO,Long packageId) {
		TrekPackage trekPackage = packageRepo.findById(packageId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Package ID !!!!!"));
		
		Guide guide = guideRepo.findByGuideName(packageDTO.getGuide());
		    if (guide == null) {
		        throw new ResourceNotFoundException("Guide not found");
		    }
		    
		TrekDetails trekDetails = trekDetailsRepo.findByTrekName(packageDTO.getTrekDetails());
		    if (trekDetails == null) {
		        throw new ResourceNotFoundException("Trek details not found");
		    }
		
		packageDTO.setId(trekPackage.getId());
		mapper.map(packageDTO,trekPackage);
		trekPackage.setServiceType(ServiceType.valueOf(packageDTO.getServiceType().toUpperCase()));
		trekPackage.setGuide(guide);
		packageRepo.save(trekPackage);
		trekPackage.setTrekDetails(trekDetails);
		return mapper.map(trekPackage, UpdatePackageResponse.class);
	}
	
	@Override
	public List<ViewPackageForNameDTO> getAllPackageByName(){
		List<TrekPackage> packageDetails = packageRepo.findAll();
		return packageDetails.stream()
				.map(trekpackage->{
					ViewPackageForNameDTO response = mapper.map(trekpackage, ViewPackageForNameDTO.class);
					return response;
				})
				.collect(Collectors.toList());	
	}
}
