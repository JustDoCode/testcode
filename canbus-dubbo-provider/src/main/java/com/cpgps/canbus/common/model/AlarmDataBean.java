package com.cpgps.canbus.common.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class AlarmDataBean {
	private String cldm;//车辆代码
	private long kssj;//开始时间
	private long jssj;//结束时间
	private int alarmType;//报警类型
	private double ksjd;//报警开始经度
	private double kswd;//报警开始纬度
	private double jsjd;//报警结束经度
	private double jswd;//报警结束纬度
	private String color;//颜色
	public String getCldm() {
		return cldm;
	}
	public void setCldm(String cldm) {
		this.cldm = cldm;
	}
	public long getKssj() {
		return kssj;
	}
	public void setKssj(long kssj) {
		this.kssj = kssj;
	}
	public long getJssj() {
		return jssj;
	}
	public void setJssj(long jssj) {
		this.jssj = jssj;
	}
	public int getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}
	public double getKsjd() {
		return ksjd;
	}
	public void setKsjd(double ksjd) {
		this.ksjd = ksjd;
	}
	public double getKswd() {
		return kswd;
	}
	public void setKswd(double kswd) {
		this.kswd = kswd;
	}
	public double getJsjd() {
		return jsjd;
	}
	public void setJsjd(double jsjd) {
		this.jsjd = jsjd;
	}
	public double getJswd() {
		return jswd;
	}
	public void setJswd(double jswd) {
		this.jswd = jswd;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public static void main(String[] args) {
		List<AlarmDataBean> alarmDataBeanList = new ArrayList<AlarmDataBean>();
		for (int i=0;i<3;i++) {
			AlarmDataBean alarmDataBean = new AlarmDataBean();
			alarmDataBean.setAlarmType(1);
			alarmDataBean.setCldm("23423423");
			alarmDataBean.setColor("43543534");
			alarmDataBean.setJsjd(113086559);
			alarmDataBean.setJswd(28248300);
			alarmDataBean.setKsjd(113086559);
			alarmDataBean.setKswd(28248300);
			alarmDataBean.setKssj(1498168528);
			alarmDataBean.setJssj(1498168530);
			alarmDataBeanList.add(alarmDataBean);
		}
		System.out.println(JSONObject.toJSONString(alarmDataBeanList));
		
	}
}

