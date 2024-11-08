package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.PowerStrip;





public interface PowerStripDAO extends GenericDAO<PowerStrip, Integer> {
  
	List<PowerStrip> findAll();
	






}


