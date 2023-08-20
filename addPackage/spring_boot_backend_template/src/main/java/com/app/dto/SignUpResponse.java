package com.app.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.app.pojos.City;
import com.app.pojos.Country;
import com.app.pojos.State;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpResponse {
	private String agencyName;
	private String agencyOwner;
	private String email;
}
