package com.cpgps.canbus.repository.entity.can;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_sj_jsygzjl")
public class DriverTaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private Long id; //主键
	@Column(name = "c_jsydm")
	private String jsydm; // 驾驶员代码
	@Column(name = "c_cldm")
	private String cldm; // 车辆代码
	@Column(name = "n_bcdh")
	private int bcdh; // 班次代号
	@Column(name = "d_gzkssj")
	private Date gzkssj; // 工作开始时间
	@Column(name = "d_gzjssj")
	private Date gzjssj; // 工作结束时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJsydm() {
		return jsydm;
	}
	public void setJsydm(String jsydm) {
		this.jsydm = jsydm;
	}
	public String getCldm() {
		return cldm;
	}
	public void setCldm(String cldm) {
		this.cldm = cldm;
	}
	public int getBcdh() {
		return bcdh;
	}
	public void setBcdh(int bcdh) {
		this.bcdh = bcdh;
	}
	public Date getGzkssj() {
		return gzkssj;
	}
	public void setGzkssj(Date gzkssj) {
		this.gzkssj = gzkssj;
	}
	public Date getGzjssj() {
		return gzjssj;
	}
	public void setGzjssj(Date gzjssj) {
		this.gzjssj = gzjssj;
	}

}
