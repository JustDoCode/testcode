package com.cpgps.canbus.common.model.realtime;
/**
 * Copyright  2017 e6gps. All rights reserved.
 *超高转速报警附带信息
 * @version: V1.0
 */
public class AlarmSuperHighEngineSpeedData extends AlarmIncidentalData {
	private static final long serialVersionUID = 1L;
	private short enginSpeedSteayTime;

    public AlarmSuperHighEngineSpeedData(){
    }

    public short getEnginSpeedSteayTime() {
        return enginSpeedSteayTime;
    }

    public void setEnginSpeedSteayTime(short enginSpeedSteayTime) {
        this.enginSpeedSteayTime = enginSpeedSteayTime;
    }
}
