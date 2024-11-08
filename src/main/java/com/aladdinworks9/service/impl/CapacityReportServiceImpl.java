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
import com.aladdinworks9.dao.CapacityReportDAO;
import com.aladdinworks9.domain.CapacityReport;
import com.aladdinworks9.dto.CapacityReportDTO;
import com.aladdinworks9.dto.CapacityReportSearchDTO;
import com.aladdinworks9.dto.CapacityReportPageDTO;
import com.aladdinworks9.dto.CapacityReportConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.CapacityReportService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class CapacityReportServiceImpl extends GenericServiceImpl<CapacityReport, Integer> implements CapacityReportService {

    private final static Logger logger = LoggerFactory.getLogger(CapacityReportServiceImpl.class);

	@Autowired
	CapacityReportDAO capacityReportDao;

	


	@Override
	public GenericDAO<CapacityReport, Integer> getDAO() {
		return (GenericDAO<CapacityReport, Integer>) capacityReportDao;
	}
	
	public List<CapacityReport> findAll () {
		List<CapacityReport> capacityReports = capacityReportDao.findAll();
		
		return capacityReports;	
		
	}

	public ResultDTO addCapacityReport(CapacityReportDTO capacityReportDTO, RequestDTO requestDTO) {

		CapacityReport capacityReport = new CapacityReport();

		capacityReport.setCapacityReportId(capacityReportDTO.getCapacityReportId());


		capacityReport.setGeneratedDate(capacityReportDTO.getGeneratedDate());


		capacityReport.setTotalCapacityUsed(capacityReportDTO.getTotalCapacityUsed());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		capacityReport = capacityReportDao.save(capacityReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CapacityReport> getAllCapacityReports(Pageable pageable) {
		return capacityReportDao.findAll(pageable);
	}

	public Page<CapacityReport> getAllCapacityReports(Specification<CapacityReport> spec, Pageable pageable) {
		return capacityReportDao.findAll(spec, pageable);
	}

	public ResponseEntity<CapacityReportPageDTO> getCapacityReports(CapacityReportSearchDTO capacityReportSearchDTO) {
	
			Integer capacityReportId = capacityReportSearchDTO.getCapacityReportId(); 
    			String sortBy = capacityReportSearchDTO.getSortBy();
			String sortOrder = capacityReportSearchDTO.getSortOrder();
			String searchQuery = capacityReportSearchDTO.getSearchQuery();
			Integer page = capacityReportSearchDTO.getPage();
			Integer size = capacityReportSearchDTO.getSize();

	        Specification<CapacityReport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, capacityReportId, "capacityReportId"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<CapacityReport> capacityReports = this.getAllCapacityReports(spec, pageable);
		
		//System.out.println(String.valueOf(capacityReports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(capacityReports.getTotalPages()));
		
		List<CapacityReport> capacityReportsList = capacityReports.getContent();
		
		CapacityReportConvertCriteriaDTO convertCriteria = new CapacityReportConvertCriteriaDTO();
		List<CapacityReportDTO> capacityReportDTOs = this.convertCapacityReportsToCapacityReportDTOs(capacityReportsList,convertCriteria);
		
		CapacityReportPageDTO capacityReportPageDTO = new CapacityReportPageDTO();
		capacityReportPageDTO.setCapacityReports(capacityReportDTOs);
		capacityReportPageDTO.setTotalElements(capacityReports.getTotalElements());
		return ResponseEntity.ok(capacityReportPageDTO);
	}

	public List<CapacityReportDTO> convertCapacityReportsToCapacityReportDTOs(List<CapacityReport> capacityReports, CapacityReportConvertCriteriaDTO convertCriteria) {
		
		List<CapacityReportDTO> capacityReportDTOs = new ArrayList<CapacityReportDTO>();
		
		for (CapacityReport capacityReport : capacityReports) {
			capacityReportDTOs.add(convertCapacityReportToCapacityReportDTO(capacityReport,convertCriteria));
		}
		
		return capacityReportDTOs;

	}
	
	public CapacityReportDTO convertCapacityReportToCapacityReportDTO(CapacityReport capacityReport, CapacityReportConvertCriteriaDTO convertCriteria) {
		
		CapacityReportDTO capacityReportDTO = new CapacityReportDTO();
		
		capacityReportDTO.setCapacityReportId(capacityReport.getCapacityReportId());

	
		capacityReportDTO.setGeneratedDate(capacityReport.getGeneratedDate());

	
		capacityReportDTO.setTotalCapacityUsed(capacityReport.getTotalCapacityUsed());

	

		
		return capacityReportDTO;
	}

	public ResultDTO updateCapacityReport(CapacityReportDTO capacityReportDTO, RequestDTO requestDTO) {
		
		CapacityReport capacityReport = capacityReportDao.getById(capacityReportDTO.getCapacityReportId());

		capacityReport.setCapacityReportId(ControllerUtils.setValue(capacityReport.getCapacityReportId(), capacityReportDTO.getCapacityReportId()));

		capacityReport.setGeneratedDate(ControllerUtils.setValue(capacityReport.getGeneratedDate(), capacityReportDTO.getGeneratedDate()));

		capacityReport.setTotalCapacityUsed(ControllerUtils.setValue(capacityReport.getTotalCapacityUsed(), capacityReportDTO.getTotalCapacityUsed()));



        capacityReport = capacityReportDao.save(capacityReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CapacityReportDTO getCapacityReportDTOById(Integer capacityReportId) {
	
		CapacityReport capacityReport = capacityReportDao.getById(capacityReportId);
			
		
		CapacityReportConvertCriteriaDTO convertCriteria = new CapacityReportConvertCriteriaDTO();
		return(this.convertCapacityReportToCapacityReportDTO(capacityReport,convertCriteria));
	}







}
