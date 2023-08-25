package com.app.service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.app.dao.CityRepository;
import com.app.dao.CountryRepository;
import com.app.dao.PackageRepository;
import com.app.dao.StateRepository;
import com.app.dao.TrekDetailsRepository;
import com.app.dao.TrekkerRespository;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.BookPack;
import com.app.dto.TrekkerSignUpRequest;
import com.app.dto.TrekkerSignUpResponse;
import com.app.pojos.City;
import com.app.pojos.Country;
import com.app.pojos.State;
import com.app.pojos.TrekDetails;
import com.app.pojos.TrekPackage;
import com.app.pojos.Trekker;


@Service
@Transactional
public class TrekkerServiceImpl implements TrekkerService{
	
	@Autowired
    private ModelMapper mapper;
	
	@Autowired
    private TrekkerRespository trekkerRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private CountryRepository countryRepo;
    
    @Autowired
    private TrekDetailsRepository trekDetailsRepo;
    
    @Autowired
    private PackageRepository packageRepo;

//	@Override
//	public String addTrekkerDetails(Trekker trekker) {
//		 // Retrieve City by name from the repository
//        City city = cityRepo.findByCityName(trekker.getCity().getCityName());
//        if (city == null) {
//            return "City not found";
//        }
//        trekker.setCity(city);
//
//        // Retrieve State by name from the repository
//        State state = stateRepo.findByStateName(trekker.getState().getStateName());
//        if (state == null) {
//            return "State not found";
//        }
//        trekker.setState(state);
//
//        // Retrieve Country by name from the repository
//        Country country = countryRepo.findByCountryName(trekker.getCountry().getCountryName());
//        if (country == null) {
//            return "Country not found";
//        }
//        trekker.setCountry(country);
//
//        Trekker persistentTrekker = trekkerRepo.save(trekker);
//        return "User registered with ID " + persistentTrekker.getId();
//		
//	}
    
    @Override
	public TrekkerSignUpResponse addTrekkerDetails(TrekkerSignUpRequest trekkerDTO) {
		System.out.println(trekkerDTO.getCityName());
		City city = cityRepo.findByCityName(trekkerDTO.getCityName());
		State state  = stateRepo.findByStateName(trekkerDTO.getStateName());
		Country country = countryRepo.findByCountryName(trekkerDTO.getCountryName());
		Trekker trekkerToAdd = new Trekker();
		mapper.map(trekkerDTO,trekkerToAdd);
		trekkerToAdd.setCity(city);
		trekkerToAdd.setState(state);
		trekkerToAdd.setCountry(country);
		Trekker persistentAgency = trekkerRepo.save(trekkerToAdd);
		return mapper.map(persistentAgency, TrekkerSignUpResponse.class);
	}
	
	//SignIn for Trekker
	@Override
	public AuthResp singInEmployee(AuthRequest request) {
		Trekker trekker = trekkerRepo.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow();
		return mapper.map(trekker, AuthResp.class);
	}
	
	//Update profile for Trekker
	@Override
	public Trekker updateTrekerDetails(Long Id, Trekker trekker) {
	    Trekker newTrekker = trekkerRepo.findById(Id)
	            .orElseThrow(() -> new EntityNotFoundException("User not found"));

	    
	    if(trekker.getState().getStateName() != null)
	    {
	    	State newState=stateRepo.findByStateName(trekker.getState().getStateName());
	    	newTrekker.setState(newState);
	    }
	    else {
	    	newTrekker.setFirstname(trekker.getFirstname());
    	    newTrekker.setLastname(trekker.getLastname());
    	    newTrekker.setBirthDate(trekker.getBirthDate());
    	    newTrekker.setMobileno(trekker.getMobileno());
	    	
	    }
	    
	    if(trekker.getCity().getCityName()!=null)
	    {
	    	City newCity=cityRepo.findByCityName(trekker.getCity().getCityName());
	    	newTrekker.setCity(newCity);
	    }
	    else {
	    	newTrekker.setFirstname(trekker.getFirstname());
    	    newTrekker.setLastname(trekker.getLastname());
    	    newTrekker.setBirthDate(trekker.getBirthDate());
    	    newTrekker.setMobileno(trekker.getMobileno());
	    	
	    }
	    
	    if(trekker.getCountry().getCountryName()!=null)
	    {
	    	Country newCountry=countryRepo.findByCountryName(trekker.getCountry().getCountryName());
	    	newTrekker.setCountry(newCountry);
	    }
	    else {
	    	newTrekker.setFirstname(trekker.getFirstname());
    	    newTrekker.setLastname(trekker.getLastname());
    	    newTrekker.setBirthDate(trekker.getBirthDate());
    	    newTrekker.setMobileno(trekker.getMobileno());
	    	
	    }
	    return trekkerRepo.save(newTrekker);
	

	}

		@Override
		public TrekPackage showPackageDetails(Long id) {
			TrekPackage packageDetails=packageRepo.findById(id).orElseThrow();
			return packageDetails;
		}

		@Override
		public BookPack shoeBookedPack(Long id) {
//			Packages bk=packageRepo.findById(mapper.map(id, Packages.class));
//			return mapper.map(bk, BookPack.class);;
			
			 Optional<TrekPackage> packageOptional = packageRepo.findById(id);
			    if (packageOptional.isPresent()) {
			    	TrekPackage bk = packageOptional.get();
			        return mapper.map(bk, BookPack.class);
			    }
			    return null; 
		}

		@Override
		public int showPaymentDetails(String payment, String no) {
			int paymentInt=Integer.parseInt(payment);
			int noInt=Integer.parseInt(no);
			int paymentdetails=paymentInt * noInt;
			return paymentdetails;
		}

		
		


		
	
	
	
}
