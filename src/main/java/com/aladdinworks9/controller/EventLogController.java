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

import com.aladdinworks9.domain.EventLog;
import com.aladdinworks9.dto.EventLogDTO;
import com.aladdinworks9.dto.EventLogSearchDTO;
import com.aladdinworks9.dto.EventLogPageDTO;
import com.aladdinworks9.service.EventLogService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/eventLog")
@RestController
public class EventLogController {

	private final static Logger logger = LoggerFactory.getLogger(EventLogController.class);

	@Autowired
	EventLogService eventLogService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<EventLog> getAll() {

		List<EventLog> eventLogs = eventLogService.findAll();
		
		return eventLogs;	
	}

	@GetMapping(value = "/{eventLogId}")
	@ResponseBody
	public EventLogDTO getEventLog(@PathVariable Integer eventLogId) {
		
		return (eventLogService.getEventLogDTOById(eventLogId));
	}

 	@RequestMapping(value = "/addEventLog", method = RequestMethod.POST)
	public ResponseEntity<?> addEventLog(@RequestBody EventLogDTO eventLogDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = eventLogService.addEventLog(eventLogDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/eventLogs")
	public ResponseEntity<EventLogPageDTO> getEventLogs(EventLogSearchDTO eventLogSearchDTO) {
 
		return eventLogService.getEventLogs(eventLogSearchDTO);
	}	

	@RequestMapping(value = "/updateEventLog", method = RequestMethod.POST)
	public ResponseEntity<?> updateEventLog(@RequestBody EventLogDTO eventLogDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = eventLogService.updateEventLog(eventLogDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
