package com.app.dto;
import java.time.LocalDate;
import com.app.pojos.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetBookingDetailsResponse extends BaseEntity{
	private String firstname;
	private String lastname;
	private String packageName;
	private LocalDate date;
}
