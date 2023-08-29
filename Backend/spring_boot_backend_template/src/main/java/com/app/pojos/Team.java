package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
public class Team extends BaseEntity{
	@Column(name = "team_name", length = 30)
	private String name;
	
	@Email
	@Column(name = "team_email", length = 40)
	private String email;
	
	@Column(name = "team_mb", length = 10)
	private String mobile;
	
	@Column(name = "team_insta", length = 30)
	private String insta;
	
	@Column(name = "imag", length = 50)
	private String imag;
}
