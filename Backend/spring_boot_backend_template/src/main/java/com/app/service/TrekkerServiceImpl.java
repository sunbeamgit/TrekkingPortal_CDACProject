package com.app.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CityRepository;
import com.app.dao.CountryRepository;
import com.app.dao.PackageRepository;
import com.app.dao.StateRepository;
import com.app.dao.TrekDetailsRepository;
import com.app.dao.TrekkerRespository;
import com.app.dto.BookPack;
import com.app.dto.ChangePasswordResponse;
import com.app.dto.GetTrekkerIdByEmailRequest;
import com.app.dto.TrekkerSignUpRequest;
import com.app.dto.TrekkerSignUpResponse;
import com.app.dto.TrekkerSigninRequest;
import com.app.dto.TrekkerSigninResp;
import com.app.dto.ViewDetailsResponse;
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
		Trekker persistentTrekker = trekkerRepo.save(trekkerToAdd);
		TrekkerSignUpResponse signupresp = new TrekkerSignUpResponse();
        if(persistentTrekker!= null) {
        	signupresp.setStatus(true);
        }
        else {
        	signupresp.setStatus(false);
        }
        return signupresp;
	}
	
	//SignIn for Trekker
    @Override
	public TrekkerSigninResp singInTrekker(TrekkerSigninRequest request) {
		Trekker trekker = trekkerRepo.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password"));
		return mapper.map(trekker, TrekkerSigninResp.class);
	}
	
	@Override
	public ViewDetailsResponse showPackageDetails(Long id) {
		TrekPackage packageDetails = packageRepo.findById(id).orElseThrow();
		ViewDetailsResponse viewDetails = new ViewDetailsResponse();
		mapper.map(packageDetails, viewDetails);
		TrekDetails trekDetails = packageDetails.getTrekDetails();
		mapper.map(trekDetails, viewDetails);
		viewDetails.setAirport(packageDetails.getTrekDetails().getAirport().getAirportName());
		viewDetails.setRailwayStation(packageDetails.getTrekDetails().getRailwayStation().getRailwaystationName());
		viewDetails.setSeason(packageDetails.getTrekDetails().getSeason().getSeasonName());
		viewDetails.setGrade(packageDetails.getTrekDetails().getGrade().getGradeCategory());
		
	    if (packageDetails.getTrekDetails().getImagePath() != null) {
	    try {
	       byte[] imageBytes = FileUtils.readFileToByteArray(new File(packageDetails.getTrekDetails().getImagePath()));
	       viewDetails.setImagePath(imageBytes);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    }
	    return viewDetails;
	}
	
	//Update profile for Trekker
	@Override
	public Trekker updateTrekerDetails(Long Id, Trekker trekker) {
	    Trekker newTrekker = trekkerRepo.findById(Id)
	            .orElseThrow(() -> new EntityNotFoundException("User not found"));

	    if(trekker.getState().getStateName() != null){
	    	State newState=stateRepo.findByStateName(trekker.getState().getStateName());
	    	newTrekker.setState(newState);
	    }else {
	    	newTrekker.setFirstname(trekker.getFirstname());
    	    newTrekker.setLastname(trekker.getLastname());
    	    newTrekker.setBirthDate(trekker.getBirthDate());
    	    newTrekker.setMobileno(trekker.getMobileno());
	    }
	    
	    if(trekker.getCity().getCityName()!=null){
	    	City newCity=cityRepo.findByCityName(trekker.getCity().getCityName());
	    	newTrekker.setCity(newCity);
	    }else {
	    	newTrekker.setFirstname(trekker.getFirstname());
    	    newTrekker.setLastname(trekker.getLastname());
    	    newTrekker.setBirthDate(trekker.getBirthDate());
    	    newTrekker.setMobileno(trekker.getMobileno());
	    }
	    
	    if(trekker.getCountry().getCountryName()!=null){
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
		public BookPack showBookedPack(Long id) {
			 Optional<TrekPackage> packageOptional = packageRepo.findById(id);
			    if (packageOptional.isPresent()) {
			    	TrekPackage bk = packageOptional.get();
			        return mapper.map(bk, BookPack.class);
			    }
			    return null; 
		}
		
		@Override
		public Long findTrekkerIdByEmail(GetTrekkerIdByEmailRequest dtobj) {
			System.out.println(dtobj.getEmail());
			Trekker trekker = trekkerRepo.findByEmail(dtobj.getEmail());
			return trekker.getId();
		}

		@Override
		public int showPaymentDetails(String payment, String no) {
			int paymentInt=Integer.parseInt(payment);
			int noInt=Integer.parseInt(no);
			int paymentdetails=paymentInt * noInt;
			return paymentdetails;
		}	
		
		@Override
		public String changepassword(ChangePasswordResponse response) {
			Trekker trekker = trekkerRepo.findByEmail(response.getEmail());
			
			if(response.getPassword().equals(trekker.getPassword()))
			{
				trekker.setPassword(response.getNewPassword());
				trekkerRepo.save(trekker);
				return("Password change successfully!");
			}
			else
			{
				return("Old password is incorrect");
			}
		}
}
