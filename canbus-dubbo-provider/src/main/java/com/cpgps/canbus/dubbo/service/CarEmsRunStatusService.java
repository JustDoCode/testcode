package com.cpgps.canbus.dubbo.service;

import java.util.Date;

import com.cpgps.canbus.repository.entity.can.CarSectionAnalysisEntity;

/**
 * canbus车辆EMS运行状态接口
 * @author LiHuan
 *
 */
public interface CarEmsRunStatusService {
	public CarSectionAnalysisEntity getCarEmsRunStatusEntity(String vehicleNo,Date startTime, Date endTime);
}
