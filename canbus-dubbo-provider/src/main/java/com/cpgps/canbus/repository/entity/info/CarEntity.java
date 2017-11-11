package com.cpgps.canbus.repository.entity.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_jc_clxx")
public class CarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_cldm")
	private String cldm; // 车辆代码
	@Column(name = "c_cph")
	private String cph; // '车牌号',
	@Column(name = "n_cpys")
	private Integer cpys; // '车牌颜色(1-黄牌 2-蓝牌) ',
	@Column(name = "n_clcx")
	private String clcx; // '车辆车型
	@Column(name = "c_clcxmc")
	private String clcxmc; // '车辆车型名称
	@Column(name = "c_ssdwdm")
	private String ssdwdm; // '配属单位代码 ',
	@Column(name = "c_sydwdm")
	private String sydwdm; // '使用单位代码 ',
	@Column(name = "c_zcbh")
	private String zcbh; // '资产编号 ',
	@Column(name = "c_fdjh")
	private String fdjh; // '发动机号 ',
	@Column(name = "c_fdjlx")
	private String fdjlx; // '发动类型 ',
	@Column(name = "c_dpbh")
	private String dpbh; // '底盘编号 ',
	@Column(name = "n_edzzl")
	private Integer edzzl; // '额定载重量 ',
	@Column(name = "n_dw")
	private Integer dw; // '吨位 ',
	@Column(name = "d_djrq")
	private Date djrq; // '登记日期 ',
	@Column(name = "c_clmc")
	private String clmc; // '车辆名称 ',
	@Column(name = "n_clppdm")
	private Integer clppdm; // '车辆品牌型号 ',
	@Column(name = "c_clxhmc")
	private String clxhmc; // '车辆型号名称 ',
	@Column(name = "n_clytdl")
	private Integer clytdl; // '车辆用途大类 ',
	@Column(name = "n_clytxl")
	private Integer clytxl; // '车辆用途细类 ',
	@Column(name = "c_csys")
	private String csys; // '车身颜色 ',
	@Column(name = "c_njyf")
	private String njyf; // '年检月份yyyydd ',
	@Column(name = "d_jdsj")
	private Date jdsj; // '建档时间 ',
	@Column(name = "n_dqgls")
	private Integer dqgls; // '当前公里数 ',
	@Column(name = "n_clzt")
	private Integer clzt; // '车辆状态:0=正常使用,1=已更新待报废,2=已更新已报废,3=未更新已报废,4=封存,5=维修中,6=调拨中,7=已调拨,8=委办车辆,9=已更新
	@Column(name = "n_bglwxf")
	private Integer bglwxf; // '百公里维修费 ',
	@Column(name = "n_yhzb")
	private Integer yhzb; // '油耗指标 ',
	@Column(name = "c_ycldh")
	private String ycldh; // '原车辆代码 ',
	@Column(name = "n_rj")
	private Integer rj; // '容积 ',
	@Column(name = "n_cxc")
	private Integer cxc; // '车厢长(mm) ',
	@Column(name = "n_cxk")
	private Integer cxk; // '车厢高(mm) ',
	@Column(name = "n_cxg")
	private Integer cxg; // '车厢高(mm) ',
	@Column(name = "c_syr")
	private String syr; // '所有人 ',
	@Column(name = "c_zz")
	private String zz; // '住址 ',
	@Column(name = "c_syxz")
	private String syxz; // '使用性质 ',
	@Column(name = "d_zcrq")
	private Date zcrq; // '注册日期（行驶证上面的注册日期） ',
	@Column(name = "d_fzrq")
	private Date fzrq; // '发证日期 ',
	@Column(name = "n_rylx")
	private Integer rylx; // '燃油类型：1-柴油，2-汽油 ',
	@Column(name = "n_czbz")
	private Integer czbz; // '操作标志:0=新增,1=修改,2=失效 ',
	@Column(name = "c_xszdabh")
	private String xszdabh; // '行驶证档案编号 ',
	@Column(name = "n_edzrs")
	private Integer edzrs; // '额定载人数 ',
	@Column(name = "n_zzl")
	private Integer zzl; // '总质量 ',
	@Column(name = "n_zbzl")
	private Integer zbzl; // '整备质量(千克) ',
	@Column(name = "n_zqyzzl")
	private Integer zqyzzl; // '总牵引总质量(千克) ',
	@Column(name = "n_cwkc")
	private Integer cwkc; // '车外廓长(毫米) ',
	@Column(name = "n_cwkk")
	private Integer cwkk; // '车外廓宽(毫米) ',
	@Column(name = "n_cwkg")
	private Integer cwkg; // '车外廓高(毫米) ',
	@Column(name = "c_xszbz")
	private String xszbz; // '行驶证备注 ',
	@Column(name = "n_zwbbz")
	private Integer zwbbz; // '自委办标志:1=自办,2=委办 ',
	@Column(name = "c_wbqydm")
	private String wbqydm; // '委办企业代码 ',
	@Column(name = "c_cjrdwdm")
	private String cjrdwdm; // '创建人单位代码 ',
	@Column(name = "d_cctjsj")
	private Date cctjsj; // '车辆添加时间 ',
	@Column(name = "d_ccxgsj")
	private Date ccxgsj; // '车辆修改时间 ',
	@Column(name = "n_lsycbz")
	private Integer lsycbz; // 'DEFAULT '0 ' '临时用车标志 0否 1是 ',
	@Column(name = "n_sfhmd")
	private Integer sfhmd; // '是否黑名单 仅针对委办:0=否,1=是 ',
	@Column(name = "n_gjdtbzt")
	private Integer gjdtbzt; // '给寄递同步状态:0=未同步,1=同步 ',
	@Column(name = "d_zhtbsj")
	private Date zhtbsj; // '最后同步时间 ',
	@Column(name = "n_sfcywyyw")
	private Integer sfcywyyw; // '是否承运网运业务(0-否 1-是) ',
	@Column(name = "n_sfcyyyyw")
	private Integer sfcyyyyw; // '是否承运营业业务(0-否 1-是) ',
	@Column(name = "n_sfcytdyw")
	private Integer sfcytdyw; // '是否承运投递业务(0-否 1-是) ',
	@Column(name = "n_yjshzt")
	private Integer yjshzt; // '一级审核状态(0-未审核 1-审核通过 2-审核不通过) ',
	@Column(name = "d_yjshsj")
	private Date yjshsj; // '一级审核时间 ',
	@Column(name = "c_yjshrid")
	private String yjshrid; // '一级审核审核人ID ',
	@Column(name = "n_ejshzt")
	private Integer ejshzt; // '二级审核状态(0-未审核 1-审核通过 2-审核不通过) ',
	@Column(name = "d_ejshsj")
	private Date ejshsj; // '二级审核时间 ',
	@Column(name = "c_ejshrid")
	private String ejshrid; // '二级审核审核人ID ',
	@Column(name = "c_shbtgyy")
	private String shbtgyy; // '审核不通过原因 ',
	@Column(name = "c_xszbh")
	private String xszbh; // '行驶证编号 ',
	@Column(name = "c_xszzylj")
	private String xszzylj; // '行驶证主页路径 ',
	@Column(name = "c_xszfylj")
	private String xszfylj; // '行驶证附页路径 ',
	@Column(name = "c_czyid")
	private String czyid; // '操作员ID ',
	@Column(name = "d_czsj")
	private Date czsj; // '操作时间 ',
	@Column(name = "c_cllr_sfdm")
	private String cllrsfdm; // '车辆录入省份代码 ',
	public String getCldm() {
		return cldm;
	}
	public void setCldm(String cldm) {
		this.cldm = cldm;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public Integer getCpys() {
		return cpys;
	}
	public void setCpys(Integer cpys) {
		this.cpys = cpys;
	}
	
	public String getClcxmc() {
		return clcxmc;
	}
	public void setClcxmc(String clcxmc) {
		this.clcxmc = clcxmc;
	}
	public String getSsdwdm() {
		return ssdwdm;
	}
	public void setSsdwdm(String ssdwdm) {
		this.ssdwdm = ssdwdm;
	}
	public String getSydwdm() {
		return sydwdm;
	}
	public void setSydwdm(String sydwdm) {
		this.sydwdm = sydwdm;
	}
	public String getZcbh() {
		return zcbh;
	}
	public void setZcbh(String zcbh) {
		this.zcbh = zcbh;
	}
	public String getFdjh() {
		return fdjh;
	}
	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}
	public String getDpbh() {
		return dpbh;
	}
	public void setDpbh(String dpbh) {
		this.dpbh = dpbh;
	}
	public Integer getEdzzl() {
		return edzzl;
	}
	public void setEdzzl(Integer edzzl) {
		this.edzzl = edzzl;
	}
	public Integer getDw() {
		return dw;
	}
	public void setDw(Integer dw) {
		this.dw = dw;
	}
	public Date getDjrq() {
		return djrq;
	}
	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}
	public String getClmc() {
		return clmc;
	}
	public void setClmc(String clmc) {
		this.clmc = clmc;
	}
	public Integer getClppdm() {
		return clppdm;
	}
	public void setClppdm(Integer clppdm) {
		this.clppdm = clppdm;
	}
	public String getClxhmc() {
		return clxhmc;
	}
	public void setClxhmc(String clxhmc) {
		this.clxhmc = clxhmc;
	}
	public Integer getClytdl() {
		return clytdl;
	}
	public void setClytdl(Integer clytdl) {
		this.clytdl = clytdl;
	}
	public Integer getClytxl() {
		return clytxl;
	}
	public void setClytxl(Integer clytxl) {
		this.clytxl = clytxl;
	}
	public String getCsys() {
		return csys;
	}
	public void setCsys(String csys) {
		this.csys = csys;
	}
	public String getNjyf() {
		return njyf;
	}
	public void setNjyf(String njyf) {
		this.njyf = njyf;
	}
	public Date getJdsj() {
		return jdsj;
	}
	public void setJdsj(Date jdsj) {
		this.jdsj = jdsj;
	}
	public Integer getDqgls() {
		return dqgls;
	}
	public void setDqgls(Integer dqgls) {
		this.dqgls = dqgls;
	}
	public Integer getClzt() {
		return clzt;
	}
	public void setClzt(Integer clzt) {
		this.clzt = clzt;
	}
	public Integer getBglwxf() {
		return bglwxf;
	}
	public void setBglwxf(Integer bglwxf) {
		this.bglwxf = bglwxf;
	}
	public Integer getYhzb() {
		return yhzb;
	}
	public void setYhzb(Integer yhzb) {
		this.yhzb = yhzb;
	}
	public String getYcldh() {
		return ycldh;
	}
	public void setYcldh(String ycldh) {
		this.ycldh = ycldh;
	}
	public Integer getRj() {
		return rj;
	}
	public void setRj(Integer rj) {
		this.rj = rj;
	}
	public Integer getCxc() {
		return cxc;
	}
	public void setCxc(Integer cxc) {
		this.cxc = cxc;
	}
	public Integer getCxk() {
		return cxk;
	}
	public void setCxk(Integer cxk) {
		this.cxk = cxk;
	}
	public Integer getCxg() {
		return cxg;
	}
	public void setCxg(Integer cxg) {
		this.cxg = cxg;
	}
	public String getSyr() {
		return syr;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = zz;
	}
	public String getSyxz() {
		return syxz;
	}
	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}
	public Date getZcrq() {
		return zcrq;
	}
	public void setZcrq(Date zcrq) {
		this.zcrq = zcrq;
	}
	public Date getFzrq() {
		return fzrq;
	}
	public void setFzrq(Date fzrq) {
		this.fzrq = fzrq;
	}
	public Integer getRylx() {
		return rylx;
	}
	public void setRylx(Integer rylx) {
		this.rylx = rylx;
	}
	public Integer getCzbz() {
		return czbz;
	}
	public void setCzbz(Integer czbz) {
		this.czbz = czbz;
	}
	public String getXszdabh() {
		return xszdabh;
	}
	public void setXszdabh(String xszdabh) {
		this.xszdabh = xszdabh;
	}
	public Integer getEdzrs() {
		return edzrs;
	}
	public void setEdzrs(Integer edzrs) {
		this.edzrs = edzrs;
	}
	public Integer getZzl() {
		return zzl;
	}
	public void setZzl(Integer zzl) {
		this.zzl = zzl;
	}
	public Integer getZbzl() {
		return zbzl;
	}
	public void setZbzl(Integer zbzl) {
		this.zbzl = zbzl;
	}
	public Integer getZqyzzl() {
		return zqyzzl;
	}
	public void setZqyzzl(Integer zqyzzl) {
		this.zqyzzl = zqyzzl;
	}
	public Integer getCwkc() {
		return cwkc;
	}
	public void setCwkc(Integer cwkc) {
		this.cwkc = cwkc;
	}
	public Integer getCwkk() {
		return cwkk;
	}
	public void setCwkk(Integer cwkk) {
		this.cwkk = cwkk;
	}
	public Integer getCwkg() {
		return cwkg;
	}
	public void setCwkg(Integer cwkg) {
		this.cwkg = cwkg;
	}
	public String getXszbz() {
		return xszbz;
	}
	public void setXszbz(String xszbz) {
		this.xszbz = xszbz;
	}
	public Integer getZwbbz() {
		return zwbbz;
	}
	public void setZwbbz(Integer zwbbz) {
		this.zwbbz = zwbbz;
	}
	public String getWbqydm() {
		return wbqydm;
	}
	public void setWbqydm(String wbqydm) {
		this.wbqydm = wbqydm;
	}
	public String getCjrdwdm() {
		return cjrdwdm;
	}
	public void setCjrdwdm(String cjrdwdm) {
		this.cjrdwdm = cjrdwdm;
	}
	public Date getCctjsj() {
		return cctjsj;
	}
	public void setCctjsj(Date cctjsj) {
		this.cctjsj = cctjsj;
	}
	public Date getCcxgsj() {
		return ccxgsj;
	}
	public void setCcxgsj(Date ccxgsj) {
		this.ccxgsj = ccxgsj;
	}
	public Integer getLsycbz() {
		return lsycbz;
	}
	public void setLsycbz(Integer lsycbz) {
		this.lsycbz = lsycbz;
	}
	public Integer getSfhmd() {
		return sfhmd;
	}
	public void setSfhmd(Integer sfhmd) {
		this.sfhmd = sfhmd;
	}
	public Integer getGjdtbzt() {
		return gjdtbzt;
	}
	public void setGjdtbzt(Integer gjdtbzt) {
		this.gjdtbzt = gjdtbzt;
	}
	public Date getZhtbsj() {
		return zhtbsj;
	}
	public void setZhtbsj(Date zhtbsj) {
		this.zhtbsj = zhtbsj;
	}
	public Integer getSfcywyyw() {
		return sfcywyyw;
	}
	public void setSfcywyyw(Integer sfcywyyw) {
		this.sfcywyyw = sfcywyyw;
	}
	public Integer getSfcyyyyw() {
		return sfcyyyyw;
	}
	public void setSfcyyyyw(Integer sfcyyyyw) {
		this.sfcyyyyw = sfcyyyyw;
	}
	public Integer getSfcytdyw() {
		return sfcytdyw;
	}
	public void setSfcytdyw(Integer sfcytdyw) {
		this.sfcytdyw = sfcytdyw;
	}
	public Integer getYjshzt() {
		return yjshzt;
	}
	public void setYjshzt(Integer yjshzt) {
		this.yjshzt = yjshzt;
	}
	public Date getYjshsj() {
		return yjshsj;
	}
	public void setYjshsj(Date yjshsj) {
		this.yjshsj = yjshsj;
	}
	public String getYjshrid() {
		return yjshrid;
	}
	public void setYjshrid(String yjshrid) {
		this.yjshrid = yjshrid;
	}
	public Integer getEjshzt() {
		return ejshzt;
	}
	public void setEjshzt(Integer ejshzt) {
		this.ejshzt = ejshzt;
	}
	public Date getEjshsj() {
		return ejshsj;
	}
	public void setEjshsj(Date ejshsj) {
		this.ejshsj = ejshsj;
	}
	public String getEjshrid() {
		return ejshrid;
	}
	public void setEjshrid(String ejshrid) {
		this.ejshrid = ejshrid;
	}
	public String getShbtgyy() {
		return shbtgyy;
	}
	public void setShbtgyy(String shbtgyy) {
		this.shbtgyy = shbtgyy;
	}
	public String getXszbh() {
		return xszbh;
	}
	public void setXszbh(String xszbh) {
		this.xszbh = xszbh;
	}
	public String getXszzylj() {
		return xszzylj;
	}
	public void setXszzylj(String xszzylj) {
		this.xszzylj = xszzylj;
	}
	public String getXszfylj() {
		return xszfylj;
	}
	public void setXszfylj(String xszfylj) {
		this.xszfylj = xszfylj;
	}
	public String getCzyid() {
		return czyid;
	}
	public void setCzyid(String czyid) {
		this.czyid = czyid;
	}
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	public String getCllrsfdm() {
		return cllrsfdm;
	}
	public void setCllrsfdm(String cllrsfdm) {
		this.cllrsfdm = cllrsfdm;
	}
	public String getClcx() {
		return clcx;
	}
	public void setClcx(String clcx) {
		this.clcx = clcx;
	}
	public String getFdjlx() {
		return fdjlx;
	}
	public void setFdjlx(String fdjlx) {
		this.fdjlx = fdjlx;
	}
	
}
