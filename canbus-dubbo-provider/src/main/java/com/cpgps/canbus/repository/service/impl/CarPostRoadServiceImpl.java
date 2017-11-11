package com.cpgps.canbus.repository.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpgps.canbus.repository.entity.info.CarPostRoadEntity;
import com.cpgps.canbus.repository.entity.info.CarPostRoadRepository;
import com.cpgps.canbus.repository.service.CarPostRoadService;
/**
 * 车辆邮路信息查询接口实现
 * @author lihuan@e6yun.com 2017年7月12日
 *
 */
@Service
public class CarPostRoadServiceImpl implements CarPostRoadService {

	@Autowired
	private CarPostRoadRepository carPostRoadRepository;
	
	@Override
	public CarPostRoadEntity findByBcdh(int bcdh) {
		return carPostRoadRepository.findOne((long)bcdh);
	}

}
