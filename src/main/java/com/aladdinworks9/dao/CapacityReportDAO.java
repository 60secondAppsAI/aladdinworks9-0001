package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.CapacityReport;





public interface CapacityReportDAO extends GenericDAO<CapacityReport, Integer> {
  
	List<CapacityReport> findAll();
	






}


