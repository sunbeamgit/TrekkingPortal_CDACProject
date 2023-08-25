package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="agency_signup")
@Getter
@Setter
@NoArgsConstructor
public class Agency extends BaseEntity{
	@Column(name="agency_name" ,length=30)
	private String agencyName;
	
	@Column(name="agency_owner" ,length=30)
	private String agencyOwner;
	
	@Column(length = 40, unique = true)
	private String email;
	
	@Column(length = 50)
	private String password;
	
	@ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

	public Agency(String agencyName, String agencyOwner, String email, String password) {
		super();
		this.agencyName = agencyName;
		this.agencyOwner = agencyOwner;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Agency [agencyName=" + agencyName + ", agencyOwner=" + agencyOwner + ", email=" + email + ", password="
				+ password + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}
}
