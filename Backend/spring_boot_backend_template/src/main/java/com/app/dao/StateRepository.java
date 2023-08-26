package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.State;

public interface StateRepository extends JpaRepository<State,Long> {
	  State findByStateName(String stateName);	
}
