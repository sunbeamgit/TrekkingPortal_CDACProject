package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Guide;
public interface GuideRepository extends JpaRepository<Guide,Long>{
	Guide findByGuideName(String guideName);
}
