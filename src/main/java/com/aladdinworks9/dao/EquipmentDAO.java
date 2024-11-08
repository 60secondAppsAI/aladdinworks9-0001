package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.Equipment;





public interface EquipmentDAO extends GenericDAO<Equipment, Integer> {
  
	List<Equipment> findAll();
	






}


