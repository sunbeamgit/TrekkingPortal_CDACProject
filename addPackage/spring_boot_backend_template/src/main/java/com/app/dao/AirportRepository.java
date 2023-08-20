package com.app.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Airport;

public interface AirportRepository extends JpaRepository<Airport,Long>{
	Airport findByAirportName(String airportName);
}
