package com.app.dto;
import java.time.LocalDate;

import com.app.enum_classes.ServiceType;
import com.app.enum_classes.Status;
import com.app.pojos.BaseEntity;
import com.app.pojos.Guide;
import com.app.pojos.TrekDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllPackageResponse extends BaseEntity{
	private String packageName;
	private String duration;
	private float altitude;
	private String location;
	private String grade;
	private float distance;
	private byte[] packageImage;
}

