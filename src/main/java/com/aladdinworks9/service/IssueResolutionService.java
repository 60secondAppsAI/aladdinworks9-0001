package com.aladdinworks9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks9.domain.IssueResolution;
import com.aladdinworks9.dto.IssueResolutionDTO;
import com.aladdinworks9.dto.IssueResolutionSearchDTO;
import com.aladdinworks9.dto.IssueResolutionPageDTO;
import com.aladdinworks9.dto.IssueResolutionConvertCriteriaDTO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IssueResolutionService extends GenericService<IssueResolution, Integer> {

	List<IssueResolution> findAll();

	ResultDTO addIssueResolution(IssueResolutionDTO issueResolutionDTO, RequestDTO requestDTO);

	ResultDTO updateIssueResolution(IssueResolutionDTO issueResolutionDTO, RequestDTO requestDTO);

    Page<IssueResolution> getAllIssueResolutions(Pageable pageable);

    Page<IssueResolution> getAllIssueResolutions(Specification<IssueResolution> spec, Pageable pageable);

	ResponseEntity<IssueResolutionPageDTO> getIssueResolutions(IssueResolutionSearchDTO issueResolutionSearchDTO);
	
	List<IssueResolutionDTO> convertIssueResolutionsToIssueResolutionDTOs(List<IssueResolution> issueResolutions, IssueResolutionConvertCriteriaDTO convertCriteria);

	IssueResolutionDTO getIssueResolutionDTOById(Integer issueResolutionId);







}





