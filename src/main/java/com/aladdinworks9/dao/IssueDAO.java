package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.Issue;





public interface IssueDAO extends GenericDAO<Issue, Integer> {
  
	List<Issue> findAll();
	






}


