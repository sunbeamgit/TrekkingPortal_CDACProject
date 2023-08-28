package com.app.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pojos.OtpRecord;
@Repository
public interface OtpRepository extends JpaRepository<OtpRecord,Long>{

	Optional<OtpRecord> findLatestValidOTPByEmail(String email);
	    
	    @Modifying
	    @Transactional
	    @Query("DELETE FROM OtpRecord WHERE timestamp < CURRENT_TIMESTAMP")
	    void deleteExpiredRecords();
}
