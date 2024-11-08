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
import com.aladdinworks9.dao.StaticTransferSwitchDAO;
import com.aladdinworks9.domain.StaticTransferSwitch;
import com.aladdinworks9.dto.StaticTransferSwitchDTO;
import com.aladdinworks9.dto.StaticTransferSwitchSearchDTO;
import com.aladdinworks9.dto.StaticTransferSwitchPageDTO;
import com.aladdinworks9.dto.StaticTransferSwitchConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.StaticTransferSwitchService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class StaticTransferSwitchServiceImpl extends GenericServiceImpl<StaticTransferSwitch, Integer> implements StaticTransferSwitchService {

    private final static Logger logger = LoggerFactory.getLogger(StaticTransferSwitchServiceImpl.class);

	@Autowired
	StaticTransferSwitchDAO staticTransferSwitchDao;

	


	@Override
	public GenericDAO<StaticTransferSwitch, Integer> getDAO() {
		return (GenericDAO<StaticTransferSwitch, Integer>) staticTransferSwitchDao;
	}
	
	public List<StaticTransferSwitch> findAll () {
		List<StaticTransferSwitch> staticTransferSwitchs = staticTransferSwitchDao.findAll();
		
		return staticTransferSwitchs;	
		
	}

	public ResultDTO addStaticTransferSwitch(StaticTransferSwitchDTO staticTransferSwitchDTO, RequestDTO requestDTO) {

		StaticTransferSwitch staticTransferSwitch = new StaticTransferSwitch();

		staticTransferSwitch.setStaticTransferSwitchId(staticTransferSwitchDTO.getStaticTransferSwitchId());


		staticTransferSwitch.setInputCurrent(staticTransferSwitchDTO.getInputCurrent());


		staticTransferSwitch.setOutputCurrent(staticTransferSwitchDTO.getOutputCurrent());


		staticTransferSwitch.setStatus(staticTransferSwitchDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		staticTransferSwitch = staticTransferSwitchDao.save(staticTransferSwitch);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<StaticTransferSwitch> getAllStaticTransferSwitchs(Pageable pageable) {
		return staticTransferSwitchDao.findAll(pageable);
	}

	public Page<StaticTransferSwitch> getAllStaticTransferSwitchs(Specification<StaticTransferSwitch> spec, Pageable pageable) {
		return staticTransferSwitchDao.findAll(spec, pageable);
	}

	public ResponseEntity<StaticTransferSwitchPageDTO> getStaticTransferSwitchs(StaticTransferSwitchSearchDTO staticTransferSwitchSearchDTO) {
	
			Integer staticTransferSwitchId = staticTransferSwitchSearchDTO.getStaticTransferSwitchId(); 
   			String status = staticTransferSwitchSearchDTO.getStatus(); 
 			String sortBy = staticTransferSwitchSearchDTO.getSortBy();
			String sortOrder = staticTransferSwitchSearchDTO.getSortOrder();
			String searchQuery = staticTransferSwitchSearchDTO.getSearchQuery();
			Integer page = staticTransferSwitchSearchDTO.getPage();
			Integer size = staticTransferSwitchSearchDTO.getSize();

	        Specification<StaticTransferSwitch> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, staticTransferSwitchId, "staticTransferSwitchId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<StaticTransferSwitch> staticTransferSwitchs = this.getAllStaticTransferSwitchs(spec, pageable);
		
		//System.out.println(String.valueOf(staticTransferSwitchs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(staticTransferSwitchs.getTotalPages()));
		
		List<StaticTransferSwitch> staticTransferSwitchsList = staticTransferSwitchs.getContent();
		
		StaticTransferSwitchConvertCriteriaDTO convertCriteria = new StaticTransferSwitchConvertCriteriaDTO();
		List<StaticTransferSwitchDTO> staticTransferSwitchDTOs = this.convertStaticTransferSwitchsToStaticTransferSwitchDTOs(staticTransferSwitchsList,convertCriteria);
		
		StaticTransferSwitchPageDTO staticTransferSwitchPageDTO = new StaticTransferSwitchPageDTO();
		staticTransferSwitchPageDTO.setStaticTransferSwitchs(staticTransferSwitchDTOs);
		staticTransferSwitchPageDTO.setTotalElements(staticTransferSwitchs.getTotalElements());
		return ResponseEntity.ok(staticTransferSwitchPageDTO);
	}

	public List<StaticTransferSwitchDTO> convertStaticTransferSwitchsToStaticTransferSwitchDTOs(List<StaticTransferSwitch> staticTransferSwitchs, StaticTransferSwitchConvertCriteriaDTO convertCriteria) {
		
		List<StaticTransferSwitchDTO> staticTransferSwitchDTOs = new ArrayList<StaticTransferSwitchDTO>();
		
		for (StaticTransferSwitch staticTransferSwitch : staticTransferSwitchs) {
			staticTransferSwitchDTOs.add(convertStaticTransferSwitchToStaticTransferSwitchDTO(staticTransferSwitch,convertCriteria));
		}
		
		return staticTransferSwitchDTOs;

	}
	
	public StaticTransferSwitchDTO convertStaticTransferSwitchToStaticTransferSwitchDTO(StaticTransferSwitch staticTransferSwitch, StaticTransferSwitchConvertCriteriaDTO convertCriteria) {
		
		StaticTransferSwitchDTO staticTransferSwitchDTO = new StaticTransferSwitchDTO();
		
		staticTransferSwitchDTO.setStaticTransferSwitchId(staticTransferSwitch.getStaticTransferSwitchId());

	
		staticTransferSwitchDTO.setInputCurrent(staticTransferSwitch.getInputCurrent());

	
		staticTransferSwitchDTO.setOutputCurrent(staticTransferSwitch.getOutputCurrent());

	
		staticTransferSwitchDTO.setStatus(staticTransferSwitch.getStatus());

	

		
		return staticTransferSwitchDTO;
	}

	public ResultDTO updateStaticTransferSwitch(StaticTransferSwitchDTO staticTransferSwitchDTO, RequestDTO requestDTO) {
		
		StaticTransferSwitch staticTransferSwitch = staticTransferSwitchDao.getById(staticTransferSwitchDTO.getStaticTransferSwitchId());

		staticTransferSwitch.setStaticTransferSwitchId(ControllerUtils.setValue(staticTransferSwitch.getStaticTransferSwitchId(), staticTransferSwitchDTO.getStaticTransferSwitchId()));

		staticTransferSwitch.setInputCurrent(ControllerUtils.setValue(staticTransferSwitch.getInputCurrent(), staticTransferSwitchDTO.getInputCurrent()));

		staticTransferSwitch.setOutputCurrent(ControllerUtils.setValue(staticTransferSwitch.getOutputCurrent(), staticTransferSwitchDTO.getOutputCurrent()));

		staticTransferSwitch.setStatus(ControllerUtils.setValue(staticTransferSwitch.getStatus(), staticTransferSwitchDTO.getStatus()));



        staticTransferSwitch = staticTransferSwitchDao.save(staticTransferSwitch);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public StaticTransferSwitchDTO getStaticTransferSwitchDTOById(Integer staticTransferSwitchId) {
	
		StaticTransferSwitch staticTransferSwitch = staticTransferSwitchDao.getById(staticTransferSwitchId);
			
		
		StaticTransferSwitchConvertCriteriaDTO convertCriteria = new StaticTransferSwitchConvertCriteriaDTO();
		return(this.convertStaticTransferSwitchToStaticTransferSwitchDTO(staticTransferSwitch,convertCriteria));
	}







}
