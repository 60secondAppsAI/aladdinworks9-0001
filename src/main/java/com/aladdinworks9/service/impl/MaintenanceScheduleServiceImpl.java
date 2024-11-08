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
import com.aladdinworks9.dao.MaintenanceScheduleDAO;
import com.aladdinworks9.domain.MaintenanceSchedule;
import com.aladdinworks9.dto.MaintenanceScheduleDTO;
import com.aladdinworks9.dto.MaintenanceScheduleSearchDTO;
import com.aladdinworks9.dto.MaintenanceSchedulePageDTO;
import com.aladdinworks9.dto.MaintenanceScheduleConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.MaintenanceScheduleService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class MaintenanceScheduleServiceImpl extends GenericServiceImpl<MaintenanceSchedule, Integer> implements MaintenanceScheduleService {

    private final static Logger logger = LoggerFactory.getLogger(MaintenanceScheduleServiceImpl.class);

	@Autowired
	MaintenanceScheduleDAO maintenanceScheduleDao;

	


	@Override
	public GenericDAO<MaintenanceSchedule, Integer> getDAO() {
		return (GenericDAO<MaintenanceSchedule, Integer>) maintenanceScheduleDao;
	}
	
	public List<MaintenanceSchedule> findAll () {
		List<MaintenanceSchedule> maintenanceSchedules = maintenanceScheduleDao.findAll();
		
		return maintenanceSchedules;	
		
	}

	public ResultDTO addMaintenanceSchedule(MaintenanceScheduleDTO maintenanceScheduleDTO, RequestDTO requestDTO) {

		MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();

		maintenanceSchedule.setMaintenanceScheduleId(maintenanceScheduleDTO.getMaintenanceScheduleId());


		maintenanceSchedule.setScheduledDate(maintenanceScheduleDTO.getScheduledDate());


		maintenanceSchedule.setTechnicianName(maintenanceScheduleDTO.getTechnicianName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		maintenanceSchedule = maintenanceScheduleDao.save(maintenanceSchedule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MaintenanceSchedule> getAllMaintenanceSchedules(Pageable pageable) {
		return maintenanceScheduleDao.findAll(pageable);
	}

	public Page<MaintenanceSchedule> getAllMaintenanceSchedules(Specification<MaintenanceSchedule> spec, Pageable pageable) {
		return maintenanceScheduleDao.findAll(spec, pageable);
	}

	public ResponseEntity<MaintenanceSchedulePageDTO> getMaintenanceSchedules(MaintenanceScheduleSearchDTO maintenanceScheduleSearchDTO) {
	
			Integer maintenanceScheduleId = maintenanceScheduleSearchDTO.getMaintenanceScheduleId(); 
   			String technicianName = maintenanceScheduleSearchDTO.getTechnicianName(); 
 			String sortBy = maintenanceScheduleSearchDTO.getSortBy();
			String sortOrder = maintenanceScheduleSearchDTO.getSortOrder();
			String searchQuery = maintenanceScheduleSearchDTO.getSearchQuery();
			Integer page = maintenanceScheduleSearchDTO.getPage();
			Integer size = maintenanceScheduleSearchDTO.getSize();

	        Specification<MaintenanceSchedule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, maintenanceScheduleId, "maintenanceScheduleId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, technicianName, "technicianName"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("technicianName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MaintenanceSchedule> maintenanceSchedules = this.getAllMaintenanceSchedules(spec, pageable);
		
		//System.out.println(String.valueOf(maintenanceSchedules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(maintenanceSchedules.getTotalPages()));
		
		List<MaintenanceSchedule> maintenanceSchedulesList = maintenanceSchedules.getContent();
		
		MaintenanceScheduleConvertCriteriaDTO convertCriteria = new MaintenanceScheduleConvertCriteriaDTO();
		List<MaintenanceScheduleDTO> maintenanceScheduleDTOs = this.convertMaintenanceSchedulesToMaintenanceScheduleDTOs(maintenanceSchedulesList,convertCriteria);
		
		MaintenanceSchedulePageDTO maintenanceSchedulePageDTO = new MaintenanceSchedulePageDTO();
		maintenanceSchedulePageDTO.setMaintenanceSchedules(maintenanceScheduleDTOs);
		maintenanceSchedulePageDTO.setTotalElements(maintenanceSchedules.getTotalElements());
		return ResponseEntity.ok(maintenanceSchedulePageDTO);
	}

	public List<MaintenanceScheduleDTO> convertMaintenanceSchedulesToMaintenanceScheduleDTOs(List<MaintenanceSchedule> maintenanceSchedules, MaintenanceScheduleConvertCriteriaDTO convertCriteria) {
		
		List<MaintenanceScheduleDTO> maintenanceScheduleDTOs = new ArrayList<MaintenanceScheduleDTO>();
		
		for (MaintenanceSchedule maintenanceSchedule : maintenanceSchedules) {
			maintenanceScheduleDTOs.add(convertMaintenanceScheduleToMaintenanceScheduleDTO(maintenanceSchedule,convertCriteria));
		}
		
		return maintenanceScheduleDTOs;

	}
	
	public MaintenanceScheduleDTO convertMaintenanceScheduleToMaintenanceScheduleDTO(MaintenanceSchedule maintenanceSchedule, MaintenanceScheduleConvertCriteriaDTO convertCriteria) {
		
		MaintenanceScheduleDTO maintenanceScheduleDTO = new MaintenanceScheduleDTO();
		
		maintenanceScheduleDTO.setMaintenanceScheduleId(maintenanceSchedule.getMaintenanceScheduleId());

	
		maintenanceScheduleDTO.setScheduledDate(maintenanceSchedule.getScheduledDate());

	
		maintenanceScheduleDTO.setTechnicianName(maintenanceSchedule.getTechnicianName());

	

		
		return maintenanceScheduleDTO;
	}

	public ResultDTO updateMaintenanceSchedule(MaintenanceScheduleDTO maintenanceScheduleDTO, RequestDTO requestDTO) {
		
		MaintenanceSchedule maintenanceSchedule = maintenanceScheduleDao.getById(maintenanceScheduleDTO.getMaintenanceScheduleId());

		maintenanceSchedule.setMaintenanceScheduleId(ControllerUtils.setValue(maintenanceSchedule.getMaintenanceScheduleId(), maintenanceScheduleDTO.getMaintenanceScheduleId()));

		maintenanceSchedule.setScheduledDate(ControllerUtils.setValue(maintenanceSchedule.getScheduledDate(), maintenanceScheduleDTO.getScheduledDate()));

		maintenanceSchedule.setTechnicianName(ControllerUtils.setValue(maintenanceSchedule.getTechnicianName(), maintenanceScheduleDTO.getTechnicianName()));



        maintenanceSchedule = maintenanceScheduleDao.save(maintenanceSchedule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MaintenanceScheduleDTO getMaintenanceScheduleDTOById(Integer maintenanceScheduleId) {
	
		MaintenanceSchedule maintenanceSchedule = maintenanceScheduleDao.getById(maintenanceScheduleId);
			
		
		MaintenanceScheduleConvertCriteriaDTO convertCriteria = new MaintenanceScheduleConvertCriteriaDTO();
		return(this.convertMaintenanceScheduleToMaintenanceScheduleDTO(maintenanceSchedule,convertCriteria));
	}







}
