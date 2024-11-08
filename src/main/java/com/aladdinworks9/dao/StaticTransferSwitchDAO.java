package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.StaticTransferSwitch;





public interface StaticTransferSwitchDAO extends GenericDAO<StaticTransferSwitch, Integer> {
  
	List<StaticTransferSwitch> findAll();
	






}


