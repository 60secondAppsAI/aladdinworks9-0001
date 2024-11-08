package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.EnergyConsumption;





public interface EnergyConsumptionDAO extends GenericDAO<EnergyConsumption, Integer> {
  
	List<EnergyConsumption> findAll();
	






}


