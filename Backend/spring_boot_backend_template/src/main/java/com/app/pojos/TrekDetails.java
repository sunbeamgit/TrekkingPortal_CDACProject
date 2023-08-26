package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="trek_details")
@Getter
@Setter
@NoArgsConstructor
public class TrekDetails extends BaseEntity{
	@Column(name="trek_name",length=40,unique=true)
	private String trekName;
	
	@Column(name="max_altitude")
	private float maxAltitude;
	
	@Column(name="trek_kilometer")
	private float trekKilometer;
	
	@Column(name="region")
	private String region;
	
	@Column(name="location")
	private String location;
	
	@Column(name="base_camp")
	private String baseCamp;
	
	@Column(name="suitable_for")
	private String suitableFor;
	
	@ManyToOne
	@JoinColumn(name = "airport_id")
	private Airport airport;
	
	@ManyToOne
	@JoinColumn(name = "railwaystation_id")
	private RailwayStation railwayStation;
	
	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;
	
	@ManyToOne
	@JoinColumn(name = "grade_id")
	private Grade grade;
	
	@Lob
	@Column(name="history")
	private String history;
	
	@Column(name="image_path")
	private String imagePath;
}
