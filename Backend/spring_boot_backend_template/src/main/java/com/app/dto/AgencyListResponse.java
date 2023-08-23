package com.app.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgencyListResponse {
	private String agencyName;
	private String agencyOwner;
	private String email;
	private String cityName;
	private String stateName;
	private String countryName;
}
