package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


