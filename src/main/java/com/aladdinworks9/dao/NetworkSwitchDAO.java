package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.NetworkSwitch;





public interface NetworkSwitchDAO extends GenericDAO<NetworkSwitch, Integer> {
  
	List<NetworkSwitch> findAll();
	






}


