package com.cpgps.canbus.repository.entity.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 车辆邮路JPA接口
 * @author lihuan@e6yun.com 2017年7月12日
 *
 */
public interface CarPostRoadRepository extends JpaRepository<CarPostRoadEntity, Long> , JpaSpecificationExecutor<CarPostRoadEntity>{
	
}
