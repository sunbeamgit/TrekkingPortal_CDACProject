package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
