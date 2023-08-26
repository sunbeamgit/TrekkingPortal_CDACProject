package com.app.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AdminSignInRequest {
//	@NotBlank(message="Email must be supplied")
//	@Length(min=4,max=20,message="")
//	@Email(message="Invalid Email")
	private String email;
	
//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", 
//			message = "Blank or Invalid password format")
	private String password;
}
