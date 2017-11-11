package com.cpgps.canbus.common.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
public final class Const {
	public final static String MQ_NAME_CANBUS = "mq.canbus.analysis";
	/**
	 * 车辆每日任务状态 在 redis中前缀 //1,为正在统计。2.为统计结束
	 */
	public final static String CANBUS_REDISKEY_JOBSTATE = "jobState_";
	/**
	 * 车辆每日统计在redis中的前缀
	 */
	public final static String CANBUS_REDISKEY_VEHICLEANALYSIS = "vehicleAnalysis_";
	/**
	 * 命令最小长度
	 */
	public final static int CMD_MIN_SIZE = 26;
	/**
	 * 车辆登入
	 */
	public final static byte CMD_UNIT_01 = 0x01; //
	/**
	 * 车辆登出
	 */
	public final static byte CMD_UNIT_02 = 0x02; 
	/**
	 * 实时信息上报
	 */
	public final static byte CMD_UNIT_03 = 0x03; 
	/**
	 * GPS定位数据
	 */
    public final static byte CMD_INFO_TYPE_07 = 0x07 ;
    /**
	 * 报警数据
	 */
	public final static byte CMD_INFO_TYPE_09 = 0x09 ; 
	/**
	 *  CAN静态数据
	 */
	public final static byte CMD_INFO_TYPE_0A = 0x0A ;
	/**
	 * 终端报警上报
	 */
	public final static byte CMD_INFO_TYPE_0B = 0x0B ;
	/**
	 * 终端上报超速事件
	 */
	public final static byte CMD_INFO_TYPE_0D = 0x0D ;
	/**
	 * 终端上报怠速事件
	 */
	public final static byte CMD_INFO_TYPE_0E = 0x0E ;
	/**
	 * 终端上报空档滑行
	 */
	public final static byte CMD_INFO_TYPE_0F = 0x0F ; 
	/**
	 *终端上报故障码 
	 */
	public final static byte CMD_INFO_TYPE_10 = 0x10 ; 
	/**
	 *行程统计
	 */
	public final static byte CMD_INFO_TYPE_11 = 0x11 ; 
	
	public final static  int OTS_KEY_TYPE_GPS =1;
	
	public final static  int OTS_KEY_TYPE_CANSTATIC =2;
	
	public final static  int OTS_KEY_TYPE_EVENT =3;
	
	public final static  int OTS_KEY_TYPE_TRAVELDATA =4;
}
