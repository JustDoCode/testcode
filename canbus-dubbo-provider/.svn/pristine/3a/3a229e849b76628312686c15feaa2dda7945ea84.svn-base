package com.cpgps.canbus.common.model.realtime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright  2017 e6gps. All rights reserved.
 *行程统计
 * @version: V1.0
 */
public class TravelStatisticalData implements Serializable {
	private static final long serialVersionUID = 1L;
	private GpsLocation gpsLocation;
    private byte messagePackageNum;
    private List<Map<String,Object>> message = new ArrayList<Map<String, Object>>();
    public TravelStatisticalData(){

    }
    public GpsLocation getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(GpsLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public byte getMessagePackageNum() {
        return messagePackageNum;
    }

    public void setMessagePackageNum(byte messagePackageNum) {
        this.messagePackageNum = messagePackageNum;
    }

    public List<Map<String, Object>> getMessage() {
        return message;
    }

    public void setMessage(List<Map<String, Object>> message) {
        this.message = message;
    }
}
