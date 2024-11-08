package com.aladdinworks9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks9.domain.CapacityReport;
import com.aladdinworks9.dto.CapacityReportDTO;
import com.aladdinworks9.dto.CapacityReportSearchDTO;
import com.aladdinworks9.dto.CapacityReportPageDTO;
import com.aladdinworks9.dto.CapacityReportConvertCriteriaDTO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CapacityReportService extends GenericService<CapacityReport, Integer> {

	List<CapacityReport> findAll();

	ResultDTO addCapacityReport(CapacityReportDTO capacityReportDTO, RequestDTO requestDTO);

	ResultDTO updateCapacityReport(CapacityReportDTO capacityReportDTO, RequestDTO requestDTO);

    Page<CapacityReport> getAllCapacityReports(Pageable pageable);

    Page<CapacityReport> getAllCapacityReports(Specification<CapacityReport> spec, Pageable pageable);

	ResponseEntity<CapacityReportPageDTO> getCapacityReports(CapacityReportSearchDTO capacityReportSearchDTO);
	
	List<CapacityReportDTO> convertCapacityReportsToCapacityReportDTOs(List<CapacityReport> capacityReports, CapacityReportConvertCriteriaDTO convertCriteria);

	CapacityReportDTO getCapacityReportDTOById(Integer capacityReportId);







}





