package com.aladdinworks9.dao;

import java.util.List;

import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.domain.SurveillanceCamera;





public interface SurveillanceCameraDAO extends GenericDAO<SurveillanceCamera, Integer> {
  
	List<SurveillanceCamera> findAll();
	






}


