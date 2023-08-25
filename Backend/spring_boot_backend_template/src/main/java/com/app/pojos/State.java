package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="state")
@Getter
@Setter
@NoArgsConstructor
public class State extends BaseEntity{
	@Column(name="state_name",length=30,unique=true)
	private String stateName;

	@Override
	public String toString() {
		return "State [stateName=" + stateName + "]";
	}
}
