package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.IssueResolution;





public interface IssueResolutionDAO extends GenericDAO<IssueResolution, Integer> {
  
	List<IssueResolution> findAll();
	






}


