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
import com.aladdinworks9.dao.IssueDAO;
import com.aladdinworks9.domain.Issue;
import com.aladdinworks9.dto.IssueDTO;
import com.aladdinworks9.dto.IssueSearchDTO;
import com.aladdinworks9.dto.IssuePageDTO;
import com.aladdinworks9.dto.IssueConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.IssueService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class IssueServiceImpl extends GenericServiceImpl<Issue, Integer> implements IssueService {

    private final static Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);

	@Autowired
	IssueDAO issueDao;

	


	@Override
	public GenericDAO<Issue, Integer> getDAO() {
		return (GenericDAO<Issue, Integer>) issueDao;
	}
	
	public List<Issue> findAll () {
		List<Issue> issues = issueDao.findAll();
		
		return issues;	
		
	}

	public ResultDTO addIssue(IssueDTO issueDTO, RequestDTO requestDTO) {

		Issue issue = new Issue();

		issue.setIssueId(issueDTO.getIssueId());


		issue.setDescription(issueDTO.getDescription());


		issue.setReportedDate(issueDTO.getReportedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		issue = issueDao.save(issue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Issue> getAllIssues(Pageable pageable) {
		return issueDao.findAll(pageable);
	}

	public Page<Issue> getAllIssues(Specification<Issue> spec, Pageable pageable) {
		return issueDao.findAll(spec, pageable);
	}

	public ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO) {
	
			Integer issueId = issueSearchDTO.getIssueId(); 
 			String description = issueSearchDTO.getDescription(); 
   			String sortBy = issueSearchDTO.getSortBy();
			String sortOrder = issueSearchDTO.getSortOrder();
			String searchQuery = issueSearchDTO.getSearchQuery();
			Integer page = issueSearchDTO.getPage();
			Integer size = issueSearchDTO.getSize();

	        Specification<Issue> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, issueId, "issueId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Issue> issues = this.getAllIssues(spec, pageable);
		
		//System.out.println(String.valueOf(issues.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(issues.getTotalPages()));
		
		List<Issue> issuesList = issues.getContent();
		
		IssueConvertCriteriaDTO convertCriteria = new IssueConvertCriteriaDTO();
		List<IssueDTO> issueDTOs = this.convertIssuesToIssueDTOs(issuesList,convertCriteria);
		
		IssuePageDTO issuePageDTO = new IssuePageDTO();
		issuePageDTO.setIssues(issueDTOs);
		issuePageDTO.setTotalElements(issues.getTotalElements());
		return ResponseEntity.ok(issuePageDTO);
	}

	public List<IssueDTO> convertIssuesToIssueDTOs(List<Issue> issues, IssueConvertCriteriaDTO convertCriteria) {
		
		List<IssueDTO> issueDTOs = new ArrayList<IssueDTO>();
		
		for (Issue issue : issues) {
			issueDTOs.add(convertIssueToIssueDTO(issue,convertCriteria));
		}
		
		return issueDTOs;

	}
	
	public IssueDTO convertIssueToIssueDTO(Issue issue, IssueConvertCriteriaDTO convertCriteria) {
		
		IssueDTO issueDTO = new IssueDTO();
		
		issueDTO.setIssueId(issue.getIssueId());

	
		issueDTO.setDescription(issue.getDescription());

	
		issueDTO.setReportedDate(issue.getReportedDate());

	

		
		return issueDTO;
	}

	public ResultDTO updateIssue(IssueDTO issueDTO, RequestDTO requestDTO) {
		
		Issue issue = issueDao.getById(issueDTO.getIssueId());

		issue.setIssueId(ControllerUtils.setValue(issue.getIssueId(), issueDTO.getIssueId()));

		issue.setDescription(ControllerUtils.setValue(issue.getDescription(), issueDTO.getDescription()));

		issue.setReportedDate(ControllerUtils.setValue(issue.getReportedDate(), issueDTO.getReportedDate()));



        issue = issueDao.save(issue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public IssueDTO getIssueDTOById(Integer issueId) {
	
		Issue issue = issueDao.getById(issueId);
			
		
		IssueConvertCriteriaDTO convertCriteria = new IssueConvertCriteriaDTO();
		return(this.convertIssueToIssueDTO(issue,convertCriteria));
	}







}
