package com.cpgps.canbus.common.model.realtime;
import java.io.Serializable;

/**
 * Copyright  2017 e6gps. All rights reserved.
 *终端上报空档滑行事件
 * @version: V1.0
 */
public class EventNeutralData implements Serializable {
	private static final long serialVersionUID = -3217012164120361024L;
	private String neutralSlideStartTime;
    private String neutralSlideStopTime;
    private short neutralSlideTime;
    public EventNeutralData(){
    }

    public String getNeutralSlideStartTime() {
        return neutralSlideStartTime;
    }

    public void setNeutralSlideStartTime(String neutralSlideStartTime) {
        this.neutralSlideStartTime = neutralSlideStartTime;
    }

    public String getNeutralSlideStopTime() {
        return neutralSlideStopTime;
    }

    public void setNeutralSlideStopTime(String neutralSlideStopTime) {
        this.neutralSlideStopTime = neutralSlideStopTime;
    }

    public short getNeutralSlideTime() {
        return neutralSlideTime;
    }

    public void setNeutralSlideTime(short neutralSlideTime) {
        this.neutralSlideTime = neutralSlideTime;
    }
}
