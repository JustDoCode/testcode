package com.cpgps.canbus.repository.entity.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 驾驶员信息表
 * 
 * @author wangshuai01@e6yun.com 2017年6月9日
 */
@Entity
@Table(name = "t_jc_jsyxx")
public class DriverEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_jsydm")
	private String jsydm;//驾驶员代码
	@Column(name = "c_jsybh")
	private String jsybh;// 驾驶员编号
	@Column(name = "c_xm")
	private String xm;// 姓名
	@Column(name = "c_sfzh")
	private String sfzh;// 身份证号
	@Column(name = "n_xb")
	private Integer xb;// 性别:0=男1=女
	@Column(name = "n_xl")
	private Integer xl;// 学历:0=小学1=初中2=高中3=大专4=本科5=研究生
	@Column(name = "c_jtzz")
	private String jtzz;// 家庭住址
	@Column(name = "c_dwdh")
	private String dwdh;// 单位电话
	@Column(name = "c_jtdh")
	private String jtdh;// 家庭电话
	@Column(name = "c_zjcx")
	private String zjcx;// 准驾车型
	@Column(name = "n_nsyf")
	private Integer nsyf;// 年审月份
	@Column(name = "c_dabh")
	private String dabh;// 档案编号
	@Column(name = "c_bassdq")
	private String bassdq;// 备案所属地区
	@Column(name = "c_sf")
	private String sf;// 身份(1-正式工，2-劳务工，3-集体工，4-合同工，5-临时工)
	@Column(name = "d_rjsj")
	private Date rjsj; // 局时间 yyyymmdd
	@Column(name = "c_dwdm")
	private String dwdm;// 工作单位代码
	@Column(name = "n_ywlx")
	private Integer ywlx;// 业务类型(1-邮政 2-速递 3-运钞车 4-委办单位)
	@Column(name = "c_ssdwdm")
	private String ssdwdm;// 所属单位代码(通过这个可以区分自办委办)
	@Column(name = "c_bz")
	private String bz;// 备注
	@Column(name = "d_csrq")
	private Date csrq; // 出生日期 yyyymmdd
	@Column(name = "d_lzrq")
	private Date lzrq; // 领证日期 yyyymmdd
	@Column(name = "d_jszyxqq")
	private Date jszyxqq;// 驾驶证有效起期 yyyymmdd
	@Column(name = "d_jszyxzq")
	private Date jszyxzq;// 驾驶证有效止期
	@Column(name = "c_jszh")
	private String jszh;// 驾驶证号
	@Column(name = "n_jsyzt")
	private Integer jsyzt;// 驾驶员状态:0=在岗1=休假2=病事假3=待岗4=离职5-删除
	@Column(name = "c_xgrid")
	private String xgrid;// 修改人ID
	@Column(name = "d_xgsj")
	private Date xgsj;// 修改驾驶员状态时间
	@Column(name = "n_ljlcs")
	private Integer ljlcs;// 累计里程数
	@Column(name = "d_rksj")
	private Date rksj;// 入库时间
	@Column(name = "d_gxsj")
	private Date gxsj;// 更新时间
	@Column(name = "n_sfhmd")
	private Integer sfhmd;// 是否黑名单:0=否1=是
	@Column(name = "n_gjdtbzt")
	private Integer gjdtbzt;// 给寄递同步状态:0=未同步1=已经同步
	@Column(name = "d_zhtbsj")
	private Date zhtbsj;// 最后同步时间
	@Column(name = "n_zwbbz")
	private Integer zwbbz;// 自委办标志:1=自办2=委办
	@Column(name = "n_sfyzc")
	private Integer sfyzc;// 是否已注册（0未注册 1已注册）
	@Column(name = "c_jsysjh")
	private String jsysjh;// 驾驶员手机号
	@Column(name = "c_appdlmm")
	private String appdlmm;// app登录密码
	@Column(name = "c_yzm")
	private String yzm;// 验证码（新增和修改时发送到手机端的验证码）
	@Column(name = "d_yzmsj")
	private Date yzmsj;// 验证码生成时间
	@Column(name = "d_zcsj")
	private Date zcsj;// 注册时间
	@Column(name = "n_shzt")
	private Integer shzt;// 审核状态(0-未审核 1-审核通过 2-审核不通过)
	@Column(name = "c_shrid")
	private String shrid;// 审核人ID
	@Column(name = "d_shsj")
	private Date shsj;// 审核时间
	@Column(name = "c_shbtgyy")
	private String shbtgyy;// 审核不通过原因
	@Column(name = "c_jszlj")
	private String jszlj;// 驾驶证路径
	public String getJsydm() {
		return jsydm;
	}
	public void setJsydm(String jsydm) {
		this.jsydm = jsydm;
	}
	public String getJsybh() {
		return jsybh;
	}
	public void setJsybh(String jsybh) {
		this.jsybh = jsybh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public Integer getXb() {
		return xb;
	}
	public void setXb(Integer xb) {
		this.xb = xb;
	}
	public Integer getXl() {
		return xl;
	}
	public void setXl(Integer xl) {
		this.xl = xl;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getDwdh() {
		return dwdh;
	}
	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	public String getJtdh() {
		return jtdh;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	public String getZjcx() {
		return zjcx;
	}
	public void setZjcx(String zjcx) {
		this.zjcx = zjcx;
	}
	public Integer getNsyf() {
		return nsyf;
	}
	public void setNsyf(Integer nsyf) {
		this.nsyf = nsyf;
	}
	public String getDabh() {
		return dabh;
	}
	public void setDabh(String dabh) {
		this.dabh = dabh;
	}
	public String getBassdq() {
		return bassdq;
	}
	public void setBassdq(String bassdq) {
		this.bassdq = bassdq;
	}
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}
	public Date getRjsj() {
		return rjsj;
	}
	public void setRjsj(Date rjsj) {
		this.rjsj = rjsj;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public Integer getYwlx() {
		return ywlx;
	}
	public void setYwlx(Integer ywlx) {
		this.ywlx = ywlx;
	}
	public String getSsdwdm() {
		return ssdwdm;
	}
	public void setSsdwdm(String ssdwdm) {
		this.ssdwdm = ssdwdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public Date getLzrq() {
		return lzrq;
	}
	public void setLzrq(Date lzrq) {
		this.lzrq = lzrq;
	}
	public Date getJszyxqq() {
		return jszyxqq;
	}
	public void setJszyxqq(Date jszyxqq) {
		this.jszyxqq = jszyxqq;
	}
	public Date getJszyxzq() {
		return jszyxzq;
	}
	public void setJszyxzq(Date jszyxzq) {
		this.jszyxzq = jszyxzq;
	}
	public String getJszh() {
		return jszh;
	}
	public void setJszh(String jszh) {
		this.jszh = jszh;
	}
	public Integer getJsyzt() {
		return jsyzt;
	}
	public void setJsyzt(Integer jsyzt) {
		this.jsyzt = jsyzt;
	}
	public String getXgrid() {
		return xgrid;
	}
	public void setXgrid(String xgrid) {
		this.xgrid = xgrid;
	}
	public Date getXgsj() {
		return xgsj;
	}
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	public Integer getLjlcs() {
		return ljlcs;
	}
	public void setLjlcs(Integer ljlcs) {
		this.ljlcs = ljlcs;
	}
	public Date getRksj() {
		return rksj;
	}
	public void setRksj(Date rksj) {
		this.rksj = rksj;
	}
	public Date getGxsj() {
		return gxsj;
	}
	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
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
	public Integer getZwbbz() {
		return zwbbz;
	}
	public void setZwbbz(Integer zwbbz) {
		this.zwbbz = zwbbz;
	}
	public Integer getSfyzc() {
		return sfyzc;
	}
	public void setSfyzc(Integer sfyzc) {
		this.sfyzc = sfyzc;
	}
	public String getJsysjh() {
		return jsysjh;
	}
	public void setJsysjh(String jsysjh) {
		this.jsysjh = jsysjh;
	}
	public String getAppdlmm() {
		return appdlmm;
	}
	public void setAppdlmm(String appdlmm) {
		this.appdlmm = appdlmm;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public Date getYzmsj() {
		return yzmsj;
	}
	public void setYzmsj(Date yzmsj) {
		this.yzmsj = yzmsj;
	}
	public Date getZcsj() {
		return zcsj;
	}
	public void setZcsj(Date zcsj) {
		this.zcsj = zcsj;
	}
	public Integer getShzt() {
		return shzt;
	}
	public void setShzt(Integer shzt) {
		this.shzt = shzt;
	}
	public String getShrid() {
		return shrid;
	}
	public void setShrid(String shrid) {
		this.shrid = shrid;
	}
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
	public String getShbtgyy() {
		return shbtgyy;
	}
	public void setShbtgyy(String shbtgyy) {
		this.shbtgyy = shbtgyy;
	}
	public String getJszlj() {
		return jszlj;
	}
	public void setJszlj(String jszlj) {
		this.jszlj = jszlj;
	}
	
}
