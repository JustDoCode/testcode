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
@Table(name = "t_canbus_clmrfx")
public class CarAnalysisEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	private long id; 
	@Column(name = "c_jsyid")
	private String jsyid; // 驾驶员编号
	@Column(name = "c_cldm")
	private String cldm; // 车辆代码
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
	private long ybzyh; //仪表总油耗
	@Column(name = "n_gpsyh")
	private long gpsyh; //gps油耗
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
	private long dshszb; // 怠速耗时占比
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
	private long jjscsm; // 急减速次数
	@Column(name = "n_jjscsp")
	private long jjscsp; // 急加速次数
	@Column(name = "n_jzwcs")
	private long jzwcs; // 急转弯次数
	@Column(name = "n_kdhxcs")
	private long kdhxcs; // 空档滑行次数
	@Column(name = "n_cjsj")
	private Date cjsj; // 创建时间
	@Column(name = "n_tjrq")
	private String tjrq; // 统计日期
	@Column(name = "n_lczdz")
	private long lczdz = 0; // 里程最大值
	@Column(name = "n_lczxz")
	private long lczxz = 0; // 里程最小值
	@Column(name = "n_zyhzxz")
	private long zyhzxz = 0; // 起始油耗(总油耗最小值)
	@Column(name = "n_zyhzdz")
	private long zyhzdz = 0; // 结束油耗(总油耗最大值)
	@Column(name = "n_gpszyhzxz")
	private long gpszyhzxz = 0; // gps起始油耗(总油耗最小值)
	@Column(name = "n_gpszyhzdz")
	private long gpszyhzdz = 0; // gps结束油耗(总油耗最大值)
	@Column(name = "n_gpslczdz")
	private long gpslczdz = 0; // gps里程最大值
	@Column(name = "n_gpslczxz")
	private long gpslczxz = 0; // gps里程最小值
	@Column(name = "n_ybzyhzxz")
	private long ybzyhzxz = 0; // 仪表起始油耗(总油耗最小值)
	@Column(name = "n_ybzyhzdz")
	private long ybzyhzdz = 0; // 仪表结束油耗(总油耗最大值)
	@Column(name = "n_yblczdz")
	private long yblczdz = 0; // 仪表里程最大值
	@Column(name = "n_yblczxz")
	private long yblczxz = 0; // 仪表里程最小值
	@Column(name = "n_yxsjzdz")
	private long yxsjzdz = 0; // 起始运行时间(运行时间最小值)
	@Column(name = "n_yxsjzxz")
	private long yxsjzxz = 0; // 结束运行时间(运行时间最大值)
	@Column(name = "n_kdhxsj")
	private long kdhxsj = 0; // 空档滑行时间

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

	public long getKdhxsj() {
		return kdhxsj;
	}

	public void setKdhxsj(long kdhxsj) {
		this.kdhxsj = kdhxsj;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJsyid() {
		return jsyid;
	}

	public void setJsyid(String jsyid) {
		this.jsyid = jsyid;
	}


	public String getCldm() {
		return cldm;
	}

	public void setCldm(String cldm) {
		this.cldm = cldm;
	}

	public long getZlc() {
		return zlc;
	}

	public void setZlc(long zlc) {
		this.zlc = zlc;
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

	public long getDshszb() {
		return dshszb;
	}

	public void setDshszb(long dshszb) {
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

	public long getYblc() {
		return yblc;
	}

	public void setYblc(long yblc) {
		this.yblc = yblc;
	}

	public long getYbzyh() {
		return ybzyh;
	}

	public void setYbzyh(long ybzyh) {
		this.ybzyh = ybzyh;
	}

	public long getFdjlc() {
		return fdjlc;
	}

	public void setFdjlc(long fdjlc) {
		this.fdjlc = fdjlc;
	}

	public long getGpslc() {
		return gpslc;
	}

	public void setGpslc(long gpslc) {
		this.gpslc = gpslc;
	}

	public long getGpsyh() {
		return gpsyh;
	}

	public void setGpsyh(long gpsyh) {
		this.gpsyh = gpsyh;
	}

	public long getGpszyhzxz() {
		return gpszyhzxz;
	}

	public void setGpszyhzxz(long gpszyhzxz) {
		this.gpszyhzxz = gpszyhzxz;
	}

	public long getGpszyhzdz() {
		return gpszyhzdz;
	}

	public void setGpszyhzdz(long gpszyhzdz) {
		this.gpszyhzdz = gpszyhzdz;
	}

	public long getGpslczdz() {
		return gpslczdz;
	}

	public void setGpslczdz(long gpslczdz) {
		this.gpslczdz = gpslczdz;
	}

	public long getGpslczxz() {
		return gpslczxz;
	}

	public void setGpslczxz(long gpslczxz) {
		this.gpslczxz = gpslczxz;
	}

	public long getYbzyhzxz() {
		return ybzyhzxz;
	}

	public void setYbzyhzxz(long ybzyhzxz) {
		this.ybzyhzxz = ybzyhzxz;
	}

	public long getYbzyhzdz() {
		return ybzyhzdz;
	}

	public void setYbzyhzdz(long ybzyhzdz) {
		this.ybzyhzdz = ybzyhzdz;
	}

	public long getYblczdz() {
		return yblczdz;
	}

	public void setYblczdz(long yblczdz) {
		this.yblczdz = yblczdz;
	}

	public long getYblczxz() {
		return yblczxz;
	}

	public void setYblczxz(long yblczxz) {
		this.yblczxz = yblczxz;
	}

}
