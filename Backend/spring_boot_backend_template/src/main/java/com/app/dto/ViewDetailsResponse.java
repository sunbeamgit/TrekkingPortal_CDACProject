package com.app.dto;
import java.time.LocalDate;
import com.app.enum_classes.ServiceType;
import com.app.pojos.BaseEntity;
import com.app.pojos.Guide;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDetailsResponse extends BaseEntity{
	private String packageName;
	private float priceperPerson;
	private String duration;
	private String meals;
	private String stay;
	private LocalDate date;
	private String itinerary;
	
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
	private String history;
	private byte[] imagePath;

	private Guide guide;
	private String status;
	private ServiceType serviceType;
}
