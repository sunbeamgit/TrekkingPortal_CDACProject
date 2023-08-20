package com.app.dto;

import com.app.pojos.Airport;
import com.app.pojos.Grade;
import com.app.pojos.RailwayStation;
import com.app.pojos.Season;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddTrekResponse {
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
	
	
}
