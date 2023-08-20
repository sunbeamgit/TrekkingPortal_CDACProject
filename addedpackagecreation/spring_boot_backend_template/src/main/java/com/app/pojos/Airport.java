package com.app.pojos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="airport")
@Getter
@Setter
@NoArgsConstructor
public class Airport extends BaseEntity{
	@Column(name="airport_name")
	private String airportName;
}
