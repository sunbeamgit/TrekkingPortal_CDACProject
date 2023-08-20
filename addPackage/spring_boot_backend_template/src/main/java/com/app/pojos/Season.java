package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="season")
@Getter
@Setter
@NoArgsConstructor
public class Season extends BaseEntity {
	@Column(name="season_name")
	private String seasonName;
	
	@Column(name="season_duration")
	private String seasonDuration;
}