package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.MonitoringPoint;





public interface MonitoringPointDAO extends GenericDAO<MonitoringPoint, Integer> {
  
	List<MonitoringPoint> findAll();
	






}


