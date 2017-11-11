package com.cpgps.canbus.repository.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpgps.canbus.repository.entity.can.CarAnalysisEntity;
import com.cpgps.canbus.repository.entity.can.CarAnalysisRepository;
import com.cpgps.canbus.repository.service.CarAnalysisService;
/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
@Service
public class CarAnalysisServiceImpl implements CarAnalysisService {
	@Autowired
    private CarAnalysisRepository carAnalysisRepository;

	@Override
	public void save(CarAnalysisEntity carAnalysisEntity) {
		carAnalysisRepository.save(carAnalysisEntity);

	}

}
