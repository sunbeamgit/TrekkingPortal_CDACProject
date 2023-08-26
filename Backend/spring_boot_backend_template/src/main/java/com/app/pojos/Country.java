package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="country")
@Getter
@Setter
@NoArgsConstructor
public class Country extends BaseEntity{
	@Column(name="country_name",length=30,unique=true)
	private String countryName;

	@Override
	public String toString() {
		return "Country [countryName=" + countryName + "]";
	}
}
