package com.app.service;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AdminRepository;
import com.app.dao.AirportRepository;
import com.app.dao.GradeRepository;
import com.app.dao.RailwayStationRepository;
import com.app.dao.SeasonRepository;
import com.app.dao.TrekDetailsRepository;
import com.app.dto.AddTrekRequest;
import com.app.dto.AddTrekResponse;
import com.app.dto.AdminSignInRequest;
import com.app.dto.AdminSignInResponse;
import com.app.pojos.Admin;
import com.app.pojos.Airport;
import com.app.pojos.Grade;
import com.app.pojos.RailwayStation;
import com.app.pojos.Season;
import com.app.pojos.TrekDetails;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
    private AdminRepository adminRepo;
	
	@Autowired
    private TrekDetailsRepository trekDetailsRepo;

    @Autowired
    private AirportRepository airportRepo;

    @Autowired
    private RailwayStationRepository railwaystationRepo;

    @Autowired
    private SeasonRepository seasonRepo;
    
    @Autowired
    private GradeRepository gradeRepo;
    
    @Autowired
    private ModelMapper mapper;
    
    @Value("${upload.location}")
	private String folderLocation;
    
    
	@PostConstruct
	public void init() {
		System.out.println("in init " + folderLocation);
		// chk if folder exists
		File folder = new File(folderLocation);
		if (folder.exists())
			System.out.println("folder alrdy exists !");
		else {
			folder.mkdir(); // creates a new folder
			System.out.println("created a new folder...");
		}

	}
	  
	@Override
	public AdminSignInResponse singInAdmin(AdminSignInRequest adminDTO) {
		Admin admin = 
		adminRepo.findByEmailAndPassword(adminDTO.getEmail(),adminDTO.getPassword()).
		orElseThrow(()->new ResourceNotFoundException("Invalid Email and Password"));
		return mapper.map(admin, AdminSignInResponse.class);
	}

	@Override
	public AddTrekResponse addTrekDetails(AddTrekRequest addtrekDTO, MultipartFile imageFile) throws IOException {
		System.out.println("inside add trek sevice");
		
	    Airport airport = airportRepo.findByAirportName(addtrekDTO.getAirportName());
	    RailwayStation station = railwaystationRepo.findByRailwaystationName(addtrekDTO.getRailwayStationName());
	    Season season = seasonRepo.findBySeasonName(addtrekDTO.getSeasonName());
	    Grade grade = gradeRepo.findByGradeCategory(addtrekDTO.getGradeCategory());
	   
	    TrekDetails newTrek = new TrekDetails();
	    mapper.map(addtrekDTO, newTrek);
	    newTrek.setAirport(airport);
	    newTrek.setRailwayStation(station);
	    newTrek.setSeason(season);
	    newTrek.setGrade(grade);
        
	    // save uploaded file contents in server side folder.
	    // create the path to store the file
	 	String path = folderLocation.concat(imageFile.getOriginalFilename());
	 	System.out.println("inside service showing path " + path);
	 	// FileUtils class : to read byte[] from multpart file ---> server side folder
	 	// API : public void writeByteArrayToFile(File file, byte[] data) throws
	 	// IOException
	 	FileUtils.writeByteArrayToFile(new File(path), imageFile.getBytes());
	 	// file saved successfully !
	 	// set image path in db
	 	newTrek.setImagePath(path);
	 	
	 	TrekDetails persistentTrek = trekDetailsRepo.save(newTrek);
	 	AddTrekResponse addtrekresp = new AddTrekResponse();
	        if(persistentTrek!= null) {
	        	addtrekresp.setStatus(true);
	        }
	        else {
	        	addtrekresp.setStatus(false);
	        }
	    return addtrekresp;
	}
}
