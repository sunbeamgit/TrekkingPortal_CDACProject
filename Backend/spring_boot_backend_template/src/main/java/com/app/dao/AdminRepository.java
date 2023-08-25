package com.app.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	Optional<Admin> findByEmailAndPassword(String email,String password);
}
