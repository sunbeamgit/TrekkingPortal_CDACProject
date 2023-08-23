package com.app.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.pojos.Season;

public interface SeasonRepository extends JpaRepository<Season,Long> {
	Season findBySeasonName(String seasonName);
}
