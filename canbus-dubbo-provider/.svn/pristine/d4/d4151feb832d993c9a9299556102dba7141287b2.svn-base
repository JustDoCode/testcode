package com.cpgps.canbus.repository.entity.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_sb_cansbxx")
public class CanbusEquipEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_sbxlh")
	private String equipNum;//设备号
	@Column(name = "c_cldm")
	private String  cldm;//车辆代码
	public String getEquipNum() {
		return equipNum;
	}
	public void setEquipNum(String equipNum) {
		this.equipNum = equipNum;
	}
	public String getCldm() {
		return cldm;
	}
	public void setCldm(String cldm) {
		this.cldm = cldm;
	}
	
}
