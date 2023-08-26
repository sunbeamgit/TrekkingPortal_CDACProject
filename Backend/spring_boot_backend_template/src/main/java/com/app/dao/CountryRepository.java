package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Country;

public interface CountryRepository extends JpaRepository<Country,Long> {
	Country findByCountryName(String countryName);
}
