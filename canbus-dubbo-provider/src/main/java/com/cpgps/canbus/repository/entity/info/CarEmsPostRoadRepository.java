package com.cpgps.canbus.repository.entity.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cpgps.canbus.repository.entity.info.CarEmsPostRoadEntity;

/**
 * 
 * @author lihuan@e6yun.com 2017年7月12日
 *
 */
public interface CarEmsPostRoadRepository extends JpaRepository<CarEmsPostRoadEntity, Long> , JpaSpecificationExecutor<CarEmsPostRoadEntity>{

	
}
