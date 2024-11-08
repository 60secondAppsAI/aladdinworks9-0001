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
import com.aladdinworks9.dao.EnergyConsumptionDAO;
import com.aladdinworks9.domain.EnergyConsumption;
import com.aladdinworks9.dto.EnergyConsumptionDTO;
import com.aladdinworks9.dto.EnergyConsumptionSearchDTO;
import com.aladdinworks9.dto.EnergyConsumptionPageDTO;
import com.aladdinworks9.dto.EnergyConsumptionConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.EnergyConsumptionService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class EnergyConsumptionServiceImpl extends GenericServiceImpl<EnergyConsumption, Integer> implements EnergyConsumptionService {

    private final static Logger logger = LoggerFactory.getLogger(EnergyConsumptionServiceImpl.class);

	@Autowired
	EnergyConsumptionDAO energyConsumptionDao;

	


	@Override
	public GenericDAO<EnergyConsumption, Integer> getDAO() {
		return (GenericDAO<EnergyConsumption, Integer>) energyConsumptionDao;
	}
	
	public List<EnergyConsumption> findAll () {
		List<EnergyConsumption> energyConsumptions = energyConsumptionDao.findAll();
		
		return energyConsumptions;	
		
	}

	public ResultDTO addEnergyConsumption(EnergyConsumptionDTO energyConsumptionDTO, RequestDTO requestDTO) {

		EnergyConsumption energyConsumption = new EnergyConsumption();

		energyConsumption.setEnergyConsumptionId(energyConsumptionDTO.getEnergyConsumptionId());


		energyConsumption.setMonth(energyConsumptionDTO.getMonth());


		energyConsumption.setEnergyConsumed(energyConsumptionDTO.getEnergyConsumed());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		energyConsumption = energyConsumptionDao.save(energyConsumption);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<EnergyConsumption> getAllEnergyConsumptions(Pageable pageable) {
		return energyConsumptionDao.findAll(pageable);
	}

	public Page<EnergyConsumption> getAllEnergyConsumptions(Specification<EnergyConsumption> spec, Pageable pageable) {
		return energyConsumptionDao.findAll(spec, pageable);
	}

	public ResponseEntity<EnergyConsumptionPageDTO> getEnergyConsumptions(EnergyConsumptionSearchDTO energyConsumptionSearchDTO) {
	
			Integer energyConsumptionId = energyConsumptionSearchDTO.getEnergyConsumptionId(); 
 			String month = energyConsumptionSearchDTO.getMonth(); 
  			String sortBy = energyConsumptionSearchDTO.getSortBy();
			String sortOrder = energyConsumptionSearchDTO.getSortOrder();
			String searchQuery = energyConsumptionSearchDTO.getSearchQuery();
			Integer page = energyConsumptionSearchDTO.getPage();
			Integer size = energyConsumptionSearchDTO.getSize();

	        Specification<EnergyConsumption> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, energyConsumptionId, "energyConsumptionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, month, "month"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("month")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<EnergyConsumption> energyConsumptions = this.getAllEnergyConsumptions(spec, pageable);
		
		//System.out.println(String.valueOf(energyConsumptions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(energyConsumptions.getTotalPages()));
		
		List<EnergyConsumption> energyConsumptionsList = energyConsumptions.getContent();
		
		EnergyConsumptionConvertCriteriaDTO convertCriteria = new EnergyConsumptionConvertCriteriaDTO();
		List<EnergyConsumptionDTO> energyConsumptionDTOs = this.convertEnergyConsumptionsToEnergyConsumptionDTOs(energyConsumptionsList,convertCriteria);
		
		EnergyConsumptionPageDTO energyConsumptionPageDTO = new EnergyConsumptionPageDTO();
		energyConsumptionPageDTO.setEnergyConsumptions(energyConsumptionDTOs);
		energyConsumptionPageDTO.setTotalElements(energyConsumptions.getTotalElements());
		return ResponseEntity.ok(energyConsumptionPageDTO);
	}

	public List<EnergyConsumptionDTO> convertEnergyConsumptionsToEnergyConsumptionDTOs(List<EnergyConsumption> energyConsumptions, EnergyConsumptionConvertCriteriaDTO convertCriteria) {
		
		List<EnergyConsumptionDTO> energyConsumptionDTOs = new ArrayList<EnergyConsumptionDTO>();
		
		for (EnergyConsumption energyConsumption : energyConsumptions) {
			energyConsumptionDTOs.add(convertEnergyConsumptionToEnergyConsumptionDTO(energyConsumption,convertCriteria));
		}
		
		return energyConsumptionDTOs;

	}
	
	public EnergyConsumptionDTO convertEnergyConsumptionToEnergyConsumptionDTO(EnergyConsumption energyConsumption, EnergyConsumptionConvertCriteriaDTO convertCriteria) {
		
		EnergyConsumptionDTO energyConsumptionDTO = new EnergyConsumptionDTO();
		
		energyConsumptionDTO.setEnergyConsumptionId(energyConsumption.getEnergyConsumptionId());

	
		energyConsumptionDTO.setMonth(energyConsumption.getMonth());

	
		energyConsumptionDTO.setEnergyConsumed(energyConsumption.getEnergyConsumed());

	

		
		return energyConsumptionDTO;
	}

	public ResultDTO updateEnergyConsumption(EnergyConsumptionDTO energyConsumptionDTO, RequestDTO requestDTO) {
		
		EnergyConsumption energyConsumption = energyConsumptionDao.getById(energyConsumptionDTO.getEnergyConsumptionId());

		energyConsumption.setEnergyConsumptionId(ControllerUtils.setValue(energyConsumption.getEnergyConsumptionId(), energyConsumptionDTO.getEnergyConsumptionId()));

		energyConsumption.setMonth(ControllerUtils.setValue(energyConsumption.getMonth(), energyConsumptionDTO.getMonth()));

		energyConsumption.setEnergyConsumed(ControllerUtils.setValue(energyConsumption.getEnergyConsumed(), energyConsumptionDTO.getEnergyConsumed()));



        energyConsumption = energyConsumptionDao.save(energyConsumption);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EnergyConsumptionDTO getEnergyConsumptionDTOById(Integer energyConsumptionId) {
	
		EnergyConsumption energyConsumption = energyConsumptionDao.getById(energyConsumptionId);
			
		
		EnergyConsumptionConvertCriteriaDTO convertCriteria = new EnergyConsumptionConvertCriteriaDTO();
		return(this.convertEnergyConsumptionToEnergyConsumptionDTO(energyConsumption,convertCriteria));
	}







}
