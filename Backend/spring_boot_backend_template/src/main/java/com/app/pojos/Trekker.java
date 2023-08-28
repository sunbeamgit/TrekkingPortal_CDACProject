package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "trekker")
@Getter
@Setter
@NoArgsConstructor
public class Trekker extends BaseEntity{
	@Column(name = "first_name", length = 20)
	private String firstname;

	@Column(name = "last_name", length = 20)
	private String lastname;

	@Email
	@Column(name = "email", length = 50,unique=true)
	private String email;

	@Column(length=25)
	private String password;

	@Column(name = "birthDate")
	private LocalDate birthDate;

	@Column(name = "mobile", length = 10)
	private long mobileno;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "state_id") //FK col name
	private State state;

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id") //FK col name
	private City city;

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id") //FK col name
	private Country country;

	private String gender;

	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "trekker_package_id") //FK col name
	private List<Booking> bookings = new ArrayList<>();

	@Override
	public String toString() {
	return "Trekker [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password
			+ ", DOB=" + birthDate + ", mobileno=" + mobileno + ", state=" + state + ", city=" + city + ", country=" + country
			+ "]";
}
}

