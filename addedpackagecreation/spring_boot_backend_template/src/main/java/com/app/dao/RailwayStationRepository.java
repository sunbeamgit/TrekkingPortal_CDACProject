package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.RailwayStation;

public interface RailwayStationRepository extends JpaRepository<RailwayStation,Long>{
	RailwayStation findByRailwaystationName(String railwaystationName);
}
