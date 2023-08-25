package com.app.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrekkerSignUpResponse {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String cityName;
	private String stateName;
	private String countryName;
	private LocalDate birthDate;
	private long mobileno;
	private String gender;
}
