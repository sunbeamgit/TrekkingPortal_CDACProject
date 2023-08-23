package com.app.dto;
import java.time.LocalDate;
import com.app.enum_classes.ServiceType;
import com.app.enum_classes.Status;
import com.app.pojos.Guide;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetPackageResponse {
	private String packageName;
	private float priceperPerson;
	private String duration;
	private String meals;
	private String stay;
	private LocalDate date;
	private String itinerary;
	private String guide;
	private String status;
	private String serviceType;
}
