package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "home")
@Getter
@Setter
@NoArgsConstructor
public class Home extends BaseEntity{
	@Lob
	@Column(name = "core_values")
	private String corevalue;
	
	@Lob
	@Column(name = "info")
	private String info;
	
	@Email
	@Column(name = "email", length = 40)
	private String email;
	
	@Column(name = "mobile", length = 10)
	private String mobile;
	
	@Column(name = "insta", length = 30)
	private String insta;
	
	
	 @OneToMany(fetch = FetchType.EAGER)
	 @JoinColumn(name = "team_id")
	    private List<Team> teams = new ArrayList<>();


	public Home(String corevalue, String info, @Email String email, String mobile, String insta, List<Team> teams) {
		super();
		this.corevalue = corevalue;
		this.info = info;
		this.email = email;
		this.mobile = mobile;
		this.insta = insta;
		this.teams = teams;
	}

}
