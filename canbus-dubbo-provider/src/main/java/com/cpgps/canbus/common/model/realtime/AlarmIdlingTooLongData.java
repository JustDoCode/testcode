package com.cpgps.canbus.common.model.realtime;

/**
 * Copyright  2017 e6gps. All rights reserved.
 *怠速时间过长附带信息
 * @version: V1.0
 */
public class AlarmIdlingTooLongData extends AlarmIncidentalData{
	private static final long serialVersionUID = 1L;
	private short idlingSteayTime;
    private short idlingFulmeter;

    public AlarmIdlingTooLongData(){
    }

    public short getIdlingSteayTime() {
        return idlingSteayTime;
    }

    public void setIdlingSteayTime(short idlingSteayTime) {
        this.idlingSteayTime = idlingSteayTime;
    }

    public short getIdlingFulmeter() {
        return idlingFulmeter;
    }

    public void setIdlingFulmeter(short idlingFulmeter) {
        this.idlingFulmeter = idlingFulmeter;
    }
}
