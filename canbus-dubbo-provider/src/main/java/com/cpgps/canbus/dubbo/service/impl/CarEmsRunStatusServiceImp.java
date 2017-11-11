package com.cpgps.canbus.dubbo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpgps.canbus.common.model.MessageBean;
import com.cpgps.canbus.common.model.realtime.AlarmHSpeedNeutralData;
import com.cpgps.canbus.common.model.realtime.AlarmIdlingTooLongData;
import com.cpgps.canbus.common.model.realtime.AlarmSuperHighEngineSpeedData;
import com.cpgps.canbus.common.model.realtime.CanStaticData;
import com.cpgps.canbus.common.model.realtime.TravelStatisticalData;
import com.cpgps.canbus.common.ots.TableSpaceDBConection;
import com.cpgps.canbus.common.utils.AlarmConst;
import com.cpgps.canbus.common.utils.CanbusDataUtil;
import com.cpgps.canbus.common.utils.Const;
import com.cpgps.canbus.common.utils.DateUtil;
import com.cpgps.canbus.common.utils.KKTool;
import com.cpgps.canbus.dubbo.service.CarEmsRunStatusService;
import com.cpgps.canbus.repository.entity.can.CarSectionAnalysisEntity;
import com.cpgps.canbus.repository.entity.info.CarEntity;
import com.cpgps.canbus.repository.entity.info.CarRepository;

/**
 * 车辆EMS运行状态接口实现
 * @author LiHuan
 */
@Service
public class CarEmsRunStatusServiceImp implements CarEmsRunStatusService {
	static Logger logger = LoggerFactory.getLogger(CarEmsRunStatusServiceImp.class);
	@Autowired
	private TableSpaceDBConection tableSpaceDBConection;
	@Autowired
	private CarRepository carRepository;

	/**
	 * 获取分析结果
	 */
	@Override
	public CarSectionAnalysisEntity getCarEmsRunStatusEntity(String cldm, Date startTime, Date endTime) {
		List<MessageBean> canStaticMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm),
				DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_CANSTATIC,
				new AtomicInteger(0));
		if (canStaticMessageBeanList.size() < 1)
			return null;
		List<MessageBean> eventMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm),
				DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_EVENT,
				new AtomicInteger(0));
		List<MessageBean> travelStatisticalDataMessageBeanList = tableSpaceDBConection.getRange(Long.valueOf(cldm),
				DateUtil.date2TimeStamp(startTime), DateUtil.date2TimeStamp(endTime), Const.OTS_KEY_TYPE_TRAVELDATA,
				new AtomicInteger(0));
		CarSectionAnalysisEntity carSectionAnalysisEntity = new CarSectionAnalysisEntity();
		carSectionAnalysisEntity.setCph(cldm);
		CarEntity carEntity = carRepository.findByCph(cldm);
		if (carEntity != null) {
			carSectionAnalysisEntity.setFdjxh(carEntity.getFdjh());
		} else {
			carSectionAnalysisEntity.setFdjxh("暂无信息");
		}

		// 天数间隔秒
		long seconds = this.getSeonds(startTime, endTime);
		carSectionAnalysisEntity.setTjrq(DateUtil.DateToString(startTime, DateUtil.YYYY_MM_DD));
		// can静态数据
		countCanStaticMessageEntity(carSectionAnalysisEntity, canStaticMessageBeanList);
		// 事件数据
		countEventMessageEntity(carSectionAnalysisEntity, eventMessageBeanList);
		// 行程数据
		countTravelStatisticalDataEntity(carSectionAnalysisEntity, travelStatisticalDataMessageBeanList);

		initcarEmsRunStatusEntity(carSectionAnalysisEntity, seconds);
		return carSectionAnalysisEntity;
	}

	/**
	 * 获取结束时间和开始时间相差秒
	 * 
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	@SuppressWarnings("unused")
	private long getSeonds(Date sTime, Date eTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long milis = 0;
		Calendar curGc1 = Calendar.getInstance();
		curGc1.setTime(sTime);
		Calendar curGc2 = Calendar.getInstance();
		curGc2.setTime(eTime);
		milis = curGc2.getTime().getTime() - curGc1.getTime().getTime();
		System.out.println(milis / 1000);
		return milis / 1000;
	}


	/**
	 * 封装分析结果
	 */
	private CarSectionAnalysisEntity initcarEmsRunStatusEntity(CarSectionAnalysisEntity carSectionAnalysisEntity,
			long seconds) {
		CanbusDataUtil canbusDataUtil = new CanbusDataUtil();
		// 车辆时间使用率
		// 车辆总里程
		long zlc = canbusDataUtil.getDifference(carSectionAnalysisEntity.getLczdz(),
				carSectionAnalysisEntity.getLczxz());

		// 车辆行车时间
		long xcsj = canbusDataUtil.getDifference(carSectionAnalysisEntity.getYxsjzdz(),
				carSectionAnalysisEntity.getYxsjzxz());
		// 发动机运行时间=行车时间
		long fdjyxsj = xcsj;
		// 车辆熄火时间=结束时间-开始时间-行车时间
		long xhsj = seconds - fdjyxsj;
		// 运转时间比
		double yzsjpt = canbusDataUtil.getPercentage(carSectionAnalysisEntity.getYxsjzdz(), xcsj);

		// 运转时间=行使时间
		long yzsj = xcsj;
		// 行使耗时
		long xshs = canbusDataUtil.getDifference(yzsj, carSectionAnalysisEntity.getDsljsj());
		// 怠速
		long dssj = carSectionAnalysisEntity.getDsljsj();
		// 行使耗时百分比
		double xshspt = canbusDataUtil.getPercentage(yzsj, xshs);

		// 总耗油量
		long zhyl = canbusDataUtil.getDifference(carSectionAnalysisEntity.getZyhzdz(),
				carSectionAnalysisEntity.getZyhzxz());

		// 百公里油耗
		double bglyh = CanbusDataUtil.getFormatNumber(canbusDataUtil.div(zhyl, zlc) * 100);
		// 行驶消耗 行驶油耗=总油耗-怠速油耗
		long xsxh = canbusDataUtil.getDifference(zhyl, carSectionAnalysisEntity.getDsxh());
		// 怠速油耗
		long dsxh = carSectionAnalysisEntity.getDsxh();
		// 行使消耗百分比
		double xhyhpt = canbusDataUtil.getPercentage(zhyl, xsxh);

		// 刹车次数
		long sccs = carSectionAnalysisEntity.getSccs();
		// 刹车距离
		long scjl = carSectionAnalysisEntity.getScjl();
		// 未刹车里程
		long wsclc = canbusDataUtil.getDifference(zlc, carSectionAnalysisEntity.getScjl());
		// 未刹车里程百分比
		double wsclcpt = canbusDataUtil.getPercentage(zlc, wsclc);

		// 重新组装数据
		carSectionAnalysisEntity.setZlc(zlc);
		carSectionAnalysisEntity.setXcsj(xcsj);
		carSectionAnalysisEntity.setXhsj(xhsj);
		carSectionAnalysisEntity.setYzsjpt(yzsjpt);

		carSectionAnalysisEntity.setYzsj(xcsj);
		carSectionAnalysisEntity.setXshs(xshs);
		carSectionAnalysisEntity.setDsljsj(dssj);
		carSectionAnalysisEntity.setXshspt(xshspt);

		carSectionAnalysisEntity.setZyh(zhyl);
		carSectionAnalysisEntity.setBglyh(bglyh);
		carSectionAnalysisEntity.setXsxh(xsxh);
		carSectionAnalysisEntity.setDsxh(dsxh);
		carSectionAnalysisEntity.setXhyhpt(xhyhpt);

		carSectionAnalysisEntity.setSccs(sccs);
		carSectionAnalysisEntity.setWsclc(wsclc);
		carSectionAnalysisEntity.setScjl(scjl);
		carSectionAnalysisEntity.setWsclcpt(wsclcpt);

		return carSectionAnalysisEntity;
	}

	/**
	 * 从can数据中分析统计
	 * 
	 * @param carSectionAnalysisEntity
	 * @param canStaticMessageBeanList
	 * @return
	 */
	private CarSectionAnalysisEntity countCanStaticMessageEntity(CarSectionAnalysisEntity carSectionAnalysisEntity,
			List<MessageBean> canStaticMessageBeanList) {
		CanbusDataUtil canbusDataUtil = new CanbusDataUtil();
		for (MessageBean messageBean : canStaticMessageBeanList) {
			CanStaticData canStaticData = messageBean.getCanStaticData();
			carSectionAnalysisEntity.setZdsd(canbusDataUtil.getZdsd(canStaticData.getSpeed()));
			carSectionAnalysisEntity.setZdzs(canbusDataUtil.getZdzs(canStaticData.getEnginSpeed()));
			carSectionAnalysisEntity.setLczdz(canbusDataUtil.getLczdz(canStaticData.getOdometer()));
			carSectionAnalysisEntity.setLczxz(canbusDataUtil.getLczxz(canStaticData.getOdometer()));
			carSectionAnalysisEntity.setZyhzxz(canbusDataUtil.getZyhzxz(canStaticData.getFulmeter()));
			carSectionAnalysisEntity.setZyhzdz(canbusDataUtil.getZyhzdz(canStaticData.getFulmeter()));
			carSectionAnalysisEntity.setYxsjzdz(canbusDataUtil.getYxsjzdz(canStaticData.getCarTotalRunTime()));
			carSectionAnalysisEntity.setYxsjzxz(canbusDataUtil.getYxsjzxz(canStaticData.getCarTotalRunTime()));
			carSectionAnalysisEntity.setFdjyxsj(canbusDataUtil.getFdjyxsj(canStaticData.getEngineRunTime()));
			carSectionAnalysisEntity.setYblczdz(canbusDataUtil.getYbLczdz(canStaticData.getDashTotalOdermeter()));
			carSectionAnalysisEntity.setYblczxz(canbusDataUtil.getYbLczxz(canStaticData.getDashTotalOdermeter()));
			carSectionAnalysisEntity.setYbzyhzxz(canbusDataUtil.getYbZyhzxz(canStaticData.getDashTotalOil()));
			carSectionAnalysisEntity.setYbzyhzdz(canbusDataUtil.getYbZyhzdz(canStaticData.getDashTotalOil()));
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
	private CarSectionAnalysisEntity countEventMessageEntity(CarSectionAnalysisEntity carSectionAnalysisEntity,
			List<MessageBean> eventMessageBeanList) {
		for (MessageBean messageBean : eventMessageBeanList) {
			byte infoType = KKTool.int2OneByte(messageBean.getInfoType());
			switch (infoType) {
			case Const.CMD_INFO_TYPE_0B:// 报警
				short alarmType = messageBean.getAlarmData().getAlarmType();
				String dateStr = DateUtil.getstrtime(messageBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
				switch (alarmType) {
				case AlarmConst.CMD_INFO_TYPE_0001:// 点火上报
					if (null == carSectionAnalysisEntity.getXckssj() || "".equals(carSectionAnalysisEntity.getXckssj()))
						carSectionAnalysisEntity.setXckssj(dateStr);
					carSectionAnalysisEntity.setXcs(carSectionAnalysisEntity.getXcs() + 1);
					break;
				case AlarmConst.CMD_INFO_TYPE_0002: // 熄火上报
					carSectionAnalysisEntity.setXcs(carSectionAnalysisEntity.getXcs() + 1);
					carSectionAnalysisEntity.setXcjssj(dateStr);
					break;
				case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
					break;
				case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
					AlarmIdlingTooLongData alarmIdlingTooLongData = (AlarmIdlingTooLongData) messageBean.getAlarmData()
							.getAlarmIncidentalData();
					carSectionAnalysisEntity
							.setDsxh(carSectionAnalysisEntity.getDsxh() + alarmIdlingTooLongData.getIdlingFulmeter());
					carSectionAnalysisEntity.setDsljsj(carSectionAnalysisEntity.getDsljsj()+alarmIdlingTooLongData.getIdlingSteayTime());
					break;
				case 0x010D:// 高速空档滑行报警[高速空档滑行报警附加信息]
					carSectionAnalysisEntity.setKdhxcs(carSectionAnalysisEntity.getKdhxcs() + 1);
					break;
				case 0x010c:// 超速报警
					carSectionAnalysisEntity.setCscs(carSectionAnalysisEntity.getCscs() + 1);
					break;
				case 0x2003:// 超速报警结束
					carSectionAnalysisEntity.setCscs(carSectionAnalysisEntity.getCscs() + 1);
					break;
				case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
					AlarmHSpeedNeutralData alarmHSpeedNeutralData = (AlarmHSpeedNeutralData) messageBean.getAlarmData()
							.getAlarmIncidentalData();
					carSectionAnalysisEntity.setKdhxsj(
							carSectionAnalysisEntity.getKdhxsj() + alarmHSpeedNeutralData.getNeutralSteayTime());
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
					AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = (AlarmSuperHighEngineSpeedData) messageBean
							.getAlarmData().getAlarmIncidentalData();
					carSectionAnalysisEntity.setCzsljsj(carSectionAnalysisEntity.getCzsljsj()
							+ alarmSuperHighEngineSpeedData.getEnginSpeedSteayTime());
					carSectionAnalysisEntity.setCzscs(carSectionAnalysisEntity.getCzscs() + 1);
					break;
				}
				break;
			case Const.CMD_INFO_TYPE_0D:// 超速事件
				carSectionAnalysisEntity.setCsljsj(
						carSectionAnalysisEntity.getCsljsj() + messageBean.getEventSuperSpeedData().getOverSpeedTime());
				break;
			case Const.CMD_INFO_TYPE_0E:// 怠速事件
				carSectionAnalysisEntity.setDsljsj(
						carSectionAnalysisEntity.getDsljsj() + messageBean.getEventIdlingData().getIdlingTotalTime());
				break;
			case Const.CMD_INFO_TYPE_0F:// 空挡事件
				carSectionAnalysisEntity.setKdhxsj(
						carSectionAnalysisEntity.getKdhxsj() + messageBean.getEventNeutralData().getNeutralSlideTime());
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
	private CarSectionAnalysisEntity countTravelStatisticalDataEntity(CarSectionAnalysisEntity carSectionAnalysisEntity,
			List<MessageBean> travelStatisticalDataMessageBeanList) {
		for (MessageBean messageBean : travelStatisticalDataMessageBeanList) {
			byte info = KKTool.int2OneByte(messageBean.getInfoType());
			if (info == Const.CMD_INFO_TYPE_11) {
				// 行程统计brakeNum,brakeOdometer,economicSpeedTime,ureaConsume,discharge
				TravelStatisticalData travelStatisticalData = messageBean.getTravelStatisticalData();
				List<Map<String, Object>> list = travelStatisticalData.getMessage();
				for (Map<String, Object> map : list) {
					if (map.get("brakeNum") != null)
						carSectionAnalysisEntity
								.setSccs(carSectionAnalysisEntity.getSccs() + (int) map.get("brakeNum"));
					if (map.get("brakeOdometer") != null)
						carSectionAnalysisEntity
								.setScjl(carSectionAnalysisEntity.getScjl() + (int) map.get("brakeOdometer"));
				}
			}
		}
		return carSectionAnalysisEntity;
	}
}
