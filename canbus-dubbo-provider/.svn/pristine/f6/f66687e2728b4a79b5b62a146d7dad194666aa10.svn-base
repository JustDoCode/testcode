package com.cpgps.canbus.repository.entity.can;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAnalysisRepository extends JpaRepository<UserAnalysisEntity, Long> {

	@Query("select u from UserAnalysisEntity u where u.bcdh = :bcdh and u.jsydm = :jsydm ")
	UserAnalysisEntity findByBcdhAndJsydm(int bcdh,String jsydm);

}