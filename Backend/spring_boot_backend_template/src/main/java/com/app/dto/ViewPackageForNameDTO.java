package com.app.dto;

import com.app.pojos.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewPackageForNameDTO extends BaseEntity{
	
	private String packageName;

}
