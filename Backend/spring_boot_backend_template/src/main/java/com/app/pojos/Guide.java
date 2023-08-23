package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="guide")
@Setter
@Getter
@NoArgsConstructor
public class Guide extends BaseEntity{
	@Column(name = "guide_name", length = 20)
	private String guideName;

	@Column(name = "experience")
	private float experience;
	
	@Column(name = "no_of_trek")
	private int noOfTrek;
}
