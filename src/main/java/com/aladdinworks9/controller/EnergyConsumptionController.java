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

import com.aladdinworks9.domain.EnergyConsumption;
import com.aladdinworks9.dto.EnergyConsumptionDTO;
import com.aladdinworks9.dto.EnergyConsumptionSearchDTO;
import com.aladdinworks9.dto.EnergyConsumptionPageDTO;
import com.aladdinworks9.service.EnergyConsumptionService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/energyConsumption")
@RestController
public class EnergyConsumptionController {

	private final static Logger logger = LoggerFactory.getLogger(EnergyConsumptionController.class);

	@Autowired
	EnergyConsumptionService energyConsumptionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<EnergyConsumption> getAll() {

		List<EnergyConsumption> energyConsumptions = energyConsumptionService.findAll();
		
		return energyConsumptions;	
	}

	@GetMapping(value = "/{energyConsumptionId}")
	@ResponseBody
	public EnergyConsumptionDTO getEnergyConsumption(@PathVariable Integer energyConsumptionId) {
		
		return (energyConsumptionService.getEnergyConsumptionDTOById(energyConsumptionId));
	}

 	@RequestMapping(value = "/addEnergyConsumption", method = RequestMethod.POST)
	public ResponseEntity<?> addEnergyConsumption(@RequestBody EnergyConsumptionDTO energyConsumptionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = energyConsumptionService.addEnergyConsumption(energyConsumptionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/energyConsumptions")
	public ResponseEntity<EnergyConsumptionPageDTO> getEnergyConsumptions(EnergyConsumptionSearchDTO energyConsumptionSearchDTO) {
 
		return energyConsumptionService.getEnergyConsumptions(energyConsumptionSearchDTO);
	}	

	@RequestMapping(value = "/updateEnergyConsumption", method = RequestMethod.POST)
	public ResponseEntity<?> updateEnergyConsumption(@RequestBody EnergyConsumptionDTO energyConsumptionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = energyConsumptionService.updateEnergyConsumption(energyConsumptionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
