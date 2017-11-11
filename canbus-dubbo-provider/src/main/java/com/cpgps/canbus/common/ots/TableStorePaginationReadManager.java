package com.cpgps.canbus.common.ots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.AsyncClient;
import com.alicloud.openservices.tablestore.ClientConfiguration;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.core.utils.Pair;
import com.alicloud.openservices.tablestore.core.utils.Preconditions;
import com.alicloud.openservices.tablestore.model.AlwaysRetryStrategy;
import com.alicloud.openservices.tablestore.model.GetRangeRequest;
import com.alicloud.openservices.tablestore.model.GetRangeResponse;
import com.alicloud.openservices.tablestore.model.PrimaryKey;
import com.alicloud.openservices.tablestore.model.PrimaryKeyBuilder;
import com.alicloud.openservices.tablestore.model.PrimaryKeyValue;
import com.alicloud.openservices.tablestore.model.RangeRowQueryCriteria;
import com.alicloud.openservices.tablestore.model.Row;
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
import com.cpgps.canbus.common.utils.PropertiesUtil;
@Component
public class TableStorePaginationReadManager {
	private final static Logger log = LoggerFactory.getLogger(TableStorePaginationReadManager.class);
	public static final String TABLE_NAME = PropertiesUtil.getValue("tablespace.canbus.tableName");
	public static final String PK1 = PropertiesUtil.getValue("tablespace.canbus.key1");
	public static final String PK2 = PropertiesUtil.getValue("tablespace.canbus.key2");
	public static final String PK3 = PropertiesUtil.getValue("tablespace.canbus.key3");
	private static String endPoint = PropertiesUtil.getValue("tablespace.endPoint");
	private static String accessId = PropertiesUtil.getValue("tablespace.accessId");
	private static String accessKey = PropertiesUtil.getValue("tablespace.accessKey");
	private static String instanceName = PropertiesUtil.getValue("tablespace.instanceName");
	public int TABLE_COUNT = 5;
	public static final int BASE_PAGESIZE = 5000;
	public TableStorePaginationReadManager() {
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

	/**
	 * 范围查询指定范围内的数据，返回指定页数大小的数据，并能根据offset跳过部分行。
	 */
	private Pair<List<Row>, PrimaryKey> readByPage(PrimaryKey startKey, PrimaryKey endKey, int offset, int pageSize) {
		Preconditions.checkArgument(offset >= 0, "Offset should not be negative.");
		Preconditions.checkArgument(pageSize > 0, "Page size should be greater than 0.");

		List<Row> rows = new ArrayList<Row>(pageSize);
		int limit = pageSize;
		int skip = offset;

		PrimaryKey nextStart = startKey;
		while (limit > 0 && nextStart != null) {
			RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria(TableStorePaginationReadManager.TABLE_NAME);
			rangeRowQueryCriteria.setInclusiveStartPrimaryKey(nextStart);
			rangeRowQueryCriteria.setExclusiveEndPrimaryKey(endKey);
			rangeRowQueryCriteria.setLimit(skip + limit);
			rangeRowQueryCriteria.setMaxVersions(1);
			GetRangeRequest request = new GetRangeRequest();
			request.setRangeRowQueryCriteria(rangeRowQueryCriteria);
			syncClinet.getRange(new GetRangeRequest(rangeRowQueryCriteria));
			GetRangeResponse getRangeResponse = syncClinet.getRange(request);
			for (Row row : getRangeResponse.getRows()) {
				if (skip > 0) {
					skip--;
				} else {
					rows.add(row);
					limit--;
				}
			}
			nextStart = getRangeResponse.getNextStartPrimaryKey();
		}

		return new Pair<List<Row>, PrimaryKey>(rows, nextStart);
	}

	private void readByPage(SyncClient client, String tableName, String cldm, long startTime, long endTime, long type, int offset, int pageSize) {

		// 设置起始主键
		PrimaryKeyBuilder startPrimaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		startPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromString(cldm));
		startPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(type));
		startPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(startTime));
		// 设置结束主键
		PrimaryKeyBuilder endPrimaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		endPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromString(cldm));
		endPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(type));
		endPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(endTime));

		// 读第一页，从范围的offset=0的行开始读起
		Pair<List<Row>, PrimaryKey> result = readByPage(startPrimaryKeyBuilder.build(), endPrimaryKeyBuilder.build(), offset, pageSize);
		for (Row row : result.getFirst()) {
			System.out.println(row.getColumns());
		}
		System.out.println("Total rows count: " + result.getFirst().size());

		// 顺序翻页，读完范围内的所有数据
		PrimaryKey startKey = result.getSecond();
		PrimaryKey endKey = endPrimaryKeyBuilder.build();
		while (startKey != null) {
			result = readByPage(startKey, endKey, 0, pageSize);
			for (Row row : result.getFirst()) {
				System.out.println(row.getColumns());
			}
			startKey = result.getSecond();
			log.info("ots total rows count: " + result.getFirst().size());
		}
	}

	private Map<List<Row>, Integer> readOtsByPage(String cldm, long startTime, long endTime, long type, int offset, int pageSize) {
		int totalSize = 0;
		// 设置起始主键
		PrimaryKeyBuilder startPrimaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		startPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromString(cldm));
		startPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(type));
		startPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(startTime));
		// 设置结束主键
		PrimaryKeyBuilder endPrimaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
		endPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK1, PrimaryKeyValue.fromString(cldm));
		endPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK2, PrimaryKeyValue.fromLong(type));
		endPrimaryKeyBuilder.addPrimaryKeyColumn(TableSpaceDBConection.PK3, PrimaryKeyValue.fromLong(endTime));
		Pair<List<Row>, PrimaryKey> result = readByPage(startPrimaryKeyBuilder.build(), endPrimaryKeyBuilder.build(), offset, pageSize);
		totalSize = getTotalSize(startPrimaryKeyBuilder, endPrimaryKeyBuilder, offset);
		Map<List<Row>, Integer> map = new HashMap<List<Row>, Integer>();
		map.put(result.getFirst(), totalSize);
		return map;
	}

	public List<MessageBean> getMessageBeanList(List<Row> rowList) {
		List<MessageBean> messageBeanList = new ArrayList<MessageBean>();
		for (Row row : rowList) {
			try {
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
					String jsonStr = row.getLatestColumn("alarmData").getValue().asString();
					JSONObject jsonObj = JSON.parseObject(jsonStr);
					switch (messageBean.getAlarmData().getAlarmType()) {
					case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
						break;
					case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
						AlarmIdlingTooLongData alarmIdlingTooLongData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmIdlingTooLongData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmIdlingTooLongData);
						break;
					case 0x010D:// 高速空档滑行报警开始 [高速空档滑行报警附加信息]
						break;
					case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
						AlarmHSpeedNeutralData alarmHSpeedNeutralData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmHSpeedNeutralData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmHSpeedNeutralData);
						break;
					case 0x0700:// 超高转速报警开始[超高转速报警附带信息]
						break;
					case 0x2007:// 超高转速报警结束[超高转速报警附带信息]
						AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmSuperHighEngineSpeedData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmSuperHighEngineSpeedData);
						break;
					case 0x010c:// 超速报警开始[报警附带信息]
						break;
					case 0x2003:// 超速报警结束[超速报警附带信息]
						AlarmOverSpeed alarmOverSpeed = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed);
						break;
					case 0x0701:// 低档高速报警[低档高速报警附加信息]
						break;
					case 0x2008:// 低档高速报警结束[低档高速报警附加信息]
						AlarmOverSpeed alarmOverSpeed1 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed1);
						break;
					case 0x0702:// 高档低速报警[高档低速报警附加信息]
						break;
					case 0x2009:// 高档低速报警结束[高档低速报警附加信息]
						AlarmOverSpeed alarmOverSpeed2 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed2);
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

			} catch (Exception e) {
				log.error("读取ots封装异常,类型：{},设备号{}，json串：{}", row.getLatestColumn("infoType").getValue(), row.getLatestColumn("equipNum").getValue(),row.getLatestColumn("travelStatisticalData").getValue().asString());
			}

		}
		return messageBeanList;
	}

	public int getTotalSize(PrimaryKeyBuilder startPrimaryKeyBuilder, PrimaryKeyBuilder endPrimaryKeyBuilder, int offset) {
		int totalSize = 0;
		// 读第一页，从范围的offset=0的行开始读起
		Pair<List<Row>, PrimaryKey> result = readByPage(startPrimaryKeyBuilder.build(), endPrimaryKeyBuilder.build(), offset, BASE_PAGESIZE);
		totalSize += result.getFirst().size();
		// 顺序翻页，读完范围内的所有数据
		PrimaryKey startKey = result.getSecond();
		PrimaryKey endKey = endPrimaryKeyBuilder.build();
		while (startKey != null) {
			result = readByPage(startKey, endKey, 0, BASE_PAGESIZE);
			startKey = result.getSecond();
			totalSize += result.getFirst().size();
		}
		return totalSize;

	}
	

	public static void main(String[] args) {
		TableStorePaginationReadManager ots = new TableStorePaginationReadManager();
		// ots.readOtsByPage("360059599", 1499320346, 1499332838, 3, 0, 10);
		List<Row> rowList = null;
		int totalSize = 0;
		Map<List<Row>, Integer> map = ots.readOtsByPage("360059599", 1499320346, 1499332838, 3, 0, 23);
		for (Entry<List<Row>, Integer> entry : map.entrySet()) {
			rowList = entry.getKey();
			totalSize = entry.getValue();
		}
		 System.out.println("获取到："+rowList.size()+"，总共："+totalSize);
	}

	public Map<List<MessageBean>,Integer> getRange(String cldm, Long startTime, Long endTime, int type, int offset, int pageSize) {
		Map<List<Row>, Integer> map = readOtsByPage(cldm, startTime, endTime, type, offset, pageSize);
		Map<List<MessageBean>,Integer> resultMap = new HashMap<>();
		List<MessageBean> messageBeanList = new ArrayList<MessageBean>();
		List<Row> rowList = null;
		int totalSize = 0;
		for (Entry<List<Row>, Integer> entry : map.entrySet()) {
			rowList = entry.getKey();
			totalSize = entry.getValue();
		}
		for (Row row : rowList) {
			try {
				MessageBean messageBean = new MessageBean();
				messageBean.setVehicleId(String.valueOf(row.getPrimaryKey().getPrimaryKeyColumn("c_cldm").getValue()));
				messageBean.setCreateTime(row.getPrimaryKey().getPrimaryKeyColumn("createTime").getValue().toString());
				messageBean.setVehicleCode(row.getLatestColumn("vehicleCode").getValue().asString());
				messageBean.setEquipNum(String.valueOf(row.getLatestColumn("equipNum").getValue()));
				int infoType = Integer.parseInt(String.valueOf(row.getLatestColumn("infoType").getValue()));
				messageBean.setReciveTime(String.valueOf(row.getLatestColumn("reciveTime").getValue()));
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
					String jsonStr = row.getLatestColumn("alarmData").getValue().asString();
					JSONObject jsonObj = JSON.parseObject(jsonStr);
					switch (messageBean.getAlarmData().getAlarmType()) {
					case 0x0006:// 汽车怠速过长上报[怠速时间过长附带信息]
						break;
					case 0x2001:// 汽车怠速过长告警结束[怠速时间过长附带信息]
						AlarmIdlingTooLongData alarmIdlingTooLongData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmIdlingTooLongData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmIdlingTooLongData);
						break;
					case 0x010D:// 高速空档滑行报警开始 [高速空档滑行报警附加信息]
						break;
					case 0x2006:// 高速空档滑行报警结束[高速空档滑行报警附加信息]
						AlarmHSpeedNeutralData alarmHSpeedNeutralData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmHSpeedNeutralData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmHSpeedNeutralData);
						break;
					case 0x0700:// 超高转速报警开始[超高转速报警附带信息]
						break;
					case 0x2007:// 超高转速报警结束[超高转速报警附带信息]
						AlarmSuperHighEngineSpeedData alarmSuperHighEngineSpeedData = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmSuperHighEngineSpeedData.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmSuperHighEngineSpeedData);
						break;
					case 0x010c:// 超速报警开始[报警附带信息]
						break;
					case 0x2003:// 超速报警结束[超速报警附带信息]
						AlarmOverSpeed alarmOverSpeed = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed);
						break;
					case 0x0701:// 低档高速报警[低档高速报警附加信息]
						break;
					case 0x2008:// 低档高速报警结束[低档高速报警附加信息]
						AlarmOverSpeed alarmOverSpeed1 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed1);
						break;
					case 0x0702:// 高档低速报警[高档低速报警附加信息]
						break;
					case 0x2009:// 高档低速报警结束[高档低速报警附加信息]
						AlarmOverSpeed alarmOverSpeed2 = JSON.parseObject(jsonObj.get("alarmIncidentalData").toString(), AlarmOverSpeed.class);
						messageBean.getAlarmData().setAlarmIncidentalData(alarmOverSpeed2);
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

			} catch (Exception e) {
				log.error("读取ots封装异常,类型：{},设备号{}", row.getLatestColumn("infoType").getValue(), row.getLatestColumn("equipNum").getValue());
			}

		}
		resultMap.put(messageBeanList, totalSize);
		return resultMap;
	}

	

}