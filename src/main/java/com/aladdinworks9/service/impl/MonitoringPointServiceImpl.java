package com.aladdinworks9.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks9.dao.GenericDAO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.service.impl.GenericServiceImpl;
import com.aladdinworks9.dao.MonitoringPointDAO;
import com.aladdinworks9.domain.MonitoringPoint;
import com.aladdinworks9.dto.MonitoringPointDTO;
import com.aladdinworks9.dto.MonitoringPointSearchDTO;
import com.aladdinworks9.dto.MonitoringPointPageDTO;
import com.aladdinworks9.dto.MonitoringPointConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.MonitoringPointService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class MonitoringPointServiceImpl extends GenericServiceImpl<MonitoringPoint, Integer> implements MonitoringPointService {

    private final static Logger logger = LoggerFactory.getLogger(MonitoringPointServiceImpl.class);

	@Autowired
	MonitoringPointDAO monitoringPointDao;

	


	@Override
	public GenericDAO<MonitoringPoint, Integer> getDAO() {
		return (GenericDAO<MonitoringPoint, Integer>) monitoringPointDao;
	}
	
	public List<MonitoringPoint> findAll () {
		List<MonitoringPoint> monitoringPoints = monitoringPointDao.findAll();
		
		return monitoringPoints;	
		
	}

	public ResultDTO addMonitoringPoint(MonitoringPointDTO monitoringPointDTO, RequestDTO requestDTO) {

		MonitoringPoint monitoringPoint = new MonitoringPoint();

		monitoringPoint.setMonitoringPointId(monitoringPointDTO.getMonitoringPointId());


		monitoringPoint.setParameterType(monitoringPointDTO.getParameterType());


		monitoringPoint.setCurrentValue(monitoringPointDTO.getCurrentValue());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		monitoringPoint = monitoringPointDao.save(monitoringPoint);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MonitoringPoint> getAllMonitoringPoints(Pageable pageable) {
		return monitoringPointDao.findAll(pageable);
	}

	public Page<MonitoringPoint> getAllMonitoringPoints(Specification<MonitoringPoint> spec, Pageable pageable) {
		return monitoringPointDao.findAll(spec, pageable);
	}

	public ResponseEntity<MonitoringPointPageDTO> getMonitoringPoints(MonitoringPointSearchDTO monitoringPointSearchDTO) {
	
			Integer monitoringPointId = monitoringPointSearchDTO.getMonitoringPointId(); 
 			String parameterType = monitoringPointSearchDTO.getParameterType(); 
  			String sortBy = monitoringPointSearchDTO.getSortBy();
			String sortOrder = monitoringPointSearchDTO.getSortOrder();
			String searchQuery = monitoringPointSearchDTO.getSearchQuery();
			Integer page = monitoringPointSearchDTO.getPage();
			Integer size = monitoringPointSearchDTO.getSize();

	        Specification<MonitoringPoint> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, monitoringPointId, "monitoringPointId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, parameterType, "parameterType"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("parameterType")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<MonitoringPoint> monitoringPoints = this.getAllMonitoringPoints(spec, pageable);
		
		//System.out.println(String.valueOf(monitoringPoints.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(monitoringPoints.getTotalPages()));
		
		List<MonitoringPoint> monitoringPointsList = monitoringPoints.getContent();
		
		MonitoringPointConvertCriteriaDTO convertCriteria = new MonitoringPointConvertCriteriaDTO();
		List<MonitoringPointDTO> monitoringPointDTOs = this.convertMonitoringPointsToMonitoringPointDTOs(monitoringPointsList,convertCriteria);
		
		MonitoringPointPageDTO monitoringPointPageDTO = new MonitoringPointPageDTO();
		monitoringPointPageDTO.setMonitoringPoints(monitoringPointDTOs);
		monitoringPointPageDTO.setTotalElements(monitoringPoints.getTotalElements());
		return ResponseEntity.ok(monitoringPointPageDTO);
	}

	public List<MonitoringPointDTO> convertMonitoringPointsToMonitoringPointDTOs(List<MonitoringPoint> monitoringPoints, MonitoringPointConvertCriteriaDTO convertCriteria) {
		
		List<MonitoringPointDTO> monitoringPointDTOs = new ArrayList<MonitoringPointDTO>();
		
		for (MonitoringPoint monitoringPoint : monitoringPoints) {
			monitoringPointDTOs.add(convertMonitoringPointToMonitoringPointDTO(monitoringPoint,convertCriteria));
		}
		
		return monitoringPointDTOs;

	}
	
	public MonitoringPointDTO convertMonitoringPointToMonitoringPointDTO(MonitoringPoint monitoringPoint, MonitoringPointConvertCriteriaDTO convertCriteria) {
		
		MonitoringPointDTO monitoringPointDTO = new MonitoringPointDTO();
		
		monitoringPointDTO.setMonitoringPointId(monitoringPoint.getMonitoringPointId());

	
		monitoringPointDTO.setParameterType(monitoringPoint.getParameterType());

	
		monitoringPointDTO.setCurrentValue(monitoringPoint.getCurrentValue());

	

		
		return monitoringPointDTO;
	}

	public ResultDTO updateMonitoringPoint(MonitoringPointDTO monitoringPointDTO, RequestDTO requestDTO) {
		
		MonitoringPoint monitoringPoint = monitoringPointDao.getById(monitoringPointDTO.getMonitoringPointId());

		monitoringPoint.setMonitoringPointId(ControllerUtils.setValue(monitoringPoint.getMonitoringPointId(), monitoringPointDTO.getMonitoringPointId()));

		monitoringPoint.setParameterType(ControllerUtils.setValue(monitoringPoint.getParameterType(), monitoringPointDTO.getParameterType()));

		monitoringPoint.setCurrentValue(ControllerUtils.setValue(monitoringPoint.getCurrentValue(), monitoringPointDTO.getCurrentValue()));



        monitoringPoint = monitoringPointDao.save(monitoringPoint);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MonitoringPointDTO getMonitoringPointDTOById(Integer monitoringPointId) {
	
		MonitoringPoint monitoringPoint = monitoringPointDao.getById(monitoringPointId);
			
		
		MonitoringPointConvertCriteriaDTO convertCriteria = new MonitoringPointConvertCriteriaDTO();
		return(this.convertMonitoringPointToMonitoringPointDTO(monitoringPoint,convertCriteria));
	}







}
