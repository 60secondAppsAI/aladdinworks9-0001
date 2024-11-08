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

import com.aladdinworks9.domain.PowerStrip;
import com.aladdinworks9.dto.PowerStripDTO;
import com.aladdinworks9.dto.PowerStripSearchDTO;
import com.aladdinworks9.dto.PowerStripPageDTO;
import com.aladdinworks9.service.PowerStripService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/powerStrip")
@RestController
public class PowerStripController {

	private final static Logger logger = LoggerFactory.getLogger(PowerStripController.class);

	@Autowired
	PowerStripService powerStripService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PowerStrip> getAll() {

		List<PowerStrip> powerStrips = powerStripService.findAll();
		
		return powerStrips;	
	}

	@GetMapping(value = "/{powerStripId}")
	@ResponseBody
	public PowerStripDTO getPowerStrip(@PathVariable Integer powerStripId) {
		
		return (powerStripService.getPowerStripDTOById(powerStripId));
	}

 	@RequestMapping(value = "/addPowerStrip", method = RequestMethod.POST)
	public ResponseEntity<?> addPowerStrip(@RequestBody PowerStripDTO powerStripDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerStripService.addPowerStrip(powerStripDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/powerStrips")
	public ResponseEntity<PowerStripPageDTO> getPowerStrips(PowerStripSearchDTO powerStripSearchDTO) {
 
		return powerStripService.getPowerStrips(powerStripSearchDTO);
	}	

	@RequestMapping(value = "/updatePowerStrip", method = RequestMethod.POST)
	public ResponseEntity<?> updatePowerStrip(@RequestBody PowerStripDTO powerStripDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerStripService.updatePowerStrip(powerStripDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
