package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.EventLog;





public interface EventLogDAO extends GenericDAO<EventLog, Integer> {
  
	List<EventLog> findAll();
	






}


