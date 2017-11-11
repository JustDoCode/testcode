package com.cpgps.canbus.common.model.realtime;

/**
 * Copyright 2017 e6gps. All rights reserved. 高速空档滑行报警附带信息
 * 
 * @version: V1.0
 */
public class AlarmHSpeedNeutralData extends AlarmIncidentalData {
	private static final long serialVersionUID = 1L;
	private short neutralSteayTime;

	public AlarmHSpeedNeutralData() {
	}

	public short getNeutralSteayTime() {
		return neutralSteayTime;
	}

	public void setNeutralSteayTime(short neutralSteayTime) {
		this.neutralSteayTime = neutralSteayTime;
	}
}
