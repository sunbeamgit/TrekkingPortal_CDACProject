package com.app.dto;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpRequest {
	@Length(min=4,max=30,message = "Invalid agency name length!!!")
	private String agencyName;
	
	@Length(min=4,max=30,message = "Invalid agency owner name length!!!")
	private String agencyOwner;
	
	private String email;
	private String password;
	
	@NotBlank(message = "city must be supplied")
	private String cityName;
	private String stateName;
	private String countryName;
}


