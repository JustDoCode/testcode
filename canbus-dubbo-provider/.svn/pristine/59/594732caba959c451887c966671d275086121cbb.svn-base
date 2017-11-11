package com.cpgps.canbus.dubbo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cpgps.canbus.dubbo.config.CarUseCateConst;
import com.cpgps.canbus.dubbo.service.CarEmsPostRoadService;
import com.cpgps.canbus.dubbo.service.CarSectionAnalysisService;
import com.cpgps.canbus.repository.service.CarPostRoadService;
import com.cpgps.canbus.repository.entity.can.CarSectionAnalysisEntity;
import com.cpgps.canbus.repository.entity.info.CarEmsPostRoadEntity;
import com.cpgps.canbus.repository.entity.info.CarEmsPostRoadRepository;
import com.cpgps.canbus.repository.entity.info.CarEntity;
import com.cpgps.canbus.repository.entity.info.CarPostRoadEntity;
import com.cpgps.canbus.repository.entity.info.CarRepository;
/**
 * dubbo-车辆EMS-邮路封装实现
 * @author lihuan@e6yun.com 
 * Date 2017年7月12日
 */
@Service
public class CarEmsPostRoadServiceImpl implements CarEmsPostRoadService {

	static Logger logger = LoggerFactory.getLogger(CarEmsPostRoadServiceImpl.class);
	@Autowired
	private CarPostRoadService carPostRoadService;
	
	@Autowired
	private CarSectionAnalysisService carSectionAnalysisService;
	
	@Autowired
	private CarEmsPostRoadRepository carEmsPostRoadRepository;
	
	@Autowired
	private CarRepository carRepository;

	/**
	 * 封装车辆EMS和邮路信息
	 */
	@Override
	public List<CarEmsPostRoadEntity> listCarEmsPostRoadEntity(String bcdh) {
		Date startTime;
		Date endTime;
		CarPostRoadEntity carPostRoadEntity = carPostRoadService.findByBcdh(Integer.parseInt(bcdh));
		CarSectionAnalysisEntity carSectionAnalysisEntity = new CarSectionAnalysisEntity();
		if(carPostRoadEntity == null)
			return null;
		startTime = carPostRoadEntity.getSjfbsj();
		endTime = carPostRoadEntity.getSjsbsj();
		//根据车辆编号、开始时间和结束时间查询当前期间的车辆EMS数据
		carSectionAnalysisEntity = carSectionAnalysisService.getCarSectionAnalysisEntity(carPostRoadEntity.getCldm(), startTime, endTime);
		if(carSectionAnalysisEntity == null)
			return null;
		return getCarEmsPostRoadEntity(carSectionAnalysisEntity,carPostRoadEntity);
	}

	private List<CarEmsPostRoadEntity> getCarEmsPostRoadEntity(CarSectionAnalysisEntity carSectionAnalysisEntity,CarPostRoadEntity carPostRoadEntity){
		List<CarEmsPostRoadEntity> listCarEmsPostRoadEntity = new ArrayList<CarEmsPostRoadEntity>();
		CarEmsPostRoadEntity carEmsPostRoadEntity = new CarEmsPostRoadEntity();
		CarEntity carEntity = carRepository.findOne(carSectionAnalysisEntity.getCldm());
		String clytdl = null;
		//分析出的车辆EMS和邮路数据封装至车辆EMS邮路表--实例化
		carEmsPostRoadEntity.setYlmc(carPostRoadEntity.getYlmc());
		carEmsPostRoadEntity.setWcqk(carPostRoadEntity.getWcqk());
		carEmsPostRoadEntity.setRwzbzt(carPostRoadEntity.getRwzbzt());
		carEmsPostRoadEntity.setRwzdzt(carPostRoadEntity.getRwzdzt());
		carEmsPostRoadEntity.setJhfbsj(carPostRoadEntity.getJhfbsj());
		carEmsPostRoadEntity.setJhsbsj(carPostRoadEntity.getJhsbsj());
		carEmsPostRoadEntity.setYhyxsc(carPostRoadEntity.getYhyxsc());
		carEmsPostRoadEntity.setSjfbsj(carPostRoadEntity.getSjfbsj());
		carEmsPostRoadEntity.setSjsbsj(carPostRoadEntity.getSjsbsj());
		carEmsPostRoadEntity.setCph(carPostRoadEntity.getCph());
		carEmsPostRoadEntity.setDwdm(carSectionAnalysisEntity.getDwdm());
		carEmsPostRoadEntity.setDwmc(carSectionAnalysisEntity.getDwmc());
		carEmsPostRoadEntity.setClcx(carSectionAnalysisEntity.getClcx());
		carEmsPostRoadEntity.setClcxmc(carSectionAnalysisEntity.getClcxmc());
		carEmsPostRoadEntity.setCldm(carSectionAnalysisEntity.getCldm());
		carEmsPostRoadEntity.setXckssj(carSectionAnalysisEntity.getXckssj());
		carEmsPostRoadEntity.setXcjssj(carSectionAnalysisEntity.getXcjssj());
		carEmsPostRoadEntity.setXcs(carSectionAnalysisEntity.getXcs());
		carEmsPostRoadEntity.setCanbussbh(carSectionAnalysisEntity.getCanbussbh());
		carEmsPostRoadEntity.setFdjxh(carSectionAnalysisEntity.getFdjxh());
		carEmsPostRoadEntity.setZlc(carSectionAnalysisEntity.getZlc());
		carEmsPostRoadEntity.setFdjlc(carSectionAnalysisEntity.getFdjlc());
		carEmsPostRoadEntity.setYblc(carSectionAnalysisEntity.getYblc());
		carEmsPostRoadEntity.setGpslc(carSectionAnalysisEntity.getGpslc());
		carEmsPostRoadEntity.setYbzyh(carSectionAnalysisEntity.getYbzyh());
		carEmsPostRoadEntity.setXcsj(carSectionAnalysisEntity.getXcsj());
		carEmsPostRoadEntity.setXhsj(carSectionAnalysisEntity.getXhsj());
		carEmsPostRoadEntity.setYzsj(carSectionAnalysisEntity.getYzsj());
		carEmsPostRoadEntity.setXshs(carSectionAnalysisEntity.getXshs());
		carEmsPostRoadEntity.setDsljsj(carSectionAnalysisEntity.getDsljsj());
		carEmsPostRoadEntity.setXsxh(carSectionAnalysisEntity.getXsxh());
		carEmsPostRoadEntity.setZyh(carSectionAnalysisEntity.getZyh());
		carEmsPostRoadEntity.setBglyh(carSectionAnalysisEntity.getBglyh());
		carEmsPostRoadEntity.setDsxh(carSectionAnalysisEntity.getXcsj());
		carEmsPostRoadEntity.setSccs(carSectionAnalysisEntity.getSccs());
		carEmsPostRoadEntity.setWsclc(carSectionAnalysisEntity.getWsclc());
		carEmsPostRoadEntity.setScjl(carSectionAnalysisEntity.getScjl());
		carEmsPostRoadEntity.setPjsd(carSectionAnalysisEntity.getScjl());
		carEmsPostRoadEntity.setDshszb(carSectionAnalysisEntity.getDshszb());
		carEmsPostRoadEntity.setFdjyxsj(carSectionAnalysisEntity.getFdjyxsj());
		carEmsPostRoadEntity.setHyl(carSectionAnalysisEntity.getHyl());
		carEmsPostRoadEntity.setCscs(carSectionAnalysisEntity.getCscs());
		carEmsPostRoadEntity.setCsljsj(carSectionAnalysisEntity.getCsljsj());
		carEmsPostRoadEntity.setZdsd(carSectionAnalysisEntity.getZdsd());
		carEmsPostRoadEntity.setCzscs(carSectionAnalysisEntity.getCzscs());
		carEmsPostRoadEntity.setCzsljsj(carSectionAnalysisEntity.getCzsljsj());
		carEmsPostRoadEntity.setZdzs(carSectionAnalysisEntity.getZdzs());
		carEmsPostRoadEntity.setJjscsm(carSectionAnalysisEntity.getJjscsm());
		carEmsPostRoadEntity.setJjscsp(carSectionAnalysisEntity.getJjscsp());
		carEmsPostRoadEntity.setJzwcs(carSectionAnalysisEntity.getJzwcs());
		carEmsPostRoadEntity.setKdhxcs(carSectionAnalysisEntity.getKdhxcs());
		carEmsPostRoadEntity.setCjsj(carSectionAnalysisEntity.getCjsj());
		carEmsPostRoadEntity.setTjrq(carSectionAnalysisEntity.getTjrq());
		carEmsPostRoadEntity.setKdhxsj(carSectionAnalysisEntity.getKdhxsj());
		if(carEntity != null){
			carEmsPostRoadEntity.setDw(carEntity.getDw());
			carEmsPostRoadEntity.setFdjlx(carEntity.getFdjlx());
			carEmsPostRoadEntity.setClytdl(carEmsPostRoadEntity.getClytdl());
			clytdl = this.getClytdlName(String.valueOf(carEmsPostRoadEntity.getClytdl()));
			carEmsPostRoadEntity.setClytdlmc(clytdl);
		}else{
			carEmsPostRoadEntity.setDw(0);
			carEmsPostRoadEntity.setFdjlx("");
			carEmsPostRoadEntity.setClytdl(0);
			carEmsPostRoadEntity.setClytdlmc("");
		}
		listCarEmsPostRoadEntity.add(carEmsPostRoadEntity);
		if(listCarEmsPostRoadEntity.size()<1)
			return null;
		this.carEmsPostRoadRepository.save(listCarEmsPostRoadEntity);
		return listCarEmsPostRoadEntity;
	}
	
	/**
	 * 处理用途大类名称
	 */
	private String getClytdlName(String clytdl){
		String carUserCateName = null;
		switch (clytdl) {
		case CarUseCateConst.CAR_USE_CATEGORY_01:// 一级干线
			carUserCateName = "一级干线";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_02:// 二级干线
			carUserCateName = "二级干线";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_03:// 普邮收投
			carUserCateName = "普邮收投";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_04:// 物流收投
			carUserCateName = "物流收投";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_05://速递收投
			carUserCateName = "速递收投";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_06://支线邮路
			carUserCateName = "支线邮路";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_07://机要收投
			carUserCateName = "机要收投";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_08: //市内转趟
			carUserCateName = "市内转趟";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_09: //储汇运钞
			carUserCateName = "储汇运钞";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_10://	邮政检查
			carUserCateName = "邮政检查";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_11: //场内作业
			carUserCateName = "场内作业";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_12://其他辅助生产用车
			carUserCateName = "其他辅助生产用车";
			break;
		case CarUseCateConst.CAR_USE_CATEGORY_13: //非生产用车 
			carUserCateName = "非生产用车";
			break;
		}
		return carUserCateName;
	}
	
	public static void main(String[] args) {
		CarEmsPostRoadServiceImpl carEmsPostRoadServiceImpl = new CarEmsPostRoadServiceImpl();
		List<CarEmsPostRoadEntity> listCarEmsPostRoadEntity = carEmsPostRoadServiceImpl.listCarEmsPostRoadEntity("598583");
		logger.info(JSON.toJSONString(listCarEmsPostRoadEntity));
	}
}
