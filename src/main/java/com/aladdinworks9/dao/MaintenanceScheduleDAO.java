package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.MaintenanceSchedule;





public interface MaintenanceScheduleDAO extends GenericDAO<MaintenanceSchedule, Integer> {
  
	List<MaintenanceSchedule> findAll();
	






}


