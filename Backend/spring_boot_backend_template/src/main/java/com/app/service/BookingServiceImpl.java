package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.BookingRepository;
import com.app.dao.PackageRepository;
import com.app.dao.TrekkerRespository;
import com.app.dto.GetBookingDetailsResponse;
import com.app.dto.ViewPackageForNameDTO;
import com.app.pojos.Booking;
import com.app.pojos.TrekPackage;
import com.app.pojos.Trekker;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
    private BookingRepository bookingRepo;
    
    @Autowired
    private TrekkerRespository trekkerRepo;
    
    @Autowired
    private PackageRepository packageRepo;
    
	@Override
	public String bookPackageForTrekker(Long trekkerId, Long packageId) {
		Trekker trekker = trekkerRepo.findById(trekkerId)
				.orElseThrow(() -> new EntityNotFoundException("Trekker not found"));
        TrekPackage packeges = packageRepo.findById(packageId)
        		.orElseThrow(() -> new EntityNotFoundException("Package not found"));
        
        Booking booking = new Booking();
        booking.setTrekker(trekker);
        booking.setPackege(packeges);
        bookingRepo.save(booking);
        return "Booking Details Added";
	}
	
	@Override
	public List<GetBookingDetailsResponse> listBookingDetails() {
	    List<Booking> bookings = bookingRepo.findAll();

	    List<GetBookingDetailsResponse> getAllBooking = bookings.stream()
	        .map(booking -> {
	            GetBookingDetailsResponse obj = new GetBookingDetailsResponse();
	            obj.setFirstname(booking.getTrekker().getFirstname());
	            obj.setLastname(booking.getTrekker().getLastname());
	            obj.setPackageName(booking.getPackege().getPackageName());
	            obj.setId(booking.getId());
	            obj.setDate(booking.getPackege().getDate());
	            return obj;
	        })
	        .collect(Collectors.toList());
	    return getAllBooking;
	}

	@Override
	public TrekPackage getBookingsForPackage(Long packageId) {
		TrekPackage packages = packageRepo.findById(packageId).orElseThrow(() -> new EntityNotFoundException("Package not found"));
        return packages;
	}
	
}
