package com.app.dto;

import com.app.pojos.Airport;
import com.app.pojos.Grade;
import com.app.pojos.RailwayStation;
import com.app.pojos.Season;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AddTrekRequest {
	private String trekName;
	
	private float maxAltitude;
	
	private float trekKilometer;
	
	private String region;
	
	private String location;
	
	private String baseCamp;
	
	private String suitableFor;
	
	private Airport airport;
	
	private RailwayStation railwayStation;

	private Season season;
	
	private Grade grade;
	
	private String history;
	
	private String imagePath;

	public AddTrekRequest(String trekName, float maxAltitude, float trekKilometer, String region, String location,
			String baseCamp, String suitableFor, Airport airport, RailwayStation railwayStation, Season season,
			Grade grade, String history) {
		super();
		this.trekName = trekName;
		this.maxAltitude = maxAltitude;
		this.trekKilometer = trekKilometer;
		this.region = region;
		this.location = location;
		this.baseCamp = baseCamp;
		this.suitableFor = suitableFor;
		this.airport = airport;
		this.railwayStation = railwayStation;
		this.season = season;
		this.grade = grade;
		this.history = history;
	}
	
	
	
	
}
