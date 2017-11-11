package com.cpgps.canbus.repository.entity.can;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverTaskRepository extends JpaRepository<DriverTaskEntity, Long> {

	List<DriverTaskEntity> findByBcdh(int bcdh);
	
	@Query("select u from DriverTaskEntity u where u.gzkssj between :gzkssj and :gzjssj ")
	List<DriverTaskEntity> getDriverList(@Param("gzkssj")Date gzkssj,@Param("gzjssj") Date gzjssj );
	
	@Query("select u from DriverTaskEntity u where u.gzkssj between :gzkssj and :gzjssj and u.jsydm in  (:jsydmList) ")
	List<DriverTaskEntity> getDriverListByJsydm(@Param("gzkssj")Date gzkssj,@Param("gzjssj") Date gzjssj,@Param("jsydmList") String[] jsydmList );


	@Query("select u from DriverTaskEntity u where u.gzkssj between :gzkssj and :gzjssj ")
	List<DriverTaskEntity> getDriverListByDate(@Param("gzkssj")Date gzkssj,@Param("gzjssj") Date gzjssj );
	
	@Query("select u from DriverTaskEntity u where u.gzkssj between :gzkssj and :gzjssj  and c_cldm=:cldm")
	List<DriverTaskEntity> getDriverListByDateAndCldm(@Param("gzkssj")Date gzkssj,@Param("gzjssj") Date gzjssj,@Param("cldm") String cldm  );

}