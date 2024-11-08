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

import com.aladdinworks9.domain.CapacityReport;
import com.aladdinworks9.dto.CapacityReportDTO;
import com.aladdinworks9.dto.CapacityReportSearchDTO;
import com.aladdinworks9.dto.CapacityReportPageDTO;
import com.aladdinworks9.service.CapacityReportService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/capacityReport")
@RestController
public class CapacityReportController {

	private final static Logger logger = LoggerFactory.getLogger(CapacityReportController.class);

	@Autowired
	CapacityReportService capacityReportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CapacityReport> getAll() {

		List<CapacityReport> capacityReports = capacityReportService.findAll();
		
		return capacityReports;	
	}

	@GetMapping(value = "/{capacityReportId}")
	@ResponseBody
	public CapacityReportDTO getCapacityReport(@PathVariable Integer capacityReportId) {
		
		return (capacityReportService.getCapacityReportDTOById(capacityReportId));
	}

 	@RequestMapping(value = "/addCapacityReport", method = RequestMethod.POST)
	public ResponseEntity<?> addCapacityReport(@RequestBody CapacityReportDTO capacityReportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = capacityReportService.addCapacityReport(capacityReportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/capacityReports")
	public ResponseEntity<CapacityReportPageDTO> getCapacityReports(CapacityReportSearchDTO capacityReportSearchDTO) {
 
		return capacityReportService.getCapacityReports(capacityReportSearchDTO);
	}	

	@RequestMapping(value = "/updateCapacityReport", method = RequestMethod.POST)
	public ResponseEntity<?> updateCapacityReport(@RequestBody CapacityReportDTO capacityReportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = capacityReportService.updateCapacityReport(capacityReportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
