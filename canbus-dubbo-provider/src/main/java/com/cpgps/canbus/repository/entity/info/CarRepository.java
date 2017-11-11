package com.cpgps.canbus.repository.entity.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<CarEntity, String> {
	CarEntity findByCph(String cph);

}