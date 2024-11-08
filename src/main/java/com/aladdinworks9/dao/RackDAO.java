package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.Rack;





public interface RackDAO extends GenericDAO<Rack, Integer> {
  
	List<Rack> findAll();
	






}


