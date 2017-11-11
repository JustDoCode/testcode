package com.cpgps.canbus.dubbo.service;

import java.util.List;

import com.cpgps.canbus.repository.entity.info.CarEmsPostRoadEntity;

/**
 * dubbo接口 车辆EMS-邮路接口
 * @author lihuan@e6yun.com 
 * Date 2017年7月12日
 */
public interface CarEmsPostRoadService {
	
	/**
	 * 封装车辆EMS邮路数据
	 */
	public List<CarEmsPostRoadEntity> listCarEmsPostRoadEntity(String bcdh);
}
