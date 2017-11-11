package com.cpgps.canbus.repository.entity.can;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CanbusAlarmTypeRepository extends JpaRepository<CanbusAlarmTypeEntity, Long> , JpaSpecificationExecutor<CanbusAlarmTypeEntity>  {
	CanbusAlarmTypeEntity findByBjlxdm(String bjlxdm);


}