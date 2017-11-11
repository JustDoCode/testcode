package com.cpgps.canbus.repository.entity.can;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * canbus报警类型
 * @author wangshuai@e6yun.com
 */
@Entity
@Table(name = "t_canbus_clbjlx")
public class CanbusAlarmTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private long id; 
	@Column(name = "c_bjlxdm")
	private String bjlxdm; // 报警类型代码
	@Column(name = "c_bjlxmc")
	private String bjlxmc; // 报警类型名称
	@Column(name = "c_ms")
	private String ms; // 描述
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBjlxdm() {
		return bjlxdm;
	}
	public void setBjlxdm(String bjlxdm) {
		this.bjlxdm = bjlxdm;
	}
	public String getBjlxmc() {
		return bjlxmc;
	}
	public void setBjlxmc(String bjlxmc) {
		this.bjlxmc = bjlxmc;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	
	
}
