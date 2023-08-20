package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="grade")
@Getter
@Setter
@NoArgsConstructor
public class Grade extends BaseEntity{
	@Column(name="grade_category")
	private String gradeCategory;
}
