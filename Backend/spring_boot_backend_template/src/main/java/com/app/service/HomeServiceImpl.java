package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import com.app.dao.AirportRepository;
import com.app.dao.CityRepository;
import com.app.dao.CountryRepository;
import com.app.dao.GradeRepository;
import com.app.dao.GuideRepository;
import com.app.dao.HomeRepository;
import com.app.dao.PackageRepository;
import com.app.dao.RailwayStationRepository;
import com.app.dao.SeasonRepository;
import com.app.dao.StateRepository;
import com.app.dao.TeamRepository;
import com.app.dao.TrekDetailsRepository;
import com.app.dao.TrekPackageRepository;
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
import com.app.dto.GetTrekImageResponse;
import com.app.dto.GetTrekNameResponse;
import com.app.dto.ViewPackageForNameDTO;
import com.app.pojos.Airport;
import com.app.pojos.City;
import com.app.pojos.Country;
import com.app.pojos.Grade;
import com.app.pojos.Guide;
import com.app.pojos.Home;
import com.app.pojos.RailwayStation;
import com.app.pojos.Season;
import com.app.pojos.State;
import com.app.pojos.Team;
import com.app.pojos.TrekDetails;
import com.app.pojos.TrekPackage;

@Service
@Transactional
public class HomeServiceImpl implements HomeService{
	@Autowired
    private TrekDetailsRepository trekDetailsRepo;
	
	@Autowired
	private TrekPackageRepository packageDetailsRepo;
	
    @Autowired
    private ModelMapper mapper;
    
    @Autowired 
    private CityRepository cityRepo;
    
    @Autowired 
    private StateRepository stateRepo;
	
    @Autowired 
    private CountryRepository countryRepo;
    
    @Autowired 
    private AirportRepository airportRepo;

    @Autowired 
    private RailwayStationRepository stationRepo;
    
    @Autowired 
    private SeasonRepository seasonRepo;
    
    @Autowired 
    private GradeRepository gradeRepo;
    
    @Autowired 
    private GuideRepository guideRepo;
    
    @Autowired
	private PackageRepository packageRepo;
    
    @Autowired
    private TeamRepository teamRepo;
    
    @Autowired HomeRepository homeRepo;
    
    @Override
    public List<GetPartialTrekResponse> getPartialTrekDetails() throws IOException {
        List<TrekDetails> trekDetails = trekDetailsRepo.findAll();
        return trekDetails.stream()
            .map(trek -> {
                GetPartialTrekResponse response = mapper.map(trek, GetPartialTrekResponse.class);
                response.setAirport(trek.getAirport().getAirportName());
                response.setRailwayStation(trek.getRailwayStation().getRailwaystationName());
                response.setSeason(trek.getSeason().getSeasonName());
                response.setGrade(trek.getGrade().getGradeCategory());
                return response;
            })
            .collect(Collectors.toList());
    }
    
	@Override
	public List<GetAllPackageResponse> getAllPackageDetails() throws IOException{
		List<TrekPackage> packageDetails = packageDetailsRepo.findAll();
		return packageDetails.stream()
				.map(trekpackage->{
					GetAllPackageResponse response = mapper.map(trekpackage, GetAllPackageResponse.class);
					response.setLocation(trekpackage.getTrekDetails().getLocation());
					response.setDistance(trekpackage.getTrekDetails().getTrekKilometer());
					response.setAltitude(trekpackage.getTrekDetails().getMaxAltitude());
					response.setGrade(trekpackage.getTrekDetails().getGrade().getGradeCategory());
					String imagePath = trekpackage.getTrekDetails().getImagePath();
					try {
						response.setPackageImage(FileUtils.readFileToByteArray(new File(imagePath)));
					} catch (IOException e) {
						e.printStackTrace();
					}
					return response;
				})
				.collect(Collectors.toList());	
	}
	
	@Override
    public List<byte[]> downloadTrekImage() {
        List<TrekDetails> trekDetails = trekDetailsRepo.findAll();
        List<byte[]> images = new ArrayList<>();
        
        trekDetails.forEach(trek -> {
            if (trek.getImagePath() != null) {
                try {
                    byte[] imageBytes = FileUtils.readFileToByteArray(new File(trek.getImagePath()));
                    images.add(imageBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return images;
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
	
	@Override
	public GetAirportResponse getAirpotDetails() {
	    List<Airport> airportDetails = airportRepo.findAll();
	    List<String> airportNames = new ArrayList<>();
	    GetAirportResponse resp = new GetAirportResponse();
	    
	    for (Airport airport : airportDetails) {
	        airportNames.add(airport.getAirportName());
	    }
	    resp.setAirportName(airportNames);
	    return resp;
	}

	@Override
	public GetStationResponse getStationDetails() {
		 List<RailwayStation> stationDetails = stationRepo.findAll();
		    List<String> stationNames = new ArrayList<>();
		    GetStationResponse resp = new GetStationResponse();
		    
		    for (RailwayStation station : stationDetails) {
		    	stationNames.add(station.getRailwaystationName());
		    }
		    resp.setRailwayStation(stationNames);
		    return resp;
	}

	@Override
	public GetSeasonResponse getSeasonDetails() {
		 List<Season> seasonDetails = seasonRepo.findAll();
		    List<String> seasonNames = new ArrayList<>();
		    GetSeasonResponse resp = new GetSeasonResponse();
		    
		    for (Season season : seasonDetails) {
		    	seasonNames.add(season.getSeasonName());
		    }
		    resp.setSeasonName(seasonNames);
		    return resp;	
	}

	@Override
	public GetGradeResponse getGradeDetails() {
		 List<Grade> gradeDetails = gradeRepo.findAll();
		    List<String> gradeNames = new ArrayList<>();
		    GetGradeResponse resp = new GetGradeResponse();
		    
		    for (Grade grade : gradeDetails) {
		    	gradeNames.add(grade.getGradeCategory());
		    }
		    resp.setGradeName(gradeNames);
		 return resp;	
	}
	
	//Harshda
	@Override
	public List<ViewPackageForNameDTO> getPackageByName() {
		List<TrekPackage> trekPackage = packageRepo.findAll();
		return trekPackage.stream().map(trekpackage ->{
			ViewPackageForNameDTO response = mapper.map(trekpackage, ViewPackageForNameDTO.class);
			response.setPackageName(trekpackage.getPackageName());
			return response;
		})
				.collect(Collectors.toList());
	}

	@Override
	public List<GetGuideResponse> getGuideName() {
		List<Guide> guideByName = guideRepo.findAll();
		return guideByName.stream().map(guide ->{
			GetGuideResponse response = mapper.map(guide, GetGuideResponse.class);
			response.setGuideName(guide.getGuideName());
			return response;
		})
				.collect(Collectors.toList());
	}
	

	@Override
	public List<GetTrekNameResponse> getTrekbyName() {
		List<TrekDetails> trekByName = trekDetailsRepo.findAll();
		return trekByName.stream().map(trek -> {
			GetTrekNameResponse response = mapper.map(trek,GetTrekNameResponse.class);
			response.setTrekName(trek.getTrekName());
			return response;
		})
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Team> showAllTeamInfo() {
		List<Team> teams=teamRepo.findAll();
		return teams;
	}
	
	@Override
	public Home showAdminDetails() {
		Home newhome=homeRepo.findAll().get(0);
		return newhome;
	}
}
