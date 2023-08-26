package com.app.dto;

import com.app.pojos.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetTrekNameResponse extends BaseEntity{
	private String trekName;
}
