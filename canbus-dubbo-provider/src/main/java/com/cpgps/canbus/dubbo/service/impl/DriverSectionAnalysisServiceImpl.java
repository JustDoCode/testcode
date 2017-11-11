package com.cpgps.canbus.dubbo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cpgps.canbus.common.model.MessageBean;
import com.cpgps.canbus.common.model.realtime.AlarmHSpeedNeutralData;
import com.cpgps.canbus.common.model.realtime.AlarmIdlingTooLongData;
import com.cpgps.canbus.common.model.realtime.AlarmSuperHighEngineSpeedData;
import com.cpgps.canbus.common.model.realtime.CanStaticData;
import com.cpgps.canbus.common.model.realtime.GpsLocation;
import com.cpgps.canbus.common.model.realtime.TravelStatisticalData;
import com.cpgps.canbus.common.ots.TableSpaceDBConection;
import com.cpgps.canbus.common.utils.AlarmConst;
import com.cpgps.canbus.common.utils.CanbusDataUtil;
import com.cpgps.canbus.common.utils.Const;
import com.cpgps.canbus.common.utils.DateUtil;
import com.cpgps.canbus.common.utils.KKTool;
import com.cpgps.canbus.dubbo.service.DriverSectionAnalysisService;
import com.cpgps.canbus.repository.entity.can.DriverSectionAnalysisEntity;
import com.cpgps.canbus.repository.entity.info.CarEntity;
import com.cpgps.canbus.repository.entity.info.CarRepository;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日 统计车的指标
 */
@Component
public class DriverSectionAnalysisServiceImpl implements DriverSectionAnalysisService {
	static Logger logger = LoggerFactory.getLogger(DriverSectionAnalysisServiceImpl.class);
	@Autowired
	private TableSpaceDBConection tableSpaceDBConection;
	@Autowired
	private CarRepository carRepository;

	/**
	 * 从can数据中分析统计
	 * 
	 * @param driverSectionAnalysisEntity
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private DriverSectionAnalysisEntity countCanStaticMessageEntity(DriverSectionAnalysisEntity driverSectionAnalysisEntity, List<MessageBean> canStaticMessageBeanList) {
		CanbusDataUtil canbusDataUtil = new CanbusDataUtil();
		for (MessageBean messageBean : canStaticMessageBeanList) {
			CanStaticData canStaticData = messageBean.getCanStaticData();
			driverSectionAnalysisEntity.setZdsd(canbusDataUtil.getZdsd(canStaticData.getSpeed()));
			driverSectionAnalysisEntity.setZdzs(canbusDataUtil.getZdzs(canStaticData.getEnginSpeed()));
			driverSectionAnalysisEntity.setLczdz(canbusDataUtil.getLczdz(canStaticData.getOdometer()));
			driverSectionAnalysisEntity.setLczxz(canbusDataUtil.getLczxz(canStaticData.getOdometer()));
			driverSectionAnalysisEntity.setZyhzxz(canbusDataUtil.getZyhzxz(canStaticData.getFulmeter()));
			driverSectionAnalysisEntity.setZyhzdz(canbusDataUtil.getZyhzdz(canStaticData.getFulmeter()));
			driverSectionAnalysisEntity.setYxsjzdz(canbusDataUtil.getYxsjzdz(canStaticData.getCarTotalRunTime()));
			driverSectionAnalysisEntity.setYxsjzxz(canbusDataUtil.getYxsjzxz(canStaticData.getCarTotalRunTime()));
			driverSectionAnalysisEntity.setFdjyxsj(canbusDataUtil.getFdjyxsj(canStaticData.getEngineRunTime()));
			driverSectionAnalysisEntity.setYblczdz(canbusDataUtil.getYbLczdz(canStaticData.getDashTotalOdermeter()));
			driverSectionAnalysisEntity.setYblczxz(canbusDataUtil.getYbLczxz(canStaticData.getDashTotalOdermeter()));
			driverSectionAnalysisEntity.setYbzyhzxz(canbusDataUtil.getYbZyhzxz(canStaticData.getDashTotalOil()));
			driverSectionAnalysisEntity.setYbzyhzdz(canbusDataUtil.getYbZyhzdz(canStaticData.getDashTotalOil()));
		}
		return driverSectionAnalysisEntity;
	}
	
	/**
	 * 从Gps数据中分析统计
	 * 
	 * @param carSectionAnalysisEntity
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private DriverSectionAnalysisEntity countGpsMessageEntity(DriverSectionAnalysisEntity carSectionAnalysisEntity, List<MessageBean> gpsMessageBeanList) {
		CanbusDataUtil canbusDataUtil = new CanbusDataUtil();
		for (MessageBean messageBean : gpsMessageBeanList) {
			GpsLocation gpsLocation = messageBean.getGpsLocation();
			carSectionAnalysisEntity.setGpslczdz(canbusDataUtil.getGpsLczdz(gpsLocation.getOdometer()));
			carSectionAnalysisEntity.setGpslczxz(canbusDataUtil.getGpsLczxz(gpsLocation.getOdometer()));
			carSectionAnalysisEntity.setGpszyhzxz(canbusDataUtil.getGpsZyhzxz(gpsLocation.getFulmeter()));
			carSectionAnalysisEntity.setGpszyhzdz(canbusDataUtil.getGpsZyhzdz(gpsLocation.getFulmeter()));
		}
		return carSectionAnalysisEntity;
	}

	/**
	 * 从事件数据中分析统计
	 * 
	 * @param carSectionAnalysisEntity
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private DriverSectionAnalysisEntity countEventMessageEntity(DriverSectionAnalysisEntity carSectionAnalysisEntity, List<MessageBean> eventMessageBeanList) {
		for (MessageBean messageBean : eventMessageBeanList) {
			byte infoType = KKTool.int2OneByte(messageBean.getInfoType());
			switch (infoType) {
			case Const.CMD_INFO_TYPE_0B:// 报警
				short alarmType = messageBean.getAlarmData().getAlarmType();
				String dateStr = DateUtil.getstrtime(messageBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
				switch (alarmType) {
				case AlarmConst.CMD_INFO_TYPE_0001://	点火上报
					if(null==carSectionAnalysisEntity.getXckssj()||"".equals(carSectionAnalysisEntity.getXckssj()))carSectionAnalysisEntity.setXckssj(dateStr);
					carSectionAnalysisEntity.setXcs(carSectionAnalysisEntity.getXcs() + 1);
					break;
				case AlarmConst.CMD_INFO_TYPE_0002: //	熄火上报
					carSectionAnalysisEntity.setXcs(carSectionAnalysisEntity.getXcs() + 1);
					carSectionAnalysisEntity.setXcjssj(dateStr);
					break;
				case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
					break;
				case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
					AlarmIdlingTooLongData alarmIdlingTooLongData = (AlarmIdlingTooLongData) messageBean.getAlarmData().getAlarmIncidentalData();
					carSectionAnalysisEntity.setDsxh(carSectionAnalysisEntity.getDsxh() + alarmIdlingTooLongData.getIdlingFulmeter());
					break;
				case 0x010D:// 高速空档滑行报警[高速空档滑行报警附加信息]
					carSectionAnalysisEntity.setKdhxcs(carSectionAnalysisEntity.getKdhxcs() + 1);
					break;
				case 0x010c:// 超速报警
					carSectionAnalysisEntity.setCscs(carSectionAnalysisEntity.getCscs() +1);
					break;
				case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
					AlarmHSpeedNeutralData alarmHSpeedNeutralData = (AlarmHSpeedNeutralData) messageBean.getAlarmData().getAlarmIncidentalData();
					carSectionAnalysisEntity.setKdhxsj(carSectionAnalysisEntity.getKdhxsj() + alarmHSpeedNeutralData.getNeutralSteayTime());
					break;
				case 0x6001:// 急加速
					carSectionAnalysisEntity.setJjscsp(carSectionAnalysisEntity.getJjscsp() + 1);
					break;
				case 0x6002:// 急减速
					carSectionAnalysisEntity.setJjscsm(carSectionAnalysisEntity.getJjscsm() + 1);
					break;
				case 0x6003:// 急转弯
					carSectionAnalysisEntity.setJzwcs(carSectionAnalysisEntity.getJzwcs() + 1);
					break;
				case 0x010F:// 转速过高报警
					carSectionAnalysisEntity.setCzscs(carSectionAnalysisEntity.getCzscs() + 1);
					break;
				case 0x0700:// 超高转速报警[超高转速报警附带信息]
					carSectionAnalysisEntity.setCzscs(carSectionAnalysisEntity.getCzscs() + 1);
					break;
				case 0x2007:// 超高转速报警结束[超高转速报警附带信息]
					AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = (AlarmSuperHighEngineSpeedData) messageBean.getAlarmData().getAlarmIncidentalData();
					carSectionAnalysisEntity.setCzsljsj(carSectionAnalysisEntity.getCzsljsj() + alarmSuperHighEngineSpeedData.getEnginSpeedSteayTime());
					carSectionAnalysisEntity.setCzscs(carSectionAnalysisEntity.getCzscs() + 1);
					break;
				}
				break;
			case Const.CMD_INFO_TYPE_0D:// 超速事件
				carSectionAnalysisEntity.setCsljsj(carSectionAnalysisEntity.getCsljsj() + messageBean.getEventSuperSpeedData().getOverSpeedTime());
				break;
			case Const.CMD_INFO_TYPE_0E:// 怠速事件
				carSectionAnalysisEntity.setDsljsj(carSectionAnalysisEntity.getDsljsj() + messageBean.getEventIdlingData().getIdlingTotalTime());
				break;
			case Const.CMD_INFO_TYPE_0F:// 空挡事件
				carSectionAnalysisEntity.setKdhxsj(carSectionAnalysisEntity.getKdhxsj() + messageBean.getEventNeutralData().getNeutralSlideTime());
				break;
			case Const.CMD_INFO_TYPE_10:// 故障码事件
				break;
			}

		}
		return carSectionAnalysisEntity;
	}

	/**
	 * 从行程数据中统计分析
	 * 
	 * @param carSectionAnalysisEntity
	 * @param travelStatisticalDataMessageBeanList
	 * @return
	 */
	private DriverSectionAnalysisEntity countTravelStatisticalDataEntity(DriverSectionAnalysisEntity carSectionAnalysisEntity, List<MessageBean> travelStatisticalDataMessageBeanList) {
		for (MessageBean messageBean : travelStatisticalDataMessageBeanList) {
			byte info = KKTool.int2OneByte(messageBean.getInfoType());
			if (info == Const.CMD_INFO_TYPE_11) {
				// 行程统计brakeNum,brakeOdometer,economicSpeedTime,ureaConsume,discharge
				TravelStatisticalData travelStatisticalData = messageBean.getTravelStatisticalData();
				List<Map<String, Object>> list = travelStatisticalData.getMessage();
				for (Map<String, Object> map : list) {
					if (map.get("brakeNum") != null)
						carSectionAnalysisEntity.setSccs(carSectionAnalysisEntity.getSccs() + (int) map.get("brakeNum"));
					if (map.get("brakeOdometer") != null)
						carSectionAnalysisEntity.setScjl(carSectionAnalysisEntity.getScjl() + (int) map.get("brakeOdometer"));
				}
			}
		}
		return carSectionAnalysisEntity;
	}

	/**
	 * 封装分析结果
	 * 
	 * @param carSectionAnalysisEntity
	 * @return
	 */
	private DriverSectionAnalysisEntity initcarSectionAnalysisEntity(DriverSectionAnalysisEntity carSectionAnalysisEntity) {
		CanbusDataUtil canbusDataUtil = new CanbusDataUtil();
		//发动机里程
		long zlc = canbusDataUtil.getDifference(carSectionAnalysisEntity.getLczdz(), carSectionAnalysisEntity.getLczxz());
		//仪表里程
		long yblc= canbusDataUtil.getDifference(carSectionAnalysisEntity.getYblczdz(),carSectionAnalysisEntity.getYblczxz());
		//gps里程
		long gpslc= canbusDataUtil.getDifference(carSectionAnalysisEntity.getGpslczdz(),carSectionAnalysisEntity.getGpslczxz());
		long zyh;
		// 发动机总油耗                  总油耗=结束时间总油耗-开始时间总油耗
		long hyl = zyh = canbusDataUtil.getDifference(carSectionAnalysisEntity.getZyhzdz(), carSectionAnalysisEntity.getZyhzxz());
		//仪表油耗
		long ybzyh= canbusDataUtil.getDifference(carSectionAnalysisEntity.getYbzyhzdz(),carSectionAnalysisEntity.getYbzyhzxz());
		//GPS油耗
		long gpszyh= canbusDataUtil.getDifference(carSectionAnalysisEntity.getGpszyhzdz(),carSectionAnalysisEntity.getGpszyhzxz());
		
		//gps油耗
		// 行驶消耗 行驶油耗=总油耗-怠速油耗
		long xsxh = canbusDataUtil.getDifference(zyh, carSectionAnalysisEntity.getDsxh());
		// 百公里油耗 百公里油耗=（行驶油耗/总里程）*100*1000
		double bglyh = CanbusDataUtil.getFormatNumber(canbusDataUtil.div(zyh, zlc) * 100 * 1000);
		// 行车时间
		long xcsj = canbusDataUtil.getDifference(carSectionAnalysisEntity.getYxsjzdz(), carSectionAnalysisEntity.getYxsjzxz());
		// 发动机运行时间=行车时间
		long fdjyxsj = xcsj;
		// 熄火时间 24*3600 -发动机运行时间
		long xhsj = 24 * 3600 - fdjyxsj;
		// 平均速度 (总里程/(1000*1000))/(总时间/3600) 公里/h
		double pjsd = CanbusDataUtil.getFormatNumber(canbusDataUtil.getPjsd(zlc, xcsj));
		long wsclc = canbusDataUtil.getDifference(zlc, carSectionAnalysisEntity.getScjl());
		carSectionAnalysisEntity.setBglyh(bglyh);
		carSectionAnalysisEntity.setCjsj(DateUtil.date2date(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		// carSectionAnalysisEntity.setDshszb(dshszb); 怠速耗时占比
		carSectionAnalysisEntity.setFdjyxsj(fdjyxsj);
		carSectionAnalysisEntity.setFdjlc(zlc);
		carSectionAnalysisEntity.setHyl(hyl);
		// carSectionAnalysisEntity.setJsyid(jsyid); 驾驶员编号取不出
		carSectionAnalysisEntity.setPjsd(pjsd);
		carSectionAnalysisEntity.setWsclc(wsclc);
		carSectionAnalysisEntity.setXcsj(xcsj);
		carSectionAnalysisEntity.setXhsj(xhsj);
		carSectionAnalysisEntity.setXshs(xcsj);
		carSectionAnalysisEntity.setXsxh(xsxh);
		carSectionAnalysisEntity.setYzsj(xcsj);
		carSectionAnalysisEntity.setZlc(zlc);
		carSectionAnalysisEntity.setGpslc(gpslc);
		carSectionAnalysisEntity.setYblc(yblc);
		carSectionAnalysisEntity.setYbzyh(ybzyh);
		carSectionAnalysisEntity.setGpsyh(gpszyh);
		carSectionAnalysisEntity.setZyh(zyh);
		return carSectionAnalysisEntity;
	}

	public static void main(String[] args) {
		Date date = DateUtil.getYesterdayTimeByCalendar();
//		CarSectionAnalysisEntity carSectionAnalysisEntity = new DriverSectionAnalysisServiceImpl().getCarSectionAnalysisEntity("2455053485", "S100702400341",DateUtil.getDayStartTime(date), DateUtil.getDayEndTime(date));
//		System.out.println(JSON.toJSONString(carSectionAnalysisEntity));

	}

	

	@Override
	public DriverSectionAnalysisEntity getDriverSectionAnalysisEntity(String cldm, Date startTime, Date endTime) {
		List<MessageBean> canStaticMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm), DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_CANSTATIC, new AtomicInteger(0));
		if(canStaticMessageBeanList.size()<1) return null;
		List<MessageBean> eventMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm), DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_EVENT, new AtomicInteger(0));
		List<MessageBean> travelStatisticalDataMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm), DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_TRAVELDATA, new AtomicInteger(0));
		List<MessageBean> gpsMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm), DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_GPS, new AtomicInteger(0));
		DriverSectionAnalysisEntity driverSectionAnalysisEntity = new DriverSectionAnalysisEntity();
		driverSectionAnalysisEntity.setCldm(cldm);
		CarEntity carEntity = carRepository.findOne(cldm);
		
		if(carEntity!=null){
			driverSectionAnalysisEntity.setFdjxh(carEntity.getFdjh());
		}else{
			driverSectionAnalysisEntity.setFdjxh("暂无信息");
		}
		driverSectionAnalysisEntity.setTjrq(DateUtil.DateToString(startTime, DateUtil.YYYY_MM_DD));
		// can静态数据
		countCanStaticMessageEntity(driverSectionAnalysisEntity, canStaticMessageBeanList);
		// gps计算
		countGpsMessageEntity(driverSectionAnalysisEntity, gpsMessageBeanList);
		// 事件数据
		countEventMessageEntity(driverSectionAnalysisEntity, eventMessageBeanList);
		// 行程数据
		countTravelStatisticalDataEntity(driverSectionAnalysisEntity, travelStatisticalDataMessageBeanList);
		initcarSectionAnalysisEntity(driverSectionAnalysisEntity);
		return driverSectionAnalysisEntity;
	}
}
