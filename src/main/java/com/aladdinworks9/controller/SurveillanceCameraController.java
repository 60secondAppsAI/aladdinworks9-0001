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

import com.aladdinworks9.domain.SurveillanceCamera;
import com.aladdinworks9.dto.SurveillanceCameraDTO;
import com.aladdinworks9.dto.SurveillanceCameraSearchDTO;
import com.aladdinworks9.dto.SurveillanceCameraPageDTO;
import com.aladdinworks9.service.SurveillanceCameraService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/surveillanceCamera")
@RestController
public class SurveillanceCameraController {

	private final static Logger logger = LoggerFactory.getLogger(SurveillanceCameraController.class);

	@Autowired
	SurveillanceCameraService surveillanceCameraService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SurveillanceCamera> getAll() {

		List<SurveillanceCamera> surveillanceCameras = surveillanceCameraService.findAll();
		
		return surveillanceCameras;	
	}

	@GetMapping(value = "/{surveillanceCameraId}")
	@ResponseBody
	public SurveillanceCameraDTO getSurveillanceCamera(@PathVariable Integer surveillanceCameraId) {
		
		return (surveillanceCameraService.getSurveillanceCameraDTOById(surveillanceCameraId));
	}

 	@RequestMapping(value = "/addSurveillanceCamera", method = RequestMethod.POST)
	public ResponseEntity<?> addSurveillanceCamera(@RequestBody SurveillanceCameraDTO surveillanceCameraDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = surveillanceCameraService.addSurveillanceCamera(surveillanceCameraDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/surveillanceCameras")
	public ResponseEntity<SurveillanceCameraPageDTO> getSurveillanceCameras(SurveillanceCameraSearchDTO surveillanceCameraSearchDTO) {
 
		return surveillanceCameraService.getSurveillanceCameras(surveillanceCameraSearchDTO);
	}	

	@RequestMapping(value = "/updateSurveillanceCamera", method = RequestMethod.POST)
	public ResponseEntity<?> updateSurveillanceCamera(@RequestBody SurveillanceCameraDTO surveillanceCameraDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = surveillanceCameraService.updateSurveillanceCamera(surveillanceCameraDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
