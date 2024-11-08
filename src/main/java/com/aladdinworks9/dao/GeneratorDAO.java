package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.Generator;





public interface GeneratorDAO extends GenericDAO<Generator, Integer> {
  
	List<Generator> findAll();
	






}


