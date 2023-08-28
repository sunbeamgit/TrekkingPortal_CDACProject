package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter //to be used during ser.
@Setter
public class EmailResponse {
	public EmailResponse(String email, String message) {
		 this.email=email;
		 this.message=message;
		}
	private String email;
	private String message;
}
