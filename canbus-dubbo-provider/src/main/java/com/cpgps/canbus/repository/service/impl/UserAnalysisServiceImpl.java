package com.cpgps.canbus.repository.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpgps.canbus.repository.entity.can.UserAnalysisEntity;
import com.cpgps.canbus.repository.entity.can.UserAnalysisRepository;
import com.cpgps.canbus.repository.service.UserAnalysisService;
/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
@Service
public class UserAnalysisServiceImpl implements UserAnalysisService {
	@Autowired
    private UserAnalysisRepository userAnalysisEntityRepository;

	@Override
	public void save(UserAnalysisEntity userAnalysisEntity) {
		userAnalysisEntityRepository.save(userAnalysisEntity);
	}

}
