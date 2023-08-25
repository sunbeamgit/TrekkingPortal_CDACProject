package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Booking;
import com.app.pojos.Trekker;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	    List<Booking> findByTrekker(Long trekkerid);

}
