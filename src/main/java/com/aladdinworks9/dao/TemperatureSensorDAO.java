package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.TemperatureSensor;





public interface TemperatureSensorDAO extends GenericDAO<TemperatureSensor, Integer> {
  
	List<TemperatureSensor> findAll();
	






}


