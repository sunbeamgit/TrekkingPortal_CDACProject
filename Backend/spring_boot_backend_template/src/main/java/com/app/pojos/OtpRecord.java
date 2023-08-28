package com.app.pojos;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="otp_record")
@Getter
@Setter
@NoArgsConstructor
public class OtpRecord {
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "otp", length = 20)
	private String otp;
	
	@Column(name = "time_stamp")
	private LocalDateTime timestamp;
	
	
}
