package com.cpgps.canbus.common.model.realtime;

import java.io.Serializable;

/**
 * Copyright  2017 e6gps. All rights reserved.
 *终端上报故障码事件
 * @version: V1.0
 */
public class EventTroubleCodeData implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte trouCodeNum;
    private String trouCode;

  
    public EventTroubleCodeData(){
    }
  

    public byte getTrouCodeNum() {
        return trouCodeNum;
    }

    public void setTrouCodeNum(byte trouCodeNum) {
        this.trouCodeNum = trouCodeNum;
    }

    public String getTrouCode() {
        return trouCode;
    }

    public void setTrouCode(String trouCode) {
        this.trouCode = trouCode;
    }
}
