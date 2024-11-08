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

import com.aladdinworks9.domain.MaintenanceSchedule;
import com.aladdinworks9.dto.MaintenanceScheduleDTO;
import com.aladdinworks9.dto.MaintenanceScheduleSearchDTO;
import com.aladdinworks9.dto.MaintenanceSchedulePageDTO;
import com.aladdinworks9.service.MaintenanceScheduleService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/maintenanceSchedule")
@RestController
public class MaintenanceScheduleController {

	private final static Logger logger = LoggerFactory.getLogger(MaintenanceScheduleController.class);

	@Autowired
	MaintenanceScheduleService maintenanceScheduleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MaintenanceSchedule> getAll() {

		List<MaintenanceSchedule> maintenanceSchedules = maintenanceScheduleService.findAll();
		
		return maintenanceSchedules;	
	}

	@GetMapping(value = "/{maintenanceScheduleId}")
	@ResponseBody
	public MaintenanceScheduleDTO getMaintenanceSchedule(@PathVariable Integer maintenanceScheduleId) {
		
		return (maintenanceScheduleService.getMaintenanceScheduleDTOById(maintenanceScheduleId));
	}

 	@RequestMapping(value = "/addMaintenanceSchedule", method = RequestMethod.POST)
	public ResponseEntity<?> addMaintenanceSchedule(@RequestBody MaintenanceScheduleDTO maintenanceScheduleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = maintenanceScheduleService.addMaintenanceSchedule(maintenanceScheduleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/maintenanceSchedules")
	public ResponseEntity<MaintenanceSchedulePageDTO> getMaintenanceSchedules(MaintenanceScheduleSearchDTO maintenanceScheduleSearchDTO) {
 
		return maintenanceScheduleService.getMaintenanceSchedules(maintenanceScheduleSearchDTO);
	}	

	@RequestMapping(value = "/updateMaintenanceSchedule", method = RequestMethod.POST)
	public ResponseEntity<?> updateMaintenanceSchedule(@RequestBody MaintenanceScheduleDTO maintenanceScheduleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = maintenanceScheduleService.updateMaintenanceSchedule(maintenanceScheduleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
