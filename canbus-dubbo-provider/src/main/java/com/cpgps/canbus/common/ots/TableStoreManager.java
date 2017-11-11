package com.cpgps.canbus.common.ots;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.AsyncClient;
import com.alicloud.openservices.tablestore.ClientConfiguration;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.AlwaysRetryStrategy;
import com.alicloud.openservices.tablestore.model.ColumnValue;
import com.alicloud.openservices.tablestore.model.GetRangeRequest;
import com.alicloud.openservices.tablestore.model.GetRangeResponse;
import com.alicloud.openservices.tablestore.model.PrimaryKeyBuilder;
import com.alicloud.openservices.tablestore.model.PrimaryKeyValue;
import com.alicloud.openservices.tablestore.model.PutRowRequest;
import com.alicloud.openservices.tablestore.model.RangeRowQueryCriteria;
import com.alicloud.openservices.tablestore.model.Row;
import com.alicloud.openservices.tablestore.model.RowPutChange;
import com.cpgps.canbus.common.model.MessageBean;
import com.cpgps.canbus.common.model.realtime.AlarmData;
import com.cpgps.canbus.common.model.realtime.AlarmHSpeedNeutralData;
import com.cpgps.canbus.common.model.realtime.AlarmIdlingTooLongData;
import com.cpgps.canbus.common.model.realtime.AlarmOverSpeed;
import com.cpgps.canbus.common.model.realtime.AlarmSuperHighEngineSpeedData;
import com.cpgps.canbus.common.model.realtime.CanStaticData;
import com.cpgps.canbus.common.model.realtime.EventIdlingData;
import com.cpgps.canbus.common.model.realtime.EventNeutralData;
import com.cpgps.canbus.common.model.realtime.EventSuperSpeedData;
import com.cpgps.canbus.common.model.realtime.EventTroubleCodeData;
import com.cpgps.canbus.common.model.realtime.GpsLocation;
import com.cpgps.canbus.common.model.realtime.TravelStatisticalData;
import com.cpgps.canbus.common.utils.Const;
import com.cpgps.canbus.common.utils.KKTool;
import com.cpgps.canbus.common.utils.PropertiesUtil;


/**
 * Copyright 2017 e6gps. All rights reserved. 写入ots工具类
 * 
 * @version: V1.0
 */
@Component
public class TableStoreManager {
	private final static Logger log = LoggerFactory.getLogger(TableSpaceDBConection.class);
	private static final long probegin = System.currentTimeMillis();
	public static final String TABLE_NAME = PropertiesUtil.getValue("tablespace.canbus.tableName");
	public static final String PK1 = PropertiesUtil.getValue("tablespace.canbus.key1");
	public static final String PK2 = PropertiesUtil.getValue("tablespace.canbus.key2");
	public static final String PK3 = PropertiesUtil.getValue("tablespace.canbus.key3");
	private static String endPoint = PropertiesUtil.getValue("tablespace.endPoint");
	private static String accessId = PropertiesUtil.getValue("tablespace.accessId");
	private static String accessKey = PropertiesUtil.getValue("tablespace.accessKey");
	private static String instanceName = PropertiesUtil.getValue("tablespace.instanceName");
	public int TABLE_COUNT = 5;
	public TableStoreManager() {
		getInstance();
		getAsyncClient();
	}
	// 同步client
	private static SyncClient syncClinet;
	// 异步client
	private static AsyncClient asyncClient;

	// 定义一个静态的方法（调用时再初始化SingletonTest，但是多线程访问时，可能造成重复初始化问题）
	public static SyncClient getInstance() {
		// ClientConfiguration提供了很多配置项，以下只列举部分。
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		// 设置建立连接的超时时间。
		clientConfiguration.setConnectionTimeoutInMillisecond(5000);
		// 设置socket超时时间。
		clientConfiguration.setSocketTimeoutInMillisecond(5000);
		// 设置重试策略，若不设置，采用默认的重试策略。
		clientConfiguration.setRetryStrategy(new AlwaysRetryStrategy());

		if (syncClinet == null) {
			syncClinet = new SyncClient(endPoint, accessId, accessKey, instanceName, clientConfiguration);
		}
		return syncClinet;
	}

	public static AsyncClient getAsyncClient() {
		// ClientConfiguration提供了很多配置项，以下只列举部分。
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		// 设置建立连接的超时时间。
		clientConfiguration.setConnectionTimeoutInMillisecond(5000);
		// 设置socket超时时间。
		clientConfiguration.setSocketTimeoutInMillisecond(5000);
		// 设置重试策略，若不设置，采用默认的重试策略。
		clientConfiguration.setRetryStrategy(new AlwaysRetryStrategy());

		if (asyncClient == null) {
			asyncClient = new AsyncClient(endPoint, accessId, accessKey, instanceName, clientConfiguration);
		}
		return asyncClient;
	}

	public void putRow(String objStr, AtomicInteger count) {
		JSONObject obj = JSONObject.parseObject(objStr);
		short infoType = obj.getShortValue("infoType");
		String createTime = obj.getString("createTime");
		Long newTime =KKTool.date2TimeStamp(createTime, "yyyy-MM-dd HH:mm:ss");
		PrimaryKeyBuilder builder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		
		builder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromLong(obj.getIntValue("vehicleId")));
		// gps
		if (infoType == Const.CMD_INFO_TYPE_07) {
			builder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(1));
			// 静态
		} else if (infoType == Const.CMD_INFO_TYPE_0A) {
			builder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(2));
			// 统计
		} else if (infoType == Const.CMD_INFO_TYPE_11) {
			builder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(4));
			// 事件
		} else {
			builder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(3));
		}
		builder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(newTime));
		RowPutChange putChange = new RowPutChange(TableSpaceDBConection.TABLE_NAME, builder.build());
		putChange.addColumn("vehicleCode", ColumnValue.fromString(obj.getString("vehicleCode")));
		putChange.addColumn("reciveTime", ColumnValue.fromString(obj.getString("reciveTime")));
		putChange.addColumn("equipNum", ColumnValue.fromString(obj.getString("equipNum")));
		putChange.addColumn("infoType", ColumnValue.fromLong(infoType));
		switch (infoType) {
		case Const.CMD_INFO_TYPE_07:
			putChange.addColumn("gpsLocation", ColumnValue.fromString(obj.getString("gpsLocation")));
			break;
		case Const.CMD_INFO_TYPE_09:
			log.debug("---报警数据:" + 0x09);
			break;
		case Const.CMD_INFO_TYPE_0A:
			putChange.addColumn("CanStaticData", ColumnValue.fromString(obj.getString("canStaticData")));
			break;
		case Const.CMD_INFO_TYPE_0B:
			putChange.addColumn("alarmData", ColumnValue.fromString(obj.getString("alarmData")));
			break;
		case Const.CMD_INFO_TYPE_0D:
			putChange.addColumn("eventSuperSpeedData", ColumnValue.fromString(obj.getString("eventSuperSpeedData")));
			break;
		case Const.CMD_INFO_TYPE_0E:
			putChange.addColumn("eventIdlingData", ColumnValue.fromString(obj.getString("eventIdlingData")));
			break;
		case Const.CMD_INFO_TYPE_0F:
			putChange.addColumn("eventNeutralData", ColumnValue.fromString(obj.getString("eventNeutralData")));
			break;
		case Const.CMD_INFO_TYPE_10:
			putChange.addColumn("eventTroubleCodeData", ColumnValue.fromString(obj.getString("eventTroubleCodeData")));
			break;
		case Const.CMD_INFO_TYPE_11:
			putChange.addColumn("travelStatisticalData", ColumnValue.fromString(obj.getString("travelStatisticalData")));
			break;
		}
		syncClinet.putRow(new PutRowRequest(putChange));
		count.incrementAndGet();
	}

	public List<MessageBean> getRange(String cldm, Long startTime, Long endTime, int type, AtomicInteger count) {
		count.incrementAndGet();
		int num = 0;
		long begin = System.currentTimeMillis();
		RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria(TableSpaceDBConection.TABLE_NAME);
		// 设置起始主键
		PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		primaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromString(cldm));
		primaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(type));
		primaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(startTime));
		rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilder.build());
		// 设置结束主键
		primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		primaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromString(cldm));
		primaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(type));
		primaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(endTime));
		rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilder.build());
		rangeRowQueryCriteria.setMaxVersions(1);
		List<MessageBean> messageBeanList = new ArrayList<MessageBean>();
		// 默认读取所有的属性列
		while (true) {
			GetRangeResponse getRangeResponse = syncClinet.getRange(new GetRangeRequest(rangeRowQueryCriteria));
			for (Row row : getRangeResponse.getRows()) {
				MessageBean messageBean = new MessageBean();
				messageBean.setVehicleId(String.valueOf(row.getPrimaryKey().getPrimaryKeyColumn("c_cldm").getValue()));
				messageBean.setCreateTime(row.getPrimaryKey().getPrimaryKeyColumn("createTime").getValue().toString());
				messageBean.setVehicleCode(row.getLatestColumn("vehicleCode").getValue().asString());
				messageBean.setEquipNum(String.valueOf(row.getLatestColumn("equipNum").getValue()));
				int infoType = Integer.parseInt(String.valueOf(row.getLatestColumn("infoType").getValue()));
				messageBean.setInfoType(infoType);
				switch (infoType) {
				case Const.CMD_INFO_TYPE_07:
					messageBean.setGpsLocation(JSON.parseObject(row.getLatestColumn("gpsLocation").getValue().asString(), GpsLocation.class));
					break;
				case Const.CMD_INFO_TYPE_09:
					log.debug("---报警数据:" + 0x09);
					break;
				case Const.CMD_INFO_TYPE_0A:
					messageBean.setCanStaticData(JSON.parseObject(row.getLatestColumn("CanStaticData").getValue().asString(), CanStaticData.class));
					break;
				case Const.CMD_INFO_TYPE_0B:
					messageBean.setAlarmData(JSON.parseObject(row.getLatestColumn("alarmData").getValue().asString(), AlarmData.class));
					messageBean.setAlarmData(JSON.parseObject(row.getLatestColumn("alarmData").getValue().asString(), AlarmData.class));
					String jsonStr = row.getLatestColumn("alarmData").getValue().asString();
					JSONObject jsonObj = JSON.parseObject(jsonStr);
					switch (messageBean.getAlarmData().getAlarmType()) {
					case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
						AlarmIdlingTooLongData alarmIdlingTooLongData1 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmIdlingTooLongData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmIdlingTooLongData1);
						break;
					case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
						AlarmIdlingTooLongData alarmIdlingTooLongData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmIdlingTooLongData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmIdlingTooLongData);
						break;
					case 0x010D:// 高速空档滑行报警开始 [高速空档滑行报警附加信息]
						AlarmHSpeedNeutralData alarmHSpeedNeutralData1 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmHSpeedNeutralData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmHSpeedNeutralData1);
						break;
					case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
						AlarmHSpeedNeutralData alarmHSpeedNeutralData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmHSpeedNeutralData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmHSpeedNeutralData);
						break;
					case 0x0700:// 超高转速报警开始[超高转速报警附带信息]
						AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData1 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmSuperHighEngineSpeedData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmSuperHighEngineSpeedData1);
						break;
					case 0x2007:// 超高转速报警结束[超高转速报警附带信息]
						AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmSuperHighEngineSpeedData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmSuperHighEngineSpeedData);
						break;
					case 0x010c:// 超速报警结束[报警附带信息]
						AlarmOverSpeed alarmOverSpeed1 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed1);
						break;
					case 0x2003:// 超速报警结束[超速报警附带信息]
						AlarmOverSpeed alarmOverSpeed = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed);
						break;
					}
					break;
				case Const.CMD_INFO_TYPE_0D:
					messageBean.setEventSuperSpeedData(JSON.parseObject(row.getLatestColumn("eventSuperSpeedData").getValue().asString(), EventSuperSpeedData.class));
					break;
				case Const.CMD_INFO_TYPE_0E:
					messageBean.setEventIdlingData(JSON.parseObject(row.getLatestColumn("eventIdlingData").getValue().asString(), EventIdlingData.class));
					break;
				case Const.CMD_INFO_TYPE_0F:
					messageBean.setEventNeutralData(JSON.parseObject(row.getLatestColumn("eventNeutralData").getValue().asString(), EventNeutralData.class));
					break;
				case Const.CMD_INFO_TYPE_10:
					messageBean.setEventTroubleCodeData(JSON.parseObject(row.getLatestColumn("eventTroubleCodeData").getValue().asString(), EventTroubleCodeData.class));
					break;
				case Const.CMD_INFO_TYPE_11:
					messageBean.setTravelStatisticalData(JSON.parseObject(row.getLatestColumn("travelStatisticalData").getValue().asString(), TravelStatisticalData.class));
					break;
				}
				messageBeanList.add(messageBean);
			}
			
			num += getRangeResponse.getRows().size();
			log.info("*********OTS返回车辆编号{}，查询起始日期{}-结束日期{}，类型编号{}数据条数：{}*********", cldm,startTime,endTime, type, getRangeResponse.getRows().size());
			// 若nextStartPrimaryKey不为null, 则继续读取.
			if (getRangeResponse.getNextStartPrimaryKey() != null) {
				rangeRowQueryCriteria.setInclusiveStartPrimaryKey(getRangeResponse.getNextStartPrimaryKey());
			} else {
				count.decrementAndGet();
				break;
			}
		} 
		
		long end = System.currentTimeMillis();
		log.info("**********消耗时间:" + (end - begin) + ",计数:" + count.incrementAndGet() + ",总条数：" + num + ",总时间：" + (end - probegin) + ",平均每秒:" + (((double) num / (end - begin)) * 1000)+"*********");
		return messageBeanList;
	}
	
	public static void main(String[] args) {
		
	}
}
