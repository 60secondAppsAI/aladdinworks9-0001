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

import com.aladdinworks9.domain.Equipment;
import com.aladdinworks9.dto.EquipmentDTO;
import com.aladdinworks9.dto.EquipmentSearchDTO;
import com.aladdinworks9.dto.EquipmentPageDTO;
import com.aladdinworks9.service.EquipmentService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/equipment")
@RestController
public class EquipmentController {

	private final static Logger logger = LoggerFactory.getLogger(EquipmentController.class);

	@Autowired
	EquipmentService equipmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Equipment> getAll() {

		List<Equipment> equipments = equipmentService.findAll();
		
		return equipments;	
	}

	@GetMapping(value = "/{equipmentId}")
	@ResponseBody
	public EquipmentDTO getEquipment(@PathVariable Integer equipmentId) {
		
		return (equipmentService.getEquipmentDTOById(equipmentId));
	}

 	@RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
	public ResponseEntity<?> addEquipment(@RequestBody EquipmentDTO equipmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = equipmentService.addEquipment(equipmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/equipments")
	public ResponseEntity<EquipmentPageDTO> getEquipments(EquipmentSearchDTO equipmentSearchDTO) {
 
		return equipmentService.getEquipments(equipmentSearchDTO);
	}	

	@RequestMapping(value = "/updateEquipment", method = RequestMethod.POST)
	public ResponseEntity<?> updateEquipment(@RequestBody EquipmentDTO equipmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = equipmentService.updateEquipment(equipmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
