package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking extends BaseEntity{
	    @ManyToOne
	    @JoinColumn(name = "trekker_id")
	    private Trekker trekker;
	    
	    @ManyToOne
	    @JoinColumn(name = "package_id")
	    private TrekPackage packege;
}


