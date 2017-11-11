package com.cpgps.canbus.dubbo.service.impl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cpgps.canbus.common.cache.RedisCache;
import com.cpgps.canbus.common.model.realtime.CarEmsRunStatusData;
import com.cpgps.canbus.common.utils.DateUtil;
import com.cpgps.canbus.dubbo.model.CanStaticDataBean;
import com.cpgps.canbus.dubbo.service.CanAlarmDataBeanService;
import com.cpgps.canbus.dubbo.service.CanStaticDataBeanService;
import com.cpgps.canbus.dubbo.service.CarEmsRunStatusService;
import com.cpgps.canbus.dubbo.service.CarSectionAnalysisService;
import com.cpgps.canbus.dubbo.service.DriverSectionAnalysisService;
import com.cpgps.canbus.repository.entity.can.CarSectionAnalysisEntity;
import com.cpgps.canbus.repository.entity.can.DriverSectionAnalysisEntity;
import com.cpgps.canbus.repository.entity.can.DriverTaskEntity;
import com.cpgps.canbus.repository.entity.can.DriverTaskRepository;
import com.cpgps.canbus.repository.entity.info.CanbusEquipEntity;
import com.cpgps.canbus.repository.entity.info.CanbusEquipRepository;
import com.cpgps.canbus.repository.entity.info.CarEntity;
import com.cpgps.canbus.repository.entity.info.CarRepository;
import com.cpgps.canbus.repository.entity.info.DepartmentEntity;
import com.cpgps.canbus.repository.entity.info.DepartmentsRepository;
import com.cpgps.canbus.repository.entity.info.DriverEntity;
import com.cpgps.canbus.repository.entity.info.DriverRepository;
import com.cpgps.dubboserver.ifce.CanbusDataServiceI;

public class CanbusDataServiceImp implements CanbusDataServiceI, Serializable {
	private static final long serialVersionUID = -5652756496487561267L;
	static Logger logger = LoggerFactory.getLogger(CanbusDataServiceImp.class);
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private DepartmentsRepository departmentsRepository;
	@Autowired
	private CanStaticDataBeanService canStaticDataBeanService;
	@Autowired
	private CanAlarmDataBeanService canAlarmDataBeanService;
	@Autowired
	private CarSectionAnalysisService carSectionAnalysisService;
	@Autowired
	private DriverSectionAnalysisService driverSectionAnalysisService;
	@Autowired
	private RedisCache redisCache;
	@Autowired
	private DriverTaskRepository driverTaskRepository;
	@Autowired
	private CarEmsRunStatusService carEmsRunStatusService;
	@Autowired
	private CanbusEquipRepository canbusEquipRepository;

	/**
	 * 通过cldm查询车辆EMS期间报表
	 */
	@Override
	public List<String> getCarSectionAnalysisListByCldm(String startTimeStr, String endTimeStr, String cldmStr) {
		List<String> carSectionAnalysisEntityList = new ArrayList<String>();
		try {
			String clcx = "";
			String cldm = "";
			String cph = "";
			String dwmc = "";
			String dwdm = "";
			int clytdl = 0;// 车辆用途大类
			String fdjlx = "";// 发动机型号
			double dw = 0;// 吨位
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
			// 查询单个车
			if (StringUtils.isNoneEmpty(cldmStr)) {
				cldm = cldmStr;
				try {
					CarEntity carEntity = carRepository.findOne(cldm);
					if (carEntity == null)
						return carSectionAnalysisEntityList;
					clcx = carEntity.getClcx();
					cph = carEntity.getCph();
					dwdm = carEntity.getSsdwdm();
					dw = carEntity.getDw();
					fdjlx = carEntity.getFdjlx();
					clytdl = carEntity.getClytdl();
					dwmc = departmentsRepository.findOne(dwdm).getDwmc();
					CarSectionAnalysisEntity carSectionAnalysisEntity = carSectionAnalysisService.getCarSectionAnalysisEntity(cldm, startTime, endTime);
					if (carSectionAnalysisEntity != null) {
						carSectionAnalysisEntity.setCph(cph);
						carSectionAnalysisEntity.setDwmc(dwmc);
						carSectionAnalysisEntity.setDwdm(dwdm);
						carSectionAnalysisEntity.setClcx(clcx);
						carSectionAnalysisEntity.setDw(dw);
						carSectionAnalysisEntity.setFdjlx(fdjlx);
						carSectionAnalysisEntity.setClytdl(clytdl);
						carSectionAnalysisEntityList.add(JSONObject.toJSONString(carSectionAnalysisEntity));
					}
				} catch (Exception e) {
					logger.error("对车" + cldm + "分析异常" + e.toString(), e);
				}
				return carSectionAnalysisEntityList;
			}
			// 查询全部
			List<CanbusEquipEntity> list = canbusEquipRepository.getCanbusEquipEntityList();
			for (CanbusEquipEntity canbusEquipEntity : list) {
				cldm = canbusEquipEntity.getCldm();
				Map<String, CarEntity> cphMap = new HashMap<String, CarEntity>();
				Map<String, DepartmentEntity> dwdmMap = new HashMap<String, DepartmentEntity>();
				if (!cphMap.containsKey(cldm)) {
					CarEntity carEntity = carRepository.findOne(cldm);
					if (carEntity == null)
						continue;
					dw = carEntity.getDw();
					fdjlx = carEntity.getFdjlx();
					clytdl = carEntity.getClytdl();
					clcx = carEntity.getClcx();
					cph = carEntity.getCph();
					dwdm = carEntity.getSsdwdm();
					DepartmentEntity departmentEntity = departmentsRepository.findOne(dwdm);
					dwmc = departmentEntity.getDwmc();
					cphMap.put(cldm, carEntity);
					dwdmMap.put(dwdm, departmentEntity);
				} else {
					cph = cphMap.get(cldm).getCph();
					clcx = cphMap.get(cldm).getClcx();
					dwmc = dwdmMap.get(dwdm).getDwmc();
					fdjlx = cphMap.get(cldm).getFdjlx();
					dw = cphMap.get(cldm).getDw();
					clytdl = cphMap.get(cldm).getClytdl();
					clcx = cphMap.get(cldm).getClcx();
					cph = cphMap.get(cldm).getCph();
					dwdm = cphMap.get(cldm).getSsdwdm();
				}
				CarSectionAnalysisEntity carSectionAnalysisEntity = carSectionAnalysisService.getCarSectionAnalysisEntity(cldm, startTime, endTime);
				if (carSectionAnalysisEntity != null) {
					carSectionAnalysisEntity.setCph(cph);
					carSectionAnalysisEntity.setDwmc(dwmc);
					carSectionAnalysisEntity.setDwdm(dwdm);
					carSectionAnalysisEntity.setClcx(clcx);
					carSectionAnalysisEntity.setDw(dw);
					carSectionAnalysisEntity.setFdjlx(fdjlx);
					carSectionAnalysisEntity.setClytdl(clytdl);
					carSectionAnalysisEntityList.add(JSONObject.toJSONString(carSectionAnalysisEntity));
				}
			}

		} catch (Exception e) {
			logger.error("对车分析异常" + e.toString(), e);
		}
		return carSectionAnalysisEntityList;
	}

	/**
	 * 通过驾驶员姓名查询驾驶员期间报表
	 */
	@Override
	public List<String> getDriverSectionAnalysisListByJsy(String startTimeStr, String endTimeStr, String cldmStr, List<String> jsydmList) {
		List<String> driverSectionAnalysisEntityList = new ArrayList<String>();
		try {
			String clcx = "";
			String cph = "";
			String dwmc = "";
			String dwdm = "";
			String xm = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTimeStr) || !StringUtils.isNotEmpty(endTimeStr)) {
				return null;
			}
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
			String[] jsydms = jsydmList.toArray(new String[jsydmList.size()]);
			// 查询司机的工作时间的车辆
			List<DriverTaskEntity> driverTaskList = null;
			if (jsydms.length > 0) {
				driverTaskList = driverTaskRepository.getDriverListByJsydm(startTime, endTime, jsydms);
			} else {
				driverTaskList = driverTaskRepository.getDriverListByDate(startTime, endTime);
			}
			for (DriverTaskEntity driverTaskEntity : driverTaskList) {
				String cldm = driverTaskEntity.getCldm();
				String jsydm = driverTaskEntity.getJsydm();
				DriverEntity driverEntity = driverRepository.findOne(jsydm);
				xm = driverEntity.getXm();
				try {
					Map<String, CarEntity> cphMap = new HashMap<String, CarEntity>();
					Map<String, DepartmentEntity> dwdmMap = new HashMap<String, DepartmentEntity>();
					if (!cphMap.containsKey(cldm)) {
						CarEntity carEntity = carRepository.findOne(cldm);
						if (carEntity == null)
							continue;
						clcx = carEntity.getClcx();
						cph = carEntity.getCph();
						dwdm = carEntity.getSsdwdm();
						DepartmentEntity departmentEntity = departmentsRepository.findOne(dwdm);
						dwmc = departmentEntity.getDwmc();
						cphMap.put(cldm, carEntity);
						dwdmMap.put(dwdm, departmentEntity);
					} else {
						cph = cphMap.get(cldm).getCph();
						clcx = cphMap.get(cldm).getClcx();
						dwmc = dwdmMap.get(dwdm).getDwmc();
						clcx = cphMap.get(cldm).getClcx();
						cph = cphMap.get(cldm).getCph();
						dwdm = cphMap.get(cldm).getSsdwdm();
					}

					DriverSectionAnalysisEntity driverSectionAnalysisEntity = driverSectionAnalysisService.getDriverSectionAnalysisEntity(cldm, startTime, endTime);
					if (driverSectionAnalysisEntity != null) {
						driverSectionAnalysisEntity.setCph(cph);
						driverSectionAnalysisEntity.setDwmc(dwmc);
						driverSectionAnalysisEntity.setXm(xm);
						driverSectionAnalysisEntity.setDwdm(dwdm);
						driverSectionAnalysisEntity.setClcx(clcx);
						driverSectionAnalysisEntityList.add(JSONObject.toJSONString(driverSectionAnalysisEntity));
					}
				} catch (Exception e) {
					logger.error("对车" + cldm + "分析异常" + e.toString(), e);
				}
				return driverSectionAnalysisEntityList;
			}
		} catch (Exception e) {
			logger.error("对车分析异常" + e.toString(), e);
		}
		return driverSectionAnalysisEntityList;
	}

	/**
	 * 获取OBD数据
	 * 
	 * @param startTimeStr
	 * @param endTimeStr
	 * @param cldm
	 * @return
	 */
	public List<String> getCanStaticDataListByDate(String startTimeStr, String endTimeStr, String cldm) {
		List<String> canStaticDataListByDateList = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTimeStr) || !StringUtils.isNotEmpty(endTimeStr)) {
				return canStaticDataListByDateList;
			}
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
			if (StringUtils.isNoneEmpty(cldm)) {
				CanStaticDataBean canStaticDataBean = canStaticDataBeanService.getCanStaticDataBean(cldm, startTime, endTime);
				if (canStaticDataBean != null) {
					canStaticDataListByDateList.add(JSONObject.toJSONString(canStaticDataBean));
				}
				return canStaticDataListByDateList;
			}
		} catch (Exception e) {
			logger.error("对车分析异常" + e.toString(), e);
		}
		return canStaticDataListByDateList;
	}

	@Override
	public List<String> getCanAlarmDataListByDate(String startTimeStr, String endTimeStr, String cldm) {
		List<String> canAlarmDataBeanList = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTimeStr) || !StringUtils.isNotEmpty(endTimeStr))
				return null;
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
			if (StringUtils.isNoneEmpty(cldm)) {
				CarEntity carEntity = carRepository.findOne(cldm);
				if (carEntity == null)
					return canAlarmDataBeanList;
				canAlarmDataBeanList = canAlarmDataBeanService.getAlarmDataBean(cldm, startTime, endTime);
				return canAlarmDataBeanList;
			}
			// 查询全部
			List<CanbusEquipEntity> canbusEquipEntityList = canbusEquipRepository.getCanbusEquipEntityList();
			for (CanbusEquipEntity canbusEquipEntity : canbusEquipEntityList) {
				List<String> list = canAlarmDataBeanService.getAlarmDataBean(canbusEquipEntity.getCldm(), startTime, endTime);
				if (list == null)
					continue;
				canAlarmDataBeanList.addAll(list);
			}
		} catch (Exception e) {
			logger.error("获取车辆{}报警事件异常",cldm, e);
		}
		return canAlarmDataBeanList;
	}
	/**
	 * 获取驾驶员车辆报警事件
	 */
	@Override
	public List<String> getCanAlarmDataListByDriver(String startTimeStr, String endTimeStr, String jsydm) {
		List<String> canAlarmDataBeanList = new ArrayList<String>();
		List<DriverTaskEntity> driverTasklist = new ArrayList<DriverTaskEntity>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTimeStr) || !StringUtils.isNotEmpty(endTimeStr)) {
				return canAlarmDataBeanList;
			}
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
				driverTasklist = driverTaskRepository.getDriverListByJsydm(startTime, endTime, new String[] { jsydm });
			for (DriverTaskEntity driverTaskEntity : driverTasklist) {
				List<String> resultList = canAlarmDataBeanService.getAlarmDataBean(driverTaskEntity.getCldm(), startTime, endTime);
				if (resultList == null)
					continue;
				canAlarmDataBeanList.addAll(resultList);
			}
		} catch (Exception e) {
			logger.error("获取车辆{}报警事件异常",e);
		}
		return canAlarmDataBeanList;
	}

	/**
	 * 获取OTS车辆EMS运行状态数据
	 */
	@Override
	public String CarEmsRunStatusList(String cldm, String startTime, String endTime) {
		CarEmsRunStatusData carEmsRunStatusData = new CarEmsRunStatusData();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTime) || !StringUtils.isNotEmpty(endTime))
				return null;
			Date sTime = sdf.parse(startTime);
			Date eTime = sdf.parse(endTime);
			DecimalFormat df = new DecimalFormat("######0.000");
			// 获取从ots读取的数据
			logger.info("开始读取分析车辆EMS运行状态", cldm);
			if (StringUtils.isNotEmpty(cldm)) {
				CarSectionAnalysisEntity carSectionAnalysisEntity = carEmsRunStatusService.getCarEmsRunStatusEntity(cldm, sTime, eTime);
				if (carSectionAnalysisEntity != null) {
					// 总里程
					double zlc = (double) carSectionAnalysisEntity.getZlc() / 1000;
					carEmsRunStatusData.setTotalMileage(df.format(zlc));
					carEmsRunStatusData.setDrivingTime(DateUtil.formatSeconds(carSectionAnalysisEntity.getXcsj()));
					carEmsRunStatusData.setShutdownTime(DateUtil.formatSeconds(carSectionAnalysisEntity.getXhsj()));
					carEmsRunStatusData.setRunningTime(DateUtil.formatSeconds(carSectionAnalysisEntity.getYzsj()));
					carEmsRunStatusData.setExerciseConsumingTime(DateUtil.formatSeconds(carSectionAnalysisEntity.getXshs()));
					carEmsRunStatusData.setIdleSpeed(DateUtil.formatSeconds(carSectionAnalysisEntity.getDsljsj()));
					// 总油耗
					double zyh = (double) carSectionAnalysisEntity.getZyh() / 1000;
					carEmsRunStatusData.setTotalConsumption(df.format(zyh));
					// 百公里油耗
					carEmsRunStatusData.setKmFuelConsumption(Double.toString(carSectionAnalysisEntity.getBglyh()));
					// 行使油耗
					double xsyh = (double) carSectionAnalysisEntity.getXsxh() / 1000;
					carEmsRunStatusData.setExerciseConsumption(df.format(xsyh));
					// 怠速油耗
					double dsyh = (double) carSectionAnalysisEntity.getDsxh() / 1000;
					carEmsRunStatusData.setIdleSpeedConsumption(df.format(dsyh));
					carEmsRunStatusData.setBrakeNumber(Long.toString(carSectionAnalysisEntity.getSccs()));
					// 未刹车里程
					double wsclc = (double) carSectionAnalysisEntity.getWsclc() / 1000;
					carEmsRunStatusData.setUnbrakeMileage(df.format(wsclc));
					// 刹车距离
					double scjl = (double) carSectionAnalysisEntity.getScjl() / 1000;
					carEmsRunStatusData.setBrakeDistance(df.format(scjl));
					carEmsRunStatusData.setCarRunPercentage(Double.toString(carSectionAnalysisEntity.getYzsjpt()));
					carEmsRunStatusData.setDrivelapsedPercentage(Double.toString(carSectionAnalysisEntity.getXshspt()));
					carEmsRunStatusData.setExerConsumptPercentage(Double.toString(carSectionAnalysisEntity.getXhyhpt()));
					carEmsRunStatusData.setUnbrakeMileagePercentage(Double.toString(carSectionAnalysisEntity.getWsclcpt()));
				} else {
					carEmsRunStatusData.setTotalMileage("0");
					carEmsRunStatusData.setDrivingTime("00时00分00秒");
					carEmsRunStatusData.setShutdownTime("00时00分00秒");
					carEmsRunStatusData.setRunningTime("00时00分00秒");
					carEmsRunStatusData.setExerciseConsumingTime("00时00分00秒");
					carEmsRunStatusData.setIdleSpeed("00时00分00秒");
					// 总油耗
					carEmsRunStatusData.setTotalConsumption("0");
					// 百公里油耗
					carEmsRunStatusData.setKmFuelConsumption("0");
					// 行使油耗
					carEmsRunStatusData.setExerciseConsumption("0");
					// 怠速油耗
					carEmsRunStatusData.setIdleSpeedConsumption("0");
					carEmsRunStatusData.setBrakeNumber("0");
					// 未刹车里程
					carEmsRunStatusData.setUnbrakeMileage("0");
					// 刹车距离
					carEmsRunStatusData.setBrakeDistance("0");
					carEmsRunStatusData.setCarRunPercentage("0");
					carEmsRunStatusData.setDrivelapsedPercentage("0");
					carEmsRunStatusData.setExerConsumptPercentage("0");
					carEmsRunStatusData.setUnbrakeMileagePercentage("0");
				}
			}
		} catch (Exception e) {
			logger.error("车辆{}EMS运行状态查询异常", cldm, e);
		}
		return JSON.toJSONString(carEmsRunStatusData);
	}

	/**
	 * 分页获取报警数据
	 */
	@Override
	public Map<List<String>, Integer> getCanAlarmDataListByPage(String startTimeStr, String endTimeStr, String cldm, int offset, int pageSize) {
		Map<List<String>, Integer> resultMap = new HashMap<List<String>, Integer>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTimeStr) || !StringUtils.isNotEmpty(endTimeStr))
				return null;
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
			if (StringUtils.isNoneEmpty(cldm)) {
				CarEntity carEntity = carRepository.findOne(cldm);
				if (carEntity == null)
					return resultMap;
				resultMap = canAlarmDataBeanService.getAlarmDataBeanByPage(cldm, startTime, endTime, offset, pageSize);
				return resultMap;
			}
			// 查询全部
			List<CanbusEquipEntity> canbusEquipEntityList = canbusEquipRepository.getCanbusEquipEntityList();
			for (CanbusEquipEntity canbusEquipEntity : canbusEquipEntityList) {
				cldm = canbusEquipEntity.getCldm();
				if (cldm != null) {
					Map<List<String>, Integer> map = canAlarmDataBeanService.getAlarmDataBeanByPage(cldm, startTime, endTime, offset, pageSize);
					for (Entry<List<String>, Integer> entry : map.entrySet()) {
						resultMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error("获取车辆报警事件异常" + e.toString(), e);
		}
		return resultMap;
	}

	/**
	 * 分页获取OBD采集数据
	 */
	@Override
	public Map<List<String>, Integer> getCanStaticDataListByPage(String startTimeStr, String endTimeStr, String cldm, int offset, int pageSize) {
		Map<List<String>, Integer> resultMap = new HashMap<List<String>, Integer>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtils.isNotEmpty(startTimeStr) || !StringUtils.isNotEmpty(endTimeStr))
				return null;
			Date startTime = sdf.parse(startTimeStr);
			Date endTime = sdf.parse(endTimeStr);
			// 查询全部
			if (StringUtils.isNoneEmpty(cldm)) {
				CarEntity carEntity = carRepository.findOne(cldm);
				if (carEntity == null)
					return resultMap;
				resultMap = canStaticDataBeanService.getCanStaticDataBeanByPage(cldm, startTime, endTime, offset, pageSize);
				return resultMap;
			}
		} catch (Exception e) {
			logger.error("获取车辆编号：{}OBD采集数据异常" ,cldm, e);
		}
		return resultMap;
	}

}
