package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.DataCenter;





public interface DataCenterDAO extends GenericDAO<DataCenter, Integer> {
  
	List<DataCenter> findAll();
	






}


