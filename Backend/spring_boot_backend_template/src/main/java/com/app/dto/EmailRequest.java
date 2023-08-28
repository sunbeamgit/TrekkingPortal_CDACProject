package com.app.dto;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter //to be used during ser.
@Setter
public class EmailRequest {
@Email(message = "Invalid Email")
	
	private String email;
}
