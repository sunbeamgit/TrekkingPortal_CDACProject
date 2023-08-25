package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Booking;
import com.app.pojos.TrekPackage;

public interface BookingService {
	
	
	    void bookPackageForTrekker(Long packageId, Long trekkerId);
	    TrekPackage getBookingsForPackage(Long packageId);
	
}
