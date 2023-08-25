package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="city")
@Getter
@Setter
@NoArgsConstructor
public class City extends BaseEntity{
	@Column(name="city_name" ,length=30,unique=true)
	private String cityName;

	@Override
	public String toString() {
		return "City [cityName=" + cityName + "]";
	}
}
