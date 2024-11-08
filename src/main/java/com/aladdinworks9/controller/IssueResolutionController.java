package com.aladdinworks9.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks9.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks9.domain.IssueResolution;
import com.aladdinworks9.dto.IssueResolutionDTO;
import com.aladdinworks9.dto.IssueResolutionSearchDTO;
import com.aladdinworks9.dto.IssueResolutionPageDTO;
import com.aladdinworks9.service.IssueResolutionService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/issueResolution")
@RestController
public class IssueResolutionController {

	private final static Logger logger = LoggerFactory.getLogger(IssueResolutionController.class);

	@Autowired
	IssueResolutionService issueResolutionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<IssueResolution> getAll() {

		List<IssueResolution> issueResolutions = issueResolutionService.findAll();
		
		return issueResolutions;	
	}

	@GetMapping(value = "/{issueResolutionId}")
	@ResponseBody
	public IssueResolutionDTO getIssueResolution(@PathVariable Integer issueResolutionId) {
		
		return (issueResolutionService.getIssueResolutionDTOById(issueResolutionId));
	}

 	@RequestMapping(value = "/addIssueResolution", method = RequestMethod.POST)
	public ResponseEntity<?> addIssueResolution(@RequestBody IssueResolutionDTO issueResolutionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = issueResolutionService.addIssueResolution(issueResolutionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/issueResolutions")
	public ResponseEntity<IssueResolutionPageDTO> getIssueResolutions(IssueResolutionSearchDTO issueResolutionSearchDTO) {
 
		return issueResolutionService.getIssueResolutions(issueResolutionSearchDTO);
	}	

	@RequestMapping(value = "/updateIssueResolution", method = RequestMethod.POST)
	public ResponseEntity<?> updateIssueResolution(@RequestBody IssueResolutionDTO issueResolutionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = issueResolutionService.updateIssueResolution(issueResolutionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
