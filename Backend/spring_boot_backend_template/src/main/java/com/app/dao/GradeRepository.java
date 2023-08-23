package com.app.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Grade;

public interface GradeRepository extends JpaRepository<Grade,Long> {
	Grade findByGradeCategory(String gradeCategory);
}
