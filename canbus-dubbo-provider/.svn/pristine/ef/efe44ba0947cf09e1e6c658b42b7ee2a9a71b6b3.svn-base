package com.cpgps.canbus.repository.entity.can;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
@Entity
@Table(name = "t_canbus_jsymrfx")
public class UserAnalysisEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private long id;
	@Column(name = "c_jsydm")
	private String jsydm;// 驾驶员ID
	@Column(name = "c_jsyxm")
	private String jsyxm;// 驾驶员姓名
	@Column(name = "c_cldm")
	private String cldm;// 车辆ID
	@Column(name = "c_bcdh")
	private int bcdh;// 任务编号 = 班次代号
	@Column(name = "d_tjrq")
	private Date tjrq; // 统计日期
	@Column(name = "n_zhpf")
	private int zhpf;// 综合评分
	@Column(name = "n_jjpf")
	private int jjpf;;// 经济评分
	@Column(name = "n_aqpf")
	private int aqpf;// 安全评分
	@Column(name = "n_fdjyxsj")
	private long fdjyxsj;// 发动机运行时间
	@Column(name = "n_xslc")
	private long xslc;// 行驶里程
	@Column(name = "n_xshs")
	private long xshs;// 行驶耗时
	@Column(name = "n_dshs")
	private long dshs;// 怠速耗时
	@Column(name = "n_dshszb")
	private double dshszb;// 怠速耗时占比
	@Column(name = "n_sccs")
	private long sccs;// 刹车次数
	@Column(name = "n_scjl")
	private long scjl;// 刹车距离
	@Column(name = "n_scjlzb")
	private double scjlzb;// 刹车距离占比
	@Column(name = "n_cszb")
	private double cszb;// 超速占比
	@Column(name = "n_jjscsp")
	private long jjscsp;// 急加速次数
	@Column(name = "n_jjscsm")
	private long jjscsm;// 急减速次数
	@Column(name = "n_kdhxzb")
	private double kdhxzb;// 空挡滑行占比
	@Column(name = "n_jjzszb")
	private double jjzszb;// 经济转速占比
	@Column(name = "n_jjzs")
	private long jjzs;// 经济转速
	@Column(name = "n_czszb")
	private double czszb;// 超转速占比
	@Column(name = "d_cjsj")
	private Date cjsj;// 创建时间
	@Column(name = "n_zdsd")
	protected long zdsd = 0;// 最大速度
	@Column(name = "n_zdzs")
	protected long zdzs = 0;// 最大转速
	@Column(name = "n_lczdz")
	protected long lczdz = 0;// 里程最大值
	@Column(name = "n_lczxz")
	protected long lczxz = 0;// 里程最小值
	@Column(name = "n_zyhzxz")
	protected long zyhzxz = 0;// 起始油耗(总油耗最小值)
	@Column(name = "n_zyhzdz")
	protected long zyhzdz = 0;// 结束油耗(总油耗最大值)
	@Column(name = "n_yxsjzdz")
	protected long yxsjzdz = 0;// 起始运行时间(运行时间最小值)
	@Column(name = "n_yxsjzxz")
	protected long yxsjzxz = 0;// 结束运行时间(运行时间最大值)
	@Column(name = "n_dsljsj")
	protected long dsljsj = 0;// 怠速累计时间
	@Column(name = "n_cscs")
	protected long cscs = 0;// 超速次数
	@Column(name = "n_csljsj")
	protected long csljsj = 0;// 超速累计时间
	@Column(name = "n_czscs")
	protected long czscs = 0;// 超转速次数
	@Column(name = "n_czsljsj")
	protected long czsljsj = 0;// 超转速累计时间
	@Column(name = "n_jzwcs")
	protected long jzwcs = 0;// 急转弯次数
	@Column(name = "n_kdhxcs")
	protected long kdhxcs = 0;// 空档滑行次数
	@Column(name = "n_kdhxsj")
	protected long kdhxsj = 0;// 空档滑行时间
	@Column(name = "n_dsxh")
	protected long dsxh = 0;// 怠速消耗
	@Column(name = "n_xsxh")
	protected long xsxh = 0;// 行驶消耗
	@Column(name = "n_hyl")
	protected long hyl = 0;// 耗油量
	@Column(name = "n_pjsd")
	protected double pjsd = 0;// 平均速度
	@Column(name = "n_wsclc")
	protected long wsclc = 0; // 未刹车里程
	@Column(name = "n_zlc")
	protected long zlc = 0; // 总里程
	@Column(name = "n_zyh")
	protected long zyh = 0;// 总油耗
	@Column(name = "n_xcsj")
	protected long xcsj = 0;// 行车时间
	@Column(name = "n_yzsj")
	protected long yzsj = 0;// 运转时间
	@Column(name = "n_xhsj")
	protected long xhsj = 0;// 熄火时间
	@Column(name = "n_bglyh")
	protected double bglyh = 0;// 百公里油耗

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getTjrq() {
		return tjrq;
	}

	public void setTjrq(Date tjrq) {
		this.tjrq = tjrq;
	}

	public int getZhpf() {
		return zhpf;
	}

	public void setZhpf(int zhpf) {
		this.zhpf = zhpf;
	}

	public int getJjpf() {
		return jjpf;
	}

	public void setJjpf(int jjpf) {
		this.jjpf = jjpf;
	}

	public int getAqpf() {
		return aqpf;
	}

	public void setAqpf(int aqpf) {
		this.aqpf = aqpf;
	}

	public long getFdjyxsj() {
		return fdjyxsj;
	}

	public void setFdjyxsj(long fdjyxsj) {
		this.fdjyxsj = fdjyxsj;
	}

	public long getXslc() {
		return xslc;
	}

	public void setXslc(long xslc) {
		this.xslc = xslc;
	}

	public long getXshs() {
		return xshs;
	}

	public void setXshs(long xshs) {
		this.xshs = xshs;
	}

	public long getDshs() {
		return dshs;
	}

	public void setDshs(long dshs) {
		this.dshs = dshs;
	}

	public double getDshszb() {
		return dshszb;
	}

	public void setDshszb(double dshszb) {
		this.dshszb = dshszb;
	}

	public long getSccs() {
		return sccs;
	}

	public void setSccs(long sccs) {
		this.sccs = sccs;
	}

	public long getScjl() {
		return scjl;
	}

	public void setScjl(long scjl) {
		this.scjl = scjl;
	}

	public double getScjlzb() {
		return scjlzb;
	}

	public void setScjlzb(double scjlzb) {
		this.scjlzb = scjlzb;
	}

	public double getCszb() {
		return cszb;
	}

	public void setCszb(double cszb) {
		this.cszb = cszb;
	}

	public long getJjscsp() {
		return jjscsp;
	}

	public void setJjscsp(long jjscsp) {
		this.jjscsp = jjscsp;
	}

	public long getJjscsm() {
		return jjscsm;
	}

	public void setJjscsm(long jjscsm) {
		this.jjscsm = jjscsm;
	}

	public double getKdhxzb() {
		return kdhxzb;
	}

	public void setKdhxzb(double kdhxzb) {
		this.kdhxzb = kdhxzb;
	}

	public double getJjzszb() {
		return jjzszb;
	}

	public void setJjzszb(double jjzszb) {
		this.jjzszb = jjzszb;
	}

	public double getCzszb() {
		return czszb;
	}

	public void setCzszb(double czszb) {
		this.czszb = czszb;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public long getZdsd() {
		return zdsd;
	}

	public void setZdsd(long zdsd) {
		this.zdsd = zdsd;
	}

	public long getZdzs() {
		return zdzs;
	}

	public void setZdzs(long zdzs) {
		this.zdzs = zdzs;
	}

	public long getLczdz() {
		return lczdz;
	}

	public void setLczdz(long lczdz) {
		this.lczdz = lczdz;
	}

	public long getLczxz() {
		return lczxz;
	}

	public void setLczxz(long lczxz) {
		this.lczxz = lczxz;
	}

	public long getZyhzxz() {
		return zyhzxz;
	}

	public void setZyhzxz(long zyhzxz) {
		this.zyhzxz = zyhzxz;
	}

	public long getZyhzdz() {
		return zyhzdz;
	}

	public void setZyhzdz(long zyhzdz) {
		this.zyhzdz = zyhzdz;
	}

	public long getYxsjzdz() {
		return yxsjzdz;
	}

	public void setYxsjzdz(long yxsjzdz) {
		this.yxsjzdz = yxsjzdz;
	}

	public long getYxsjzxz() {
		return yxsjzxz;
	}

	public void setYxsjzxz(long yxsjzxz) {
		this.yxsjzxz = yxsjzxz;
	}

	public long getDsljsj() {
		return dsljsj;
	}

	public void setDsljsj(long dsljsj) {
		this.dsljsj = dsljsj;
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

	public long getKdhxsj() {
		return kdhxsj;
	}

	public void setKdhxsj(long kdhxsj) {
		this.kdhxsj = kdhxsj;
	}

	public long getDsxh() {
		return dsxh;
	}

	public void setDsxh(long dsxh) {
		this.dsxh = dsxh;
	}

	public long getHyl() {
		return hyl;
	}

	public void setHyl(long hyl) {
		this.hyl = hyl;
	}

	public double getPjsd() {
		return pjsd;
	}

	public void setPjsd(double pjsd) {
		this.pjsd = pjsd;
	}

	public long getWsclc() {
		return wsclc;
	}

	public void setWsclc(long wsclc) {
		this.wsclc = wsclc;
	}

	public long getZlc() {
		return zlc;
	}

	public void setZlc(long zlc) {
		this.zlc = zlc;
	}

	public long getZyh() {
		return zyh;
	}

	public void setZyh(long zyh) {
		this.zyh = zyh;
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

	public double getBglyh() {
		return bglyh;
	}

	public void setBglyh(double bglyh) {
		this.bglyh = bglyh;
	}

	public long getXsxh() {
		return xsxh;
	}

	public void setXsxh(long xsxh) {
		this.xsxh = xsxh;
	}

	public long getYzsj() {
		return yzsj;
	}

	public void setYzsj(long yzsj) {
		this.yzsj = yzsj;
	}

	public long getJjzs() {
		return jjzs;
	}

	public void setJjzs(long jjzs) {
		this.jjzs = jjzs;
	}

	public String getJsyxm() {
		return jsyxm;
	}

	public void setJsyxm(String jsyxm) {
		this.jsyxm = jsyxm;
	}

}
