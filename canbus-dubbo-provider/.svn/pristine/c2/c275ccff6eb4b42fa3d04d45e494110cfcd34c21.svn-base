package com.cpgps.canbus.common.model.realtime;

import java.io.Serializable;

import org.jboss.netty.buffer.ChannelBuffer;
/**
 * Copyright  2017 e6gps. All rights reserved.
 *
 * @version: V1.0
 */
public class CanStaticData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int batVoltage;      //电瓶电压，单位V
    private int  odometerType;       //累积里程类型 0：Gps方式 1：obd方式，2，无效
    private int odometer;           //车辆累积行驶里程，单位为m
    private int fulmeter;           //车辆累积油耗，单位为ml
    private int  trouLightStatus;    //故障灯状态 0:开，1关
    private int  trouCodeNum;        //发动机故障码个数
    private int enginSpeed;         //发动机转速
    private int  speed;              //车辆当前速度
    private int  airIntakeTemp;      //进气口温度(0~255)
    private int  coolantTemp;        //冷却液温度(0~255)
    private int  carAmbientTemp;     //车辆环境温度（0~255）
    private int  intakeTubPressure;  //进气歧管压力(10~105kpa)
    private int oilPressure;        //燃油压力
    private int  airPressure;        //大气压力
    private int airFlowRate;        //空气流量，显示值为上传值/10
    private int valvePositionSensor;//气门位置传感器，显示值为上传值/10
    private int oilPedalPosition;   //油门踏板位置，显示值为上传值/10( 0~100)
    private int engineRunTime;      //发动机运行时间，一个驾驶循环的运行时间
    private int trouRunOdemeter;    //故障行驶里程km
    private int remainOil;          //剩余油量，单位为L，显示值为上传值/10
    private int  engineLoad;         //发动机负荷，0~100
    private int oilCorrection;      //燃油修正，显示值为上传值/10
    private int ignitionAngle;      //显示值为(上传值/10)-64
    private int dashTotalOdermeter; //仪表总里程，汽车仪表总里程，单位m,显示值除以1000处理
    private int carTotalRunTime;    //车辆总运行时间，单位秒
    private int dashTotalOil;       //仪表上显示总的用油量，单位为ml(毫升)，显示值除以1000,处理

    public CanStaticData(){
    }
    

    public int getBatVoltage() {
        return batVoltage;
    }

    public void setBatVoltage(int batVoltage) {
        this.batVoltage = batVoltage;
    }

    public int  getOdometerType() {
        return odometerType;
    }

    public void setOdometerType(int  odometerType) {
        this.odometerType = odometerType;
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

    public int  getTrouLightStatus() {
        return trouLightStatus;
    }

    public void setTrouLightStatus(int  trouLightStatus) {
        this.trouLightStatus = trouLightStatus;
    }

    public int  getTrouCodeNum() {
        return trouCodeNum;
    }

    public void setTrouCodeNum(int  trouCodeNum) {
        this.trouCodeNum = trouCodeNum;
    }

    public int getEnginSpeed() {
        return enginSpeed;
    }

    public void setEnginSpeed(int enginSpeed) {
        this.enginSpeed = enginSpeed;
    }

    public int  getSpeed() {
        return speed;
    }

    public void setSpeed(int  speed) {
        this.speed = speed;
    }

    public int  getAirIntakeTemp() {
        return airIntakeTemp;
    }

    public void setAirIntakeTemp(int  airIntakeTemp) {
        this.airIntakeTemp = airIntakeTemp;
    }

    public int  getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(int  coolantTemp) {
        this.coolantTemp = coolantTemp;
    }

    public int  getCarAmbientTemp() {
        return carAmbientTemp;
    }

    public void setCarAmbientTemp(int  carAmbientTemp) {
        this.carAmbientTemp = carAmbientTemp;
    }

    public int  getIntakeTubPressure() {
        return intakeTubPressure;
    }

    public void setIntakeTubPressure(int  intakeTubPressure) {
        this.intakeTubPressure = intakeTubPressure;
    }

    public int getOilPressure() {
        return oilPressure;
    }

    public void setOilPressure(int oilPressure) {
        this.oilPressure = oilPressure;
    }

    public int  getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(int  airPressure) {
        this.airPressure = airPressure;
    }

    public int getAirFlowRate() {
        return airFlowRate;
    }

    public void setAirFlowRate(int airFlowRate) {
        this.airFlowRate = airFlowRate;
    }

    public int getValvePositionSensor() {
        return valvePositionSensor;
    }

    public void setValvePositionSensor(int valvePositionSensor) {
        this.valvePositionSensor = valvePositionSensor;
    }

    public int getOilPedalPosition() {
        return oilPedalPosition;
    }

    public void setOilPedalPosition(int oilPedalPosition) {
        this.oilPedalPosition = oilPedalPosition;
    }

    public int getEngineRunTime() {
        return engineRunTime;
    }

    public void setEngineRunTime(int engineRunTime) {
        this.engineRunTime = engineRunTime;
    }

    public int getTrouRunOdemeter() {
        return trouRunOdemeter;
    }

    public void setTrouRunOdemeter(int trouRunOdemeter) {
        this.trouRunOdemeter = trouRunOdemeter;
    }

    public int getRemainOil() {
        return remainOil;
    }

    public void setRemainOil(int remainOil) {
        this.remainOil = remainOil;
    }

    public int  getEngineLoad() {
        return engineLoad;
    }

    public void setEngineLoad(int  engineLoad) {
        this.engineLoad = engineLoad;
    }

    public int getOilCorrection() {
        return oilCorrection;
    }

    public void setOilCorrection(int oilCorrection) {
        this.oilCorrection = oilCorrection;
    }

    public int getIgnitionAngle() {
        return ignitionAngle;
    }

    public void setIgnitionAngle(int ignitionAngle) {
        this.ignitionAngle = ignitionAngle;
    }

    public int getDashTotalOdermeter() {
        return dashTotalOdermeter;
    }

    public void setDashTotalOdermeter(int dashTotalOdermeter) {
        this.dashTotalOdermeter = dashTotalOdermeter;
    }

    public int getCarTotalRunTime() {
        return carTotalRunTime;
    }

    public void setCarTotalRunTime(int carTotalRunTime) {
        this.carTotalRunTime = carTotalRunTime;
    }

    public int getDashTotalOil() {
        return dashTotalOil;
    }

    public void setDashTotalOil(int dashTotalOil) {
        this.dashTotalOil = dashTotalOil;
    }
}
