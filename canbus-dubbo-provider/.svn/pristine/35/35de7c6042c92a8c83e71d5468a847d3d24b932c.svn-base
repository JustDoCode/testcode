package com.cpgps.canbus.repository.entity.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆邮路信息
 * @author lihuan@e6yun.com 
 * 2017年7月12日
 *
 */
@Entity
@Table(name = "t_rw_pbjh")
public class CarPostRoadEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "n_bcdh")
	private Integer bcdh;
	@Column(name = "c_cph")
	private String cph; // 车牌号
	@Column(name = "c_cldm")
	private String cldm; // 车辆代码
	@Column(name = "c_ylmc")
	private String ylmc;// 派班计划表-邮路名称
	@Column(name = "n_wcqk")
	private String wcqk;// 派班计划表-完成情况 (0 未开始 1正在执行 2已完成 3 // 未完成(超时),超过计划完成时间太久(配置时间))(adc判断出入围栏)
	@Column(name = "n_rwzbzt")
	private String rwzbzt;// 派班计划表-准发 // 任务准班状态(车辆准时发班状态,实际发班时间<=邮路_计划发班时间则为准班)(0-未知 1-准班 // 2-晚班)
	@Column(name = "n_rwzdzt")
	private String rwzdzt;// 派班计划表-准点 // 任务准班状态(车辆准时发班状态,实际发班时间<=邮路_计划发班时间则为准班)(0-未知 1-准班 // 2-晚班)
	@Column(name = "d_yl_jhfbsj")
	private Date jhfbsj;// 派班计划表-计划发车时间
	@Column(name = "d_yl_jhsbsj")
	private Date jhsbsj;// 派班计划表-计划到达时间
	@Column(name = "d_yl_yhyxsc")
	private long yhyxsc;// 计划运行时长
	@Column(name = "d_sjfbsj")
	private Date sjfbsj;// 实际发班时间（出围栏时间）
	@Column(name = "d_sjsbsj")
	private Date sjsbsj;// 实际收班时间（入围栏时间）
	
	
	public CarPostRoadEntity(){}

	public Integer getBcdh() {
		return bcdh;
	}

	public void setBcdh(Integer bcdh) {
		this.bcdh = bcdh;
	}

	public String getYlmc() {
		return ylmc;
	}

	public void setYlmc(String ylmc) {
		this.ylmc = ylmc;
	}

	public String getWcqk() {
		return wcqk;
	}

	public void setWcqk(String wcqk) {
		this.wcqk = wcqk;
	}

	public String getRwzbzt() {
		return rwzbzt;
	}

	public void setRwzbzt(String rwzbzt) {
		this.rwzbzt = rwzbzt;
	}

	public String getRwzdzt() {
		return rwzdzt;
	}

	public void setRwzdzt(String rwzdzt) {
		this.rwzdzt = rwzdzt;
	}

	public Date getJhfbsj() {
		return jhfbsj;
	}

	public void setJhfbsj(Date jhfbsj) {
		this.jhfbsj = jhfbsj;
	}

	public Date getJhsbsj() {
		return jhsbsj;
	}

	public void setJhsbsj(Date jhsbsj) {
		this.jhsbsj = jhsbsj;
	}

	public long getYhyxsc() {
		return yhyxsc;
	}

	public void setYhyxsc(long yhyxsc) {
		this.yhyxsc = yhyxsc;
	}

	public Date getSjfbsj() {
		return sjfbsj;
	}

	public void setSjfbsj(Date sjfbsj) {
		this.sjfbsj = sjfbsj;
	}

	public Date getSjsbsj() {
		return sjsbsj;
	}

	public void setSjsbsj(Date sjsbsj) {
		this.sjsbsj = sjsbsj;
	}

	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	public String getCldm() {
		return cldm;
	}

	public void setCldm(String cldm) {
		this.cldm = cldm;
	}
	
}
