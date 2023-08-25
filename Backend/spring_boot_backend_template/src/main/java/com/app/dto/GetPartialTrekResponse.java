package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetPartialTrekResponse {
	private String trekName;  
	private float maxAltitude; 
	private float trekKilometer; 
	private String region;       
	private String location;      
	private String baseCamp;    
	private String suitableFor; 
	private String airport;
	private String railwayStation;
	private String season;
	private String grade;
	//private byte[] image;
	//private String image;
}
