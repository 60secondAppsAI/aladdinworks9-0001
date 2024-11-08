package com.aladdinworks9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks9.domain.Issue;
import com.aladdinworks9.dto.IssueDTO;
import com.aladdinworks9.dto.IssueSearchDTO;
import com.aladdinworks9.dto.IssuePageDTO;
import com.aladdinworks9.dto.IssueConvertCriteriaDTO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IssueService extends GenericService<Issue, Integer> {

	List<Issue> findAll();

	ResultDTO addIssue(IssueDTO issueDTO, RequestDTO requestDTO);

	ResultDTO updateIssue(IssueDTO issueDTO, RequestDTO requestDTO);

    Page<Issue> getAllIssues(Pageable pageable);

    Page<Issue> getAllIssues(Specification<Issue> spec, Pageable pageable);

	ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO);
	
	List<IssueDTO> convertIssuesToIssueDTOs(List<Issue> issues, IssueConvertCriteriaDTO convertCriteria);

	IssueDTO getIssueDTOById(Integer issueId);







}





