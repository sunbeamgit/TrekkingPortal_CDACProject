package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="railway_station")
@Getter
@Setter
@NoArgsConstructor
public class RailwayStation extends BaseEntity{
	@Column(name="railwaystation_name")
	private String railwaystationName;
}