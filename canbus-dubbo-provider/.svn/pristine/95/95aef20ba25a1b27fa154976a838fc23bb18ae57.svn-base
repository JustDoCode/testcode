package com.cpgps.canbus.repository.entity.can;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日 评分明细
 */
@Entity
@Table(name = "t_canbus_pfmx")
public class ScoreDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private int id;
	@Column(name = "c_jsydm")
	private String jsydm; // 驾驶员ID
	@Column(name = "c_jsyxm")
	private String jsyxm; // 驾驶员姓名
	@Column(name = "n_pfszid")
	private int pfszId;//评分设置ID
	@Column(name = "c_pfszmc")
	private String pfszmc;//评分设置名称
	@Column(name = "n_bcdh")
	private int bcdh;//班次代号
	@Column(name = "n_fz")
	private int fz;//分值
	@Column(name = "n_pfsj")
	private Date pfsj; // 创建时间
	@Column(name = "d_xgsj")
	private Date xgsj; // 修改时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJsydm() {
		return jsydm;
	}
	public void setJsydm(String jsydm) {
		this.jsydm = jsydm;
	}
	public int getPfszId() {
		return pfszId;
	}
	public void setPfszId(int pfszId) {
		this.pfszId = pfszId;
	}
	
	public int getBcdh() {
		return bcdh;
	}
	public void setBcdh(int bcdh) {
		this.bcdh = bcdh;
	}
	public int getFz() {
		return fz;
	}
	public void setFz(int fz) {
		this.fz = fz;
	}
	public Date getPfsj() {
		return pfsj;
	}
	public void setPfsj(Date pfsj) {
		this.pfsj = pfsj;
	}
	public Date getXgsj() {
		return xgsj;
	}
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	public String getJsyxm() {
		return jsyxm;
	}
	public void setJsyxm(String jsyxm) {
		this.jsyxm = jsyxm;
	}
	public String getPfszmc() {
		return pfszmc;
	}
	public void setPfszmc(String pfszmc) {
		this.pfszmc = pfszmc;
	}

	

}
