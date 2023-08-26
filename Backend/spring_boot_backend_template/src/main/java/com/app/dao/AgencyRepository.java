package com.app.dao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Agency;


public interface AgencyRepository extends JpaRepository<Agency, Long> {
	Optional<Agency> findByEmailAndPassword(String email,String password);
}
