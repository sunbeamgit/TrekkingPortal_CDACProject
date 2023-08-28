package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Trekker;

public interface TrekkerRespository extends JpaRepository<Trekker, Long>{
	Optional<Trekker> findByEmailAndPassword(String em,String pass);
	Trekker findByEmail(String email);	
}
