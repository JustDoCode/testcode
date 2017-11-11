package com.cpgps.canbus.dubbo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cpgps.canbus.common.model.MessageBean;
import com.cpgps.canbus.common.model.realtime.CanStaticData;
import com.cpgps.canbus.common.ots.TableStoreManager;
import com.cpgps.canbus.common.ots.TableStorePaginationReadManager;
import com.cpgps.canbus.common.utils.Const;
import com.cpgps.canbus.common.utils.DateUtil;
import com.cpgps.canbus.dubbo.model.CanAlarmDataBean;
import com.cpgps.canbus.dubbo.model.CanStaticDataBean;
import com.cpgps.canbus.dubbo.service.CanStaticDataBeanService;
import com.cpgps.canbus.repository.entity.can.DriverTaskEntity;
/**
 * canbus静态数据
 * @author wangshuai@e6yun.com
 *
 */
@Service
public class CanStaticDataBeanServiceImpl implements CanStaticDataBeanService {
	static Logger logger = LoggerFactory.getLogger(CanStaticDataBeanServiceImpl.class);
	@Autowired
	private TableStoreManager tableStoreManager;
	@Autowired
	private TableStorePaginationReadManager tableStorePaginationReadManager;
	@Override
	public CanStaticDataBean getCanStaticDataBean(String cldm, Date startTime, Date endTime) {
		List<MessageBean> canStaticMessageBeanList = tableStoreManager.getRange(cldm, DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_CANSTATIC, new AtomicInteger(0));
		if(canStaticMessageBeanList.size()<1) return null;
		CanStaticDataBean canStaticDataBean = new CanStaticDataBean();
		canStaticDataBean.setVehicleCode(cldm);
		getCanStaticDataList( canStaticMessageBeanList);
		return canStaticDataBean;
	}
	
	
	/**
	 * 从can数据中分析统计
	 * @param CanStaticDataBean
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private List<CanStaticDataBean> getCanStaticDataList(List<MessageBean> canStaticMessageBeanList) {
		 List<CanStaticDataBean> list = new  ArrayList<CanStaticDataBean>();
		for (MessageBean messageBean : canStaticMessageBeanList) {
			CanStaticData canStaticData = messageBean.getCanStaticData();
			CanStaticDataBean canStaticDataBean = new CanStaticDataBean();
			canStaticDataBean.setEquipNum(messageBean.getEquipNum());
			canStaticDataBean.setAirFlowRate(canStaticData.getAirFlowRate());
			canStaticDataBean.setAirIntakeTemp(canStaticData.getAirIntakeTemp());
			canStaticDataBean.setAirPressure(canStaticData.getAirPressure());
			canStaticDataBean.setBatVoltage(canStaticData.getBatVoltage());
			canStaticDataBean.setCarAmbientTemp(canStaticData.getCarAmbientTemp());
			canStaticDataBean.setCarTotalRunTime(canStaticData.getCarTotalRunTime());
			canStaticDataBean.setCoolantTemp(canStaticData.getCoolantTemp());
			canStaticDataBean.setCreateTime(messageBean.getCreateTime());
			canStaticDataBean.setDashTotalOdermeter(canStaticData.getDashTotalOdermeter());
			canStaticDataBean.setDashTotalOil(canStaticData.getDashTotalOil());
			canStaticDataBean.setEngineLoad(canStaticData.getEngineLoad());
			canStaticDataBean.setEngineRunTime(canStaticData.getEngineRunTime());
			canStaticDataBean.setEnginSpeed(canStaticData.getEnginSpeed());
			canStaticDataBean.setEquipNum(messageBean.getEquipNum());
			canStaticDataBean.setFulmeter(canStaticData.getFulmeter());
			canStaticDataBean.setIgnitionAngle(canStaticData.getIgnitionAngle());
			canStaticDataBean.setInfoType(messageBean.getInfoType());
			canStaticDataBean.setIntakeTubPressure(canStaticData.getIntakeTubPressure());
			canStaticDataBean.setOdometer(canStaticData.getOdometer());
			canStaticDataBean.setOdometerType(canStaticData.getOdometerType());
			canStaticDataBean.setOilCorrection(canStaticData.getOilCorrection());
			canStaticDataBean.setOilPedalPosition(canStaticData.getOilPedalPosition());
			canStaticDataBean.setOilPressure(canStaticData.getOilPressure());
			canStaticDataBean.setReciveTime(messageBean.getReciveTime());
			canStaticDataBean.setRemainOil(canStaticData.getRemainOil());
			canStaticDataBean.setSpeed(canStaticData.getSpeed());
			canStaticDataBean.setTrouCodeNum(canStaticData.getTrouCodeNum());
			canStaticDataBean.setTrouLightStatus(canStaticData.getTrouLightStatus());
			canStaticDataBean.setTrouRunOdemeter(canStaticData.getTrouRunOdemeter());
			canStaticDataBean.setValvePositionSensor(canStaticData.getValvePositionSensor());
			canStaticDataBean.setVehicleCode(messageBean.getVehicleCode());
			canStaticDataBean.setVehicleId(messageBean.getVehicleId());
			list.add(canStaticDataBean);
		}
		return list;
	}
	/**
	 * 获取静态数据分页
	 * @param messageBeanMap
	 * @return
	 */
	private Map<List<String>, Integer> getCanStaticDataListByPage(Map<List<MessageBean>, Integer> messageBeanMap) {
		Map<List<String>, Integer> resultMap = new HashMap<>();
		List<String> list = new ArrayList<String>();
		List<MessageBean> canStaticDataBeanList = new ArrayList<MessageBean>();
		int totalSize = 0;
		for (Entry<List<MessageBean>, Integer> entry : messageBeanMap.entrySet()) {
			canStaticDataBeanList = entry.getKey();
			totalSize = entry.getValue();
		}
		for (MessageBean messageBean : canStaticDataBeanList) {
			CanStaticData canStaticData = messageBean.getCanStaticData();
			CanStaticDataBean canStaticDataBean = new CanStaticDataBean();
			canStaticDataBean.setEquipNum(messageBean.getEquipNum());
			canStaticDataBean.setAirFlowRate(canStaticData.getAirFlowRate());
			canStaticDataBean.setAirIntakeTemp(canStaticData.getAirIntakeTemp());
			canStaticDataBean.setAirPressure(canStaticData.getAirPressure());
			canStaticDataBean.setBatVoltage(canStaticData.getBatVoltage());
			canStaticDataBean.setCarAmbientTemp(canStaticData.getCarAmbientTemp());
			canStaticDataBean.setCarTotalRunTime(canStaticData.getCarTotalRunTime());
			canStaticDataBean.setCoolantTemp(canStaticData.getCoolantTemp());
			canStaticDataBean.setCreateTime(messageBean.getCreateTime());
			canStaticDataBean.setDashTotalOdermeter(canStaticData.getDashTotalOdermeter());
			canStaticDataBean.setDashTotalOil(canStaticData.getDashTotalOil());
			canStaticDataBean.setEngineLoad(canStaticData.getEngineLoad());
			canStaticDataBean.setEngineRunTime(canStaticData.getEngineRunTime());
			canStaticDataBean.setEnginSpeed(canStaticData.getEnginSpeed());
			canStaticDataBean.setEquipNum(messageBean.getEquipNum());
			canStaticDataBean.setFulmeter(canStaticData.getFulmeter());
			canStaticDataBean.setIgnitionAngle(canStaticData.getIgnitionAngle());
			canStaticDataBean.setInfoType(messageBean.getInfoType());
			canStaticDataBean.setIntakeTubPressure(canStaticData.getIntakeTubPressure());
			canStaticDataBean.setOdometer(canStaticData.getOdometer());
			canStaticDataBean.setOdometerType(canStaticData.getOdometerType());
			canStaticDataBean.setOilCorrection(canStaticData.getOilCorrection());
			canStaticDataBean.setOilPedalPosition(canStaticData.getOilPedalPosition());
			canStaticDataBean.setOilPressure(canStaticData.getOilPressure());
			canStaticDataBean.setReciveTime(messageBean.getReciveTime());
			canStaticDataBean.setRemainOil(canStaticData.getRemainOil());
			canStaticDataBean.setSpeed(canStaticData.getSpeed());
			canStaticDataBean.setTrouCodeNum(canStaticData.getTrouCodeNum());
			canStaticDataBean.setTrouLightStatus(canStaticData.getTrouLightStatus());
			canStaticDataBean.setTrouRunOdemeter(canStaticData.getTrouRunOdemeter());
			canStaticDataBean.setValvePositionSensor(canStaticData.getValvePositionSensor());
			canStaticDataBean.setVehicleCode(messageBean.getVehicleCode());
			canStaticDataBean.setVehicleId(messageBean.getVehicleId());
			list.add(JSONObject.toJSONString(canStaticDataBean));
		}
		resultMap.put(list, totalSize);
		return resultMap;
	}
	/**
	 * 分页查询静态数据
	 */
	@Override
	public Map<List<String>, Integer> getCanStaticDataBeanByPage(String cldm, Date startTime, Date endTime, int offset, int pageSize) {
		
		Map<List<MessageBean>, Integer> canAlarmDataBeanListMap = tableStorePaginationReadManager.getRange(cldm, DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_CANSTATIC, offset, pageSize);
		Map<List<String>, Integer> map = getCanStaticDataListByPage(canAlarmDataBeanListMap);
		return map;
	}


}
