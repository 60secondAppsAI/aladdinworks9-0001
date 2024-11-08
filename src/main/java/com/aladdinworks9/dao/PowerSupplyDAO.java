package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.PowerSupply;





public interface PowerSupplyDAO extends GenericDAO<PowerSupply, Integer> {
  
	List<PowerSupply> findAll();
	






}


