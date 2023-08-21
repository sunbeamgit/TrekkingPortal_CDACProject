package com.app.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TrekDetailsRepository;
import com.app.dto.GetPartialTrekResponse;
import com.app.pojos.TrekDetails;

@Service
@Transactional
public class HomeServiceImpl implements HomeService{
	@Autowired
    private TrekDetailsRepository trekDetailsRepo;
	
    @Autowired
    private ModelMapper mapper;
	
	@Override
	public List<GetPartialTrekResponse> getPartialTrekDetails(){
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
}
