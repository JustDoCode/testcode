package com.cpgps.canbus.common.model.realtime;

import java.io.Serializable;

/**
 * Copyright 2017 e6gps. All rights reserved.
 *
 * @version: V1.0
 */
public class GpsLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String vehicleCode; // 车辆唯一识别码
	private String equipNum; // 设备编号
	private String createTime; // 数据采集时间
	private String reciveTime; // 数据接收时间
	private int lon; // 经度
	private int lat; // 纬度
	private byte loStatus; // 定位状态;0 无,1 1D,2 2D ,3 3D
	private short gpsSpeed; // 车辆行使速度（0~220KM/h）
	private short direction; // 行使方向;用角度表示（0~360）
	private short accStatus; // 点熄火状态。1：点火 0：熄火
	private int altitude; // 海拔高度m,显示以值除以10处理
	private byte loStarsNum; // 定位星数
	private int obdSpeed; // OBD速度0.1km/h
	private int odometer; // 车辆累积行驶里程，单位为m
	private int fulmeter; // 车辆累积油耗，单位为ml
	private int runTimeTotal; // 车辆总运行时间,单位为秒

	public GpsLocation() {
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReciveTime() {
		return reciveTime;
	}

	public void setReciveTime(String reciveTime) {
		this.reciveTime = reciveTime;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public String getEquipNum() {
		return equipNum;
	}

	public void setEquipNum(String equipNum) {
		this.equipNum = equipNum;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public byte getLoStatus() {
		return loStatus;
	}

	public void setLoStatus(byte loStatus) {
		this.loStatus = loStatus;
	}

	public short getGpsSpeed() {
		return gpsSpeed;
	}

	public void setGpsSpeed(short gpsSpeed) {
		this.gpsSpeed = gpsSpeed;
	}

	public short getDirection() {
		return direction;
	}

	public void setDirection(short direction) {
		this.direction = direction;
	}

	public short getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(short accStatus) {
		this.accStatus = accStatus;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public byte getLoStarsNum() {
		return loStarsNum;
	}

	public void setLoStarsNum(byte loStarsNum) {
		this.loStarsNum = loStarsNum;
	}

	public int getObdSpeed() {
		return obdSpeed;
	}

	public void setObdSpeed(int obdSpeed) {
		this.obdSpeed = obdSpeed;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public int getFulmeter() {
		return fulmeter;
	}

	public void setFulmeter(int fulmeter) {
		this.fulmeter = fulmeter;
	}

	public int getRunTimeTotal() {
		return runTimeTotal;
	}

	public void setRunTimeTotal(int runTimeTotal) {
		this.runTimeTotal = runTimeTotal;
	}
}
