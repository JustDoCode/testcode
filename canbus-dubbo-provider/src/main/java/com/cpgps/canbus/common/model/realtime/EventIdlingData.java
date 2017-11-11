package com.cpgps.canbus.common.model.realtime;

/**
 * Copyright  2017 e6gps. All rights reserved.
 *终端上报怠速事件
 * @version: V1.0
 */
public class EventIdlingData extends AlarmIncidentalData{
	private static final long serialVersionUID = 1L;
	private String idlingStartTime;
    private String idlingStopTime;
    private short idlingTime;
    private int   idlingTotalTime;

    public EventIdlingData(){
    }
   

    public String getIdlingStartTime() {
        return idlingStartTime;
    }

    public void setIdlingStartTime(String idlingStartTime) {
        this.idlingStartTime = idlingStartTime;
    }

    public String getIdlingStopTime() {
        return idlingStopTime;
    }

    public void setIdlingStopTime(String idlingStopTime) {
        this.idlingStopTime = idlingStopTime;
    }

    public short getIdlingTime() {
        return idlingTime;
    }

    public void setIdlingTime(short idlingTime) {
        this.idlingTime = idlingTime;
    }

    public int getIdlingTotalTime() {
        return idlingTotalTime;
    }

    public void setIdlingTotalTime(int idlingTotalTime) {
        this.idlingTotalTime = idlingTotalTime;
    }
}
