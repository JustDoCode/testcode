package com.cpgps.canbus.repository.entity.can;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日 评分设置项
 */
@Entity
@Table(name = "t_canbus_pfsz")
public class ScoreSettingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private int id;
	@Column(name = "c_pfmc")
	private String  pfmc; //评分名称
	@Column(name = "n_fz")
	private int fz; // 分值
	@Column(name = "n_lxbh")
	private int lxbh; // 类型编号
	@Column(name = "n_qyzt")
	private int qyzt; // 启用状态
	@Column(name = "c_ms")
	private String ms; // 描述
	@Column(name = "c_pfxmc") //评分项名称
	private String pfxmc;
	@Column(name = "d_cjsj")
	private Date cjsj; // 创建时间
	@Column(name = "d_xgsj")
	private Date xgsj; // 修改时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPfmc() {
		return pfmc;
	}
	public void setPfmc(String pfmc) {
		this.pfmc = pfmc;
	}
	public int getFz() {
		return fz;
	}
	public void setFz(int fz) {
		this.fz = fz;
	}
	public int getLxbh() {
		return lxbh;
	}
	public void setLxbh(int lxbh) {
		this.lxbh = lxbh;
	}
	public int getQyzt() {
		return qyzt;
	}
	public void setQyzt(int qyzt) {
		this.qyzt = qyzt;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	public String getPfxmc() {
		return pfxmc;
	}
	public void setPfxmc(String pfxmc) {
		this.pfxmc = pfxmc;
	}
	public Date getXgsj() {
		return xgsj;
	}
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public Date getXgrq() {
		return xgsj;
	}
	public void setXgrq(Date xgsj) {
		this.xgsj = xgsj;
	}
	

	
}
