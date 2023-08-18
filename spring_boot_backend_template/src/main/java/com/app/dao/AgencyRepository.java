package com.app.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.pojos.Agency;


public interface AgencyRepository extends JpaRepository<Agency, Long> {
	Optional<Agency> findByEmailAndPassword(String email,String password);
	
//	@Query("SELECT DISTINCT a FROM Agency a " +
//	           "LEFT JOIN FETCH a.city " +
//	           "LEFT JOIN FETCH a.state " +
//	           "LEFT JOIN FETCH a.country")
//	List<Agency> findAllWithCityStateCountry();
}
