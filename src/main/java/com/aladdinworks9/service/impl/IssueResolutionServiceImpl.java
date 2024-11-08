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
import com.aladdinworks9.dao.IssueResolutionDAO;
import com.aladdinworks9.domain.IssueResolution;
import com.aladdinworks9.dto.IssueResolutionDTO;
import com.aladdinworks9.dto.IssueResolutionSearchDTO;
import com.aladdinworks9.dto.IssueResolutionPageDTO;
import com.aladdinworks9.dto.IssueResolutionConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.IssueResolutionService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class IssueResolutionServiceImpl extends GenericServiceImpl<IssueResolution, Integer> implements IssueResolutionService {

    private final static Logger logger = LoggerFactory.getLogger(IssueResolutionServiceImpl.class);

	@Autowired
	IssueResolutionDAO issueResolutionDao;

	


	@Override
	public GenericDAO<IssueResolution, Integer> getDAO() {
		return (GenericDAO<IssueResolution, Integer>) issueResolutionDao;
	}
	
	public List<IssueResolution> findAll () {
		List<IssueResolution> issueResolutions = issueResolutionDao.findAll();
		
		return issueResolutions;	
		
	}

	public ResultDTO addIssueResolution(IssueResolutionDTO issueResolutionDTO, RequestDTO requestDTO) {

		IssueResolution issueResolution = new IssueResolution();

		issueResolution.setIssueResolutionId(issueResolutionDTO.getIssueResolutionId());


		issueResolution.setResolutionDetails(issueResolutionDTO.getResolutionDetails());


		issueResolution.setResolutionDate(issueResolutionDTO.getResolutionDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		issueResolution = issueResolutionDao.save(issueResolution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<IssueResolution> getAllIssueResolutions(Pageable pageable) {
		return issueResolutionDao.findAll(pageable);
	}

	public Page<IssueResolution> getAllIssueResolutions(Specification<IssueResolution> spec, Pageable pageable) {
		return issueResolutionDao.findAll(spec, pageable);
	}

	public ResponseEntity<IssueResolutionPageDTO> getIssueResolutions(IssueResolutionSearchDTO issueResolutionSearchDTO) {
	
			Integer issueResolutionId = issueResolutionSearchDTO.getIssueResolutionId(); 
 			String resolutionDetails = issueResolutionSearchDTO.getResolutionDetails(); 
   			String sortBy = issueResolutionSearchDTO.getSortBy();
			String sortOrder = issueResolutionSearchDTO.getSortOrder();
			String searchQuery = issueResolutionSearchDTO.getSearchQuery();
			Integer page = issueResolutionSearchDTO.getPage();
			Integer size = issueResolutionSearchDTO.getSize();

	        Specification<IssueResolution> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, issueResolutionId, "issueResolutionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, resolutionDetails, "resolutionDetails"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("resolutionDetails")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<IssueResolution> issueResolutions = this.getAllIssueResolutions(spec, pageable);
		
		//System.out.println(String.valueOf(issueResolutions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(issueResolutions.getTotalPages()));
		
		List<IssueResolution> issueResolutionsList = issueResolutions.getContent();
		
		IssueResolutionConvertCriteriaDTO convertCriteria = new IssueResolutionConvertCriteriaDTO();
		List<IssueResolutionDTO> issueResolutionDTOs = this.convertIssueResolutionsToIssueResolutionDTOs(issueResolutionsList,convertCriteria);
		
		IssueResolutionPageDTO issueResolutionPageDTO = new IssueResolutionPageDTO();
		issueResolutionPageDTO.setIssueResolutions(issueResolutionDTOs);
		issueResolutionPageDTO.setTotalElements(issueResolutions.getTotalElements());
		return ResponseEntity.ok(issueResolutionPageDTO);
	}

	public List<IssueResolutionDTO> convertIssueResolutionsToIssueResolutionDTOs(List<IssueResolution> issueResolutions, IssueResolutionConvertCriteriaDTO convertCriteria) {
		
		List<IssueResolutionDTO> issueResolutionDTOs = new ArrayList<IssueResolutionDTO>();
		
		for (IssueResolution issueResolution : issueResolutions) {
			issueResolutionDTOs.add(convertIssueResolutionToIssueResolutionDTO(issueResolution,convertCriteria));
		}
		
		return issueResolutionDTOs;

	}
	
	public IssueResolutionDTO convertIssueResolutionToIssueResolutionDTO(IssueResolution issueResolution, IssueResolutionConvertCriteriaDTO convertCriteria) {
		
		IssueResolutionDTO issueResolutionDTO = new IssueResolutionDTO();
		
		issueResolutionDTO.setIssueResolutionId(issueResolution.getIssueResolutionId());

	
		issueResolutionDTO.setResolutionDetails(issueResolution.getResolutionDetails());

	
		issueResolutionDTO.setResolutionDate(issueResolution.getResolutionDate());

	

		
		return issueResolutionDTO;
	}

	public ResultDTO updateIssueResolution(IssueResolutionDTO issueResolutionDTO, RequestDTO requestDTO) {
		
		IssueResolution issueResolution = issueResolutionDao.getById(issueResolutionDTO.getIssueResolutionId());

		issueResolution.setIssueResolutionId(ControllerUtils.setValue(issueResolution.getIssueResolutionId(), issueResolutionDTO.getIssueResolutionId()));

		issueResolution.setResolutionDetails(ControllerUtils.setValue(issueResolution.getResolutionDetails(), issueResolutionDTO.getResolutionDetails()));

		issueResolution.setResolutionDate(ControllerUtils.setValue(issueResolution.getResolutionDate(), issueResolutionDTO.getResolutionDate()));



        issueResolution = issueResolutionDao.save(issueResolution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public IssueResolutionDTO getIssueResolutionDTOById(Integer issueResolutionId) {
	
		IssueResolution issueResolution = issueResolutionDao.getById(issueResolutionId);
			
		
		IssueResolutionConvertCriteriaDTO convertCriteria = new IssueResolutionConvertCriteriaDTO();
		return(this.convertIssueResolutionToIssueResolutionDTO(issueResolution,convertCriteria));
	}







}
