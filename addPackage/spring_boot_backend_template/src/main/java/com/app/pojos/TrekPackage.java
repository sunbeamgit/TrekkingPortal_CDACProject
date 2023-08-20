package com.app.pojos;

import java.time.LocalDate;
import com.app.enum_classes.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="package")
@Getter
@Setter
@NoArgsConstructor
public class TrekPackage extends BaseEntity{
	@Column(name="pacakage_name")
	private String packageName;
	
	@Column(name="priceper_person")
	private float priceperPerson;
	
	@Column(name = "duration", length = 20)
	private String duration;

	@Column(name = "meals")
	private String meals;

	@Column(name = "stay")
	private  String stay;

	@Column(name = "date")
	private LocalDate date;
	
	@Lob
	@Column(name="itinerary")
	private String itinerary;
	
	@ManyToOne
	@JoinColumn(name="trek_details_id")
	private TrekDetails trekDetails;
	
	@ManyToOne
	@JoinColumn(name="guide_id")
	private Guide guide;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="service_type")
	private ServiceType serviceType;
}
