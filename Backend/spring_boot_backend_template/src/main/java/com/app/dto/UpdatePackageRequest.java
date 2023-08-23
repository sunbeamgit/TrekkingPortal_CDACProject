package com.app.dto;
import java.time.LocalDate;
import com.app.pojos.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePackageRequest extends BaseEntity{
	private String packageName;
	private float priceperPerson;
	private String duration;
	private String meals;
	private String stay;
	private LocalDate date;
	private String itinerary;
	private String trekDetails;
	private String guide;
	private String status;
	private String serviceType;
}
