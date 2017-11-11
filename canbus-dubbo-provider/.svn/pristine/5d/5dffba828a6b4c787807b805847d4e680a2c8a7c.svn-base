package com.cpgps.canbus.dubbo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.model.Row;
import com.cpgps.canbus.common.model.MessageBean;
import com.cpgps.canbus.common.model.realtime.AlarmData;
import com.cpgps.canbus.common.model.realtime.AlarmHSpeedNeutralData;
import com.cpgps.canbus.common.model.realtime.AlarmIdlingTooLongData;
import com.cpgps.canbus.common.model.realtime.AlarmOverSpeed;
import com.cpgps.canbus.common.model.realtime.AlarmSuperHighEngineSpeedData;
import com.cpgps.canbus.common.model.realtime.GpsLocation;
import com.cpgps.canbus.common.ots.TableSpaceDBConection;
import com.cpgps.canbus.common.ots.TableStoreManager;
import com.cpgps.canbus.common.ots.TableStorePaginationReadManager;
import com.cpgps.canbus.common.utils.AlarmConst;
import com.cpgps.canbus.common.utils.Const;
import com.cpgps.canbus.common.utils.DateUtil;
import com.cpgps.canbus.dubbo.model.CanAlarmDataBean;
import com.cpgps.canbus.dubbo.service.CanAlarmDataBeanService;
import com.cpgps.canbus.repository.entity.can.DriverTaskEntity;
import com.cpgps.canbus.repository.entity.can.DriverTaskRepository;
import com.cpgps.canbus.repository.entity.info.DriverEntity;

@Component
public class CanAlarmDataBeanServiceImpl implements CanAlarmDataBeanService {
	static Logger logger = LoggerFactory.getLogger(CanStaticDataBeanServiceImpl.class);
	@Autowired
	private TableSpaceDBConection tableStoreManager;
	@Autowired
	private TableStorePaginationReadManager tableStorePaginationReadManager;
	@Autowired
	private DriverTaskRepository driverTaskRepository;

	@Override
	public List<String> getAlarmDataBean(String cldm, Date startTime, Date endTime) {
		List<DriverTaskEntity> driverTaskEntityList = driverTaskRepository.getDriverListByDateAndCldm(startTime, endTime, cldm);
		List<MessageBean> canAlarmDataBeanList = tableStoreManager.getRange(Long.valueOf(cldm), DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_EVENT, new AtomicInteger(0));
		if (canAlarmDataBeanList.size() < 1)
			return null;
		return getCanStaticDataList(canAlarmDataBeanList, driverTaskEntityList);
	}

	/**
	 * 从can数据报警和事件中分析统计
	 * 
	 * @param driverTaskEntity
	 * @param CanStaticDataBean
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private List<String> getCanStaticDataList(List<MessageBean> canAlarmDataBeanList, List<DriverTaskEntity> driverTaskEntityList) {
		List<String> list = new ArrayList<String>();
		int totalSize = 0;
		String driverId = "";
		if (driverTaskEntityList != null && driverTaskEntityList.size() > 0) {
			driverId = driverTaskEntityList.get(0).getJsydm();
		}

		for (MessageBean messageBean : canAlarmDataBeanList) {
			CanAlarmDataBean canAlarmDataBean = new CanAlarmDataBean();
			AlarmData alarmData = messageBean.getAlarmData();
			GpsLocation gpsLocation = alarmData.getGpsLocation();
			String time = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()));
			canAlarmDataBean.setDriverId(driverId);
			canAlarmDataBean.setAlarmEndTime(time);
			canAlarmDataBean.setAlarmStartTime(time);
			canAlarmDataBean.setSteayTime(0);
			canAlarmDataBean.setAlarmType(alarmData.getAlarmType());
			canAlarmDataBean.setCreateTime(Long.parseLong(messageBean.getCreateTime()));
			canAlarmDataBean.setEquipNum(messageBean.getEquipNum());
			canAlarmDataBean.setInfoType(messageBean.getInfoType());
			canAlarmDataBean.setLat(String.valueOf(gpsLocation.getLat()));
			canAlarmDataBean.setLocation("");
			canAlarmDataBean.setLon(String.valueOf(gpsLocation.getLon()));
			canAlarmDataBean.setReciveTime(messageBean.getReciveTime());
			canAlarmDataBean.setSpeed(gpsLocation.getGpsSpeed());
			canAlarmDataBean.setSteayTime(0);
			canAlarmDataBean.setVehicleCode(messageBean.getVehicleCode());
			canAlarmDataBean.setVehicleNum("");
			canAlarmDataBean.setVehicleId(messageBean.getVehicleId());
			switch (messageBean.getAlarmData().getAlarmType()) {
			case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
				canAlarmDataBean.setAlarmName("汽车怠速过长上报");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
				canAlarmDataBean.setAlarmName("汽车怠速过长告警结束");
				AlarmIdlingTooLongData alarmIdlingTooLongData = (AlarmIdlingTooLongData) alarmData.getAlarmIncidentalData();
				String startTime = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmIdlingTooLongData.getIdlingSteayTime());
				canAlarmDataBean.setAlarmStartTime(startTime);
				canAlarmDataBean.setSteayTime(alarmIdlingTooLongData.getIdlingSteayTime());
				break;
			case 0x010D:// 高速空档滑行报警开始 [高速空档滑行报警附加信息]
				canAlarmDataBean.setAlarmName("高速空档滑行报警开始");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
				canAlarmDataBean.setAlarmName(" 高速空档滑行报警结束");
				AlarmHSpeedNeutralData alarmHSpeedNeutralData = (AlarmHSpeedNeutralData) alarmData.getAlarmIncidentalData();
				String startTime2 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmHSpeedNeutralData.getNeutralSteayTime());
				canAlarmDataBean.setAlarmStartTime(startTime2);
				canAlarmDataBean.setSteayTime(alarmHSpeedNeutralData.getNeutralSteayTime());
				break;
			case 0x0700:// 超高转速报警[超高转速报警附带信息]
				canAlarmDataBean.setAlarmName("超高转速报警开始");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2007:// 超高转速报警结束[超高转速报警附带信息]
				canAlarmDataBean.setAlarmName("超高转速报警结束");
				AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = (AlarmSuperHighEngineSpeedData) alarmData.getAlarmIncidentalData();
				String startTime3 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmSuperHighEngineSpeedData.getEnginSpeedSteayTime());
				canAlarmDataBean.setAlarmStartTime(startTime3);
				break;
			case 0x010c:// 超速报警开始
				canAlarmDataBean.setAlarmName("超速报警");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2003:// 超速报警结束
				canAlarmDataBean.setAlarmName("超速报警结束");
				AlarmOverSpeed alarmOverSpeed = (AlarmOverSpeed) alarmData.getAlarmIncidentalData();
				;
				String startTime4 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmOverSpeed.getContinueTime());
				canAlarmDataBean.setAlarmStartTime(startTime4);
				canAlarmDataBean.setSteayTime(alarmOverSpeed.getContinueTime());
				break;
			case AlarmConst.CMD_INFO_TYPE_6001:
				canAlarmDataBean.setAlarmName("急加速");
				break;// 急加速
			case AlarmConst.CMD_INFO_TYPE_6002:
				canAlarmDataBean.setAlarmName("急减速");
				break;// 急减速
			case AlarmConst.CMD_INFO_TYPE_6003:
				canAlarmDataBean.setAlarmName("急转弯");
				break;// 急转弯
			case AlarmConst.CMD_INFO_TYPE_0001:// 点火上报
				canAlarmDataBean.setAlarmName("点火上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_0002: // 熄火上报
				canAlarmDataBean.setAlarmName("熄火上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_0701:// 低档高速报警[低档高速报警附加信息]
				canAlarmDataBean.setAlarmName("低档高速报警上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_2008: // 低档高速报警结束[低档高速报警附加信息]
				canAlarmDataBean.setAlarmName("低档高速报警结束");
				AlarmOverSpeed alarmOverSpeed2 = (AlarmOverSpeed) alarmData.getAlarmIncidentalData();
				;
				String startTime5 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmOverSpeed2.getContinueTime());
				canAlarmDataBean.setAlarmStartTime(startTime5);
				canAlarmDataBean.setSteayTime(alarmOverSpeed2.getContinueTime());
				break;
			case AlarmConst.CMD_INFO_TYPE_0702: // 高档低速报警[高档低速报警附加信息]
				canAlarmDataBean.setAlarmName("高档低速报警上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_2009:// 高档低速报警结束[高档低速报警附加信息]
				canAlarmDataBean.setAlarmName("高档低速报警结束[高档低速报警附加信息");
				AlarmOverSpeed alarmOverSpeed3 = (AlarmOverSpeed) alarmData.getAlarmIncidentalData();
				;
				String startTime6 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmOverSpeed3.getContinueTime());
				canAlarmDataBean.setAlarmStartTime(startTime6);
				canAlarmDataBean.setSteayTime(alarmOverSpeed3.getContinueTime());
				break;
			}
			if (StringUtils.isNotEmpty(canAlarmDataBean.getAlarmName()))
				list.add(JSONObject.toJSONString(canAlarmDataBean));
		}
		return list;
	}

	/**
	 * 从can数据报警和事件中分析统计
	 * 
	 * @param driverTaskEntity
	 * @param CanStaticDataBean
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private Map<List<String>, Integer> getCanStaticDataListByPage(Map<List<MessageBean>, Integer> messageBeanMap) {
		Map<List<String>, Integer> resultMap = new HashMap<>();
		List<String> list = new ArrayList<String>();
		List<MessageBean> canAlarmDataBeanList = new ArrayList<MessageBean>();
		int totalSize = 0;
		for (Entry<List<MessageBean>, Integer> entry : messageBeanMap.entrySet()) {
			canAlarmDataBeanList = entry.getKey();
			totalSize = entry.getValue();
		}

		for (MessageBean messageBean : canAlarmDataBeanList) {
			CanAlarmDataBean canAlarmDataBean = new CanAlarmDataBean();
			AlarmData alarmData = messageBean.getAlarmData();
			GpsLocation gpsLocation = alarmData.getGpsLocation();
			String time = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()));
			// canAlarmDataBean.setDriverId(driverId);
			canAlarmDataBean.setAlarmEndTime(time);
			canAlarmDataBean.setAlarmStartTime(time);
			canAlarmDataBean.setSteayTime(0);
			canAlarmDataBean.setAlarmType(alarmData.getAlarmType());
			canAlarmDataBean.setCreateTime(Long.parseLong(messageBean.getCreateTime()));
			canAlarmDataBean.setEquipNum(messageBean.getEquipNum());
			canAlarmDataBean.setInfoType(messageBean.getInfoType());
			canAlarmDataBean.setLat(String.valueOf(gpsLocation.getLat()));
			canAlarmDataBean.setLocation("");
			canAlarmDataBean.setLon(String.valueOf(gpsLocation.getLon()));
			canAlarmDataBean.setReciveTime(messageBean.getReciveTime());
			canAlarmDataBean.setSpeed(gpsLocation.getGpsSpeed());
			canAlarmDataBean.setSteayTime(0);
			canAlarmDataBean.setVehicleCode(messageBean.getVehicleCode());
			canAlarmDataBean.setVehicleNum("");
			canAlarmDataBean.setVehicleId(messageBean.getVehicleId());
			switch (messageBean.getAlarmData().getAlarmType()) {
			case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
				canAlarmDataBean.setAlarmName("汽车怠速过长上报");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
				canAlarmDataBean.setAlarmName("汽车怠速过长告警结束");
				AlarmIdlingTooLongData alarmIdlingTooLongData = (AlarmIdlingTooLongData) alarmData.getAlarmIncidentalData();
				String startTime = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmIdlingTooLongData.getIdlingSteayTime());
				canAlarmDataBean.setAlarmStartTime(startTime);
				canAlarmDataBean.setSteayTime(alarmIdlingTooLongData.getIdlingSteayTime());
				break;
			case 0x010D:// 高速空档滑行报警开始 [高速空档滑行报警附加信息]
				canAlarmDataBean.setAlarmName("高速空档滑行报警开始");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
				canAlarmDataBean.setAlarmName(" 高速空档滑行报警结束");
				AlarmHSpeedNeutralData alarmHSpeedNeutralData = (AlarmHSpeedNeutralData) alarmData.getAlarmIncidentalData();
				String startTime2 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmHSpeedNeutralData.getNeutralSteayTime());
				canAlarmDataBean.setAlarmStartTime(startTime2);
				canAlarmDataBean.setSteayTime(alarmHSpeedNeutralData.getNeutralSteayTime());
				break;
			case 0x0700:// 超高转速报警[超高转速报警附带信息]
				canAlarmDataBean.setAlarmName("超高转速报警开始");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2007:// 超高转速报警结束[超高转速报警附带信息]
				canAlarmDataBean.setAlarmName("超高转速报警结束");
				AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = (AlarmSuperHighEngineSpeedData) alarmData.getAlarmIncidentalData();
				String startTime3 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmSuperHighEngineSpeedData.getEnginSpeedSteayTime());
				canAlarmDataBean.setAlarmStartTime(startTime3);
				break;
			case 0x010c:// 超速报警开始
				canAlarmDataBean.setAlarmName("超速报警");
				canAlarmDataBean.setAlarmEndTime("/");
				break;
			case 0x2003:// 超速报警结束
				canAlarmDataBean.setAlarmName("超速报警结束");
				AlarmOverSpeed alarmOverSpeed = (AlarmOverSpeed) alarmData.getAlarmIncidentalData();
				;
				String startTime4 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmOverSpeed.getContinueTime());
				canAlarmDataBean.setAlarmStartTime(startTime4);
				canAlarmDataBean.setSteayTime(alarmOverSpeed.getContinueTime());
				break;
			case AlarmConst.CMD_INFO_TYPE_6001:
				canAlarmDataBean.setAlarmName("急加速");
				break;// 急加速
			case AlarmConst.CMD_INFO_TYPE_6002:
				canAlarmDataBean.setAlarmName("急减速");
				break;// 急减速
			case AlarmConst.CMD_INFO_TYPE_6003:
				canAlarmDataBean.setAlarmName("急转弯");
				break;// 急转弯
			case AlarmConst.CMD_INFO_TYPE_0001:// 点火上报
				canAlarmDataBean.setAlarmName("点火上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_0002: // 熄火上报
				canAlarmDataBean.setAlarmName("熄火上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_0701:// 低档高速报警[低档高速报警附加信息]
				canAlarmDataBean.setAlarmName("低档高速报警上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_2008: // 低档高速报警结束[低档高速报警附加信息]
				canAlarmDataBean.setAlarmName("低档高速报警结束");
				AlarmOverSpeed alarmOverSpeed2 = (AlarmOverSpeed) alarmData.getAlarmIncidentalData();
				;
				String startTime5 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmOverSpeed2.getContinueTime());
				canAlarmDataBean.setAlarmStartTime(startTime5);
				canAlarmDataBean.setSteayTime(alarmOverSpeed2.getContinueTime());
				break;
			case AlarmConst.CMD_INFO_TYPE_0702: // 高档低速报警[高档低速报警附加信息]
				canAlarmDataBean.setAlarmName("高档低速报警上报");
				break;
			case AlarmConst.CMD_INFO_TYPE_2009:// 高档低速报警结束[高档低速报警附加信息]
				canAlarmDataBean.setAlarmName("高档低速报警结束[高档低速报警附加信息");
				AlarmOverSpeed alarmOverSpeed3 = (AlarmOverSpeed) alarmData.getAlarmIncidentalData();
				String startTime6 = DateUtil.getstrtime(Long.valueOf(messageBean.getCreateTime()) - alarmOverSpeed3.getContinueTime());
				canAlarmDataBean.setAlarmStartTime(startTime6);
				canAlarmDataBean.setSteayTime(alarmOverSpeed3.getContinueTime());
				break;
			default:
				canAlarmDataBean.setAlarmName("其他报警");
				break;
			}
			if (StringUtils.isNotEmpty(canAlarmDataBean.getAlarmName()))
				list.add(JSONObject.toJSONString(canAlarmDataBean));
		}
		resultMap.put(list, totalSize);
		return resultMap;
	}

	/**
	 * 分页查询报警事件
	 */
	@Override
	public Map<List<String>, Integer> getAlarmDataBeanByPage(String cldm, Date startTime, Date endTime, int offset, int pageSize) {
		Map<List<MessageBean>, Integer> canAlarmDataBeanListMap = tableStorePaginationReadManager.getRange(cldm, DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_EVENT, offset, pageSize);
		Map<List<String>, Integer> map = getCanStaticDataListByPage(canAlarmDataBeanListMap);
		return map;
	}

}
