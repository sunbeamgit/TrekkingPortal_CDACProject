package com.app.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class AddTrekRequest {
	private String trekName;
	private float maxAltitude;
	private float trekKilometer;
	private String region;
	private String location;
	private String baseCamp;
	private String suitableFor;
	private String airportName;
	private String railwayStationName;
	private String seasonName;
	private String gradeCategory;
	private String history;
	private String imagePath;
}
