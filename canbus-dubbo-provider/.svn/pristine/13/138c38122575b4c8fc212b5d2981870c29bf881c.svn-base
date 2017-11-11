package com.cpgps.canbus.repository.entity.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆EMS期间邮路实例
 * 
 * @author lihuan@e6yun.com 
 * 2017年7月12日
 *
 */
@Entity
@Table(name = "t_ems_carprxx")
public class CarEmsPostRoadEntity {
	// 车辆EMS信息
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_cph")
	private String cph; // 车牌号
	@Column(name = "c_dwdm")
	private String dwdm; // 单位代码
	@Column(name = "c_dwmc")
	private String dwmc; // 单位名称
	@Column(name = "c_clcx")
	private String clcx; // 车辆车型
	@Column(name = "c_clcxmc")
	private String clcxmc; // 车辆车型名称
	@Column(name = "c_cldm")
	private String cldm; // 车辆代码
	@Column(name = "c_xckssj")
	private String xckssj; // 行程开始时间
	@Column(name = "c_xcjssj")
	private String xcjssj; // 行程结束时间
	@Column(name = "c_xcs")
	private long xcs; // 行程数
	@Column(name = "c_canbussbh")
	private String canbussbh; // 设备号
	@Column(name = "c_fdjxh")
	private String fdjxh; // 发动机型号
	@Column(name = "n_zlc")
	private long zlc; // 总里程
	@Column(name = "n_fdjlc")
	private long fdjlc; // 发动机里程
	@Column(name = "n_yblc")
	private long yblc; // 仪表里程
	@Column(name = "n_gpslc")
	private long gpslc; // gps里程
	@Column(name = "n_ybzyh")
	private long ybzyh; // 仪表总油耗
	@Column(name = "n_gpsyh")
	private long gpsyh; // gps油耗
	@Column(name = "n_xcsj")
	private long xcsj; // 行车时间
	@Column(name = "n_xhsj")
	private long xhsj; // 熄火时间
	@Column(name = "n_yzsj")
	private long yzsj; // 运转时间
	@Column(name = "n_xshs")
	private long xshs; // 行驶耗时
	@Column(name = "n_dsljsj")
	private long dsljsj; // 怠速（怠速累计时间）
	@Column(name = "n_xsxh")
	private long xsxh; // 行驶消耗
	@Column(name = "n_zyh")
	private long zyh; // 总油耗
	@Column(name = "n_bglyh")
	private double bglyh; // 百公里油耗
	@Column(name = "n_dsxh")
	private long dsxh; // 怠速消耗
	@Column(name = "n_sccs")
	private long sccs; // 刹车次数
	@Column(name = "n_wsclc")
	private long wsclc; // 未刹车里程
	@Column(name = "n_scjl")
	private long scjl; // 刹车距离
	@Column(name = "n_pjsd")
	private double pjsd; // 平均速度
	@Column(name = "n_dshszb")
	private double dshszb; // 怠速耗时占比
	@Column(name = "n_fdjyxsj")
	private long fdjyxsj; // 发动机运行时间
	@Column(name = "n_hyl")
	private long hyl; // 耗油量
	@Column(name = "n_cscs")
	private long cscs; // 超速次数
	@Column(name = "n_csljsj")
	private long csljsj; // 超速累计时间
	@Column(name = "n_zdsd")
	private long zdsd; // 最大速度
	@Column(name = "n_czscs")
	private long czscs; // 超转速次数
	@Column(name = "n_czsljsj")
	private long czsljsj; // 超转速累计时间
	@Column(name = "n_zdzs")
	private long zdzs; // 最大转速
	@Column(name = "n_jjscsm")
	private long jjscsm = 0; // 急减速次数
	@Column(name = "n_jjscsp")
	private long jjscsp = 0; // 急加速次数
	@Column(name = "n_jzwcs")
	private long jzwcs = 0; // 急转弯次数
	@Column(name = "n_kdhxcs")
	private long kdhxcs = 0; // 空档滑行次数
	@Column(name = "n_cjsj")
	private Date cjsj; // 创建时间
	@Column(name = "n_tjrq")
	private String tjrq; // 统计日期
	@Column(name = "n_kdhxsj")
	private long kdhxsj = 0; // 空档滑行时间

	// 邮路相关显示字段信息
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

	// 新加字段 不入库
	private int clytdl;// 车辆用途大类
	private String clytdlmc;//车辆用途大类名称
	private String fdjlx;// 发动机型号
	private double dw = 0;// 吨位

	public CarEmsPostRoadEntity() {
	}

	public String getClytdlmc() {
		return clytdlmc;
	}

	public void setClytdlmc(String clytdlmc) {
		this.clytdlmc = clytdlmc;
	}

	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getClcx() {
		return clcx;
	}

	public void setClcx(String clcx) {
		this.clcx = clcx;
	}

	public String getClcxmc() {
		return clcxmc;
	}

	public void setClcxmc(String clcxmc) {
		this.clcxmc = clcxmc;
	}

	public String getCldm() {
		return cldm;
	}

	public void setCldm(String cldm) {
		this.cldm = cldm;
	}

	public String getXckssj() {
		return xckssj;
	}

	public void setXckssj(String xckssj) {
		this.xckssj = xckssj;
	}

	public String getXcjssj() {
		return xcjssj;
	}

	public void setXcjssj(String xcjssj) {
		this.xcjssj = xcjssj;
	}

	public long getXcs() {
		return xcs;
	}

	public void setXcs(long xcs) {
		this.xcs = xcs;
	}

	public String getCanbussbh() {
		return canbussbh;
	}

	public void setCanbussbh(String canbussbh) {
		this.canbussbh = canbussbh;
	}

	public String getFdjxh() {
		return fdjxh;
	}

	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}

	public long getZlc() {
		return zlc;
	}

	public void setZlc(long zlc) {
		this.zlc = zlc;
	}

	public long getFdjlc() {
		return fdjlc;
	}

	public void setFdjlc(long fdjlc) {
		this.fdjlc = fdjlc;
	}

	public long getYblc() {
		return yblc;
	}

	public void setYblc(long yblc) {
		this.yblc = yblc;
	}

	public long getGpslc() {
		return gpslc;
	}

	public void setGpslc(long gpslc) {
		this.gpslc = gpslc;
	}

	public long getYbzyh() {
		return ybzyh;
	}

	public void setYbzyh(long ybzyh) {
		this.ybzyh = ybzyh;
	}

	public long getGpsyh() {
		return gpsyh;
	}

	public void setGpsyh(long gpsyh) {
		this.gpsyh = gpsyh;
	}

	public long getXcsj() {
		return xcsj;
	}

	public void setXcsj(long xcsj) {
		this.xcsj = xcsj;
	}

	public long getXhsj() {
		return xhsj;
	}

	public void setXhsj(long xhsj) {
		this.xhsj = xhsj;
	}

	public long getYzsj() {
		return yzsj;
	}

	public void setYzsj(long yzsj) {
		this.yzsj = yzsj;
	}

	public long getXshs() {
		return xshs;
	}

	public void setXshs(long xshs) {
		this.xshs = xshs;
	}

	public long getDsljsj() {
		return dsljsj;
	}

	public void setDsljsj(long dsljsj) {
		this.dsljsj = dsljsj;
	}

	public long getXsxh() {
		return xsxh;
	}

	public void setXsxh(long xsxh) {
		this.xsxh = xsxh;
	}

	public long getZyh() {
		return zyh;
	}

	public void setZyh(long zyh) {
		this.zyh = zyh;
	}

	public double getBglyh() {
		return bglyh;
	}

	public void setBglyh(double bglyh) {
		this.bglyh = bglyh;
	}

	public long getDsxh() {
		return dsxh;
	}

	public void setDsxh(long dsxh) {
		this.dsxh = dsxh;
	}

	public long getSccs() {
		return sccs;
	}

	public void setSccs(long sccs) {
		this.sccs = sccs;
	}

	public long getWsclc() {
		return wsclc;
	}

	public void setWsclc(long wsclc) {
		this.wsclc = wsclc;
	}

	public long getScjl() {
		return scjl;
	}

	public void setScjl(long scjl) {
		this.scjl = scjl;
	}

	public double getPjsd() {
		return pjsd;
	}

	public void setPjsd(double pjsd) {
		this.pjsd = pjsd;
	}

	public double getDshszb() {
		return dshszb;
	}

	public void setDshszb(double dshszb) {
		this.dshszb = dshszb;
	}

	public long getFdjyxsj() {
		return fdjyxsj;
	}

	public void setFdjyxsj(long fdjyxsj) {
		this.fdjyxsj = fdjyxsj;
	}

	public long getHyl() {
		return hyl;
	}

	public void setHyl(long hyl) {
		this.hyl = hyl;
	}

	public long getCscs() {
		return cscs;
	}

	public void setCscs(long cscs) {
		this.cscs = cscs;
	}

	public long getCsljsj() {
		return csljsj;
	}

	public void setCsljsj(long csljsj) {
		this.csljsj = csljsj;
	}

	public long getZdsd() {
		return zdsd;
	}

	public void setZdsd(long zdsd) {
		this.zdsd = zdsd;
	}

	public long getCzscs() {
		return czscs;
	}

	public void setCzscs(long czscs) {
		this.czscs = czscs;
	}

	public long getCzsljsj() {
		return czsljsj;
	}

	public void setCzsljsj(long czsljsj) {
		this.czsljsj = czsljsj;
	}

	public long getZdzs() {
		return zdzs;
	}

	public void setZdzs(long zdzs) {
		this.zdzs = zdzs;
	}

	public long getJjscsm() {
		return jjscsm;
	}

	public void setJjscsm(long jjscsm) {
		this.jjscsm = jjscsm;
	}

	public long getJjscsp() {
		return jjscsp;
	}

	public void setJjscsp(long jjscsp) {
		this.jjscsp = jjscsp;
	}

	public long getJzwcs() {
		return jzwcs;
	}

	public void setJzwcs(long jzwcs) {
		this.jzwcs = jzwcs;
	}

	public long getKdhxcs() {
		return kdhxcs;
	}

	public void setKdhxcs(long kdhxcs) {
		this.kdhxcs = kdhxcs;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getTjrq() {
		return tjrq;
	}

	public void setTjrq(String tjrq) {
		this.tjrq = tjrq;
	}

	public long getKdhxsj() {
		return kdhxsj;
	}

	public void setKdhxsj(long kdhxsj) {
		this.kdhxsj = kdhxsj;
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

	public int getClytdl() {
		return clytdl;
	}

	public void setClytdl(int clytdl) {
		this.clytdl = clytdl;
	}

	public String getFdjlx() {
		return fdjlx;
	}

	public void setFdjlx(String fdjlx) {
		this.fdjlx = fdjlx;
	}

	public double getDw() {
		return dw;
	}

	public void setDw(double dw) {
		this.dw = dw;
	}

}
