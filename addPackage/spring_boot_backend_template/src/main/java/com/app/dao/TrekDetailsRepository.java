package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.TrekDetails;

public interface TrekDetailsRepository extends JpaRepository<TrekDetails, Long>{
	TrekDetails findByTrekName(String trekDetails);
}
