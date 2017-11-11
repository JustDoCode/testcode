package com.cpgps.canbus.repository.service;

import com.cpgps.canbus.repository.entity.info.CarPostRoadEntity;
/**
 * 车辆邮路查询接口
 * @author lihuan@e6yun.com 2017年7月12日
 *
 */
public interface CarPostRoadService {
	public CarPostRoadEntity findByBcdh(int bcdh);
}
