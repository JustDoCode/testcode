package com.cpgps.canbus.repository.entity.can;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日 评分类型
 */
@Entity
@Table(name = "t_canbus_pflx")
public class ScoreTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private int id;
	@Column(name = "c_lxmc")
	private String  lxmc; //类型名称
	@Column(name = "n_qz")
	private int qz; // 权重
	@Column(name = "c_ms")
	private String ms; // 描述
	@Column(name = "d_cjsj")
	private Date cjsj; // 创建时间
	@Column(name = "d_xgrq")
	private Date xgrq; // 修改日期
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public int getQz() {
		return qz;
	}
	public void setQz(int qz) {
		this.qz = qz;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public Date getXgrq() {
		return xgrq;
	}
	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}
	

	
}
