package com.cpgps.canbus.repository.entity.info;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cpgps.canbus.repository.entity.can.DriverTaskEntity;

public interface CanbusEquipRepository extends JpaRepository<CanbusEquipEntity, String> {
	@Query("select u from CanbusEquipEntity u where u.cldm is not null and u.cldm<>'' ")
	List<CanbusEquipEntity> getCanbusEquipEntityList();

}