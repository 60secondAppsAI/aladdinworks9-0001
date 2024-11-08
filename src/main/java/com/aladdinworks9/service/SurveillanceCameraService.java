package com.aladdinworks9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks9.domain.SurveillanceCamera;
import com.aladdinworks9.dto.SurveillanceCameraDTO;
import com.aladdinworks9.dto.SurveillanceCameraSearchDTO;
import com.aladdinworks9.dto.SurveillanceCameraPageDTO;
import com.aladdinworks9.dto.SurveillanceCameraConvertCriteriaDTO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SurveillanceCameraService extends GenericService<SurveillanceCamera, Integer> {

	List<SurveillanceCamera> findAll();

	ResultDTO addSurveillanceCamera(SurveillanceCameraDTO surveillanceCameraDTO, RequestDTO requestDTO);

	ResultDTO updateSurveillanceCamera(SurveillanceCameraDTO surveillanceCameraDTO, RequestDTO requestDTO);

    Page<SurveillanceCamera> getAllSurveillanceCameras(Pageable pageable);

    Page<SurveillanceCamera> getAllSurveillanceCameras(Specification<SurveillanceCamera> spec, Pageable pageable);

	ResponseEntity<SurveillanceCameraPageDTO> getSurveillanceCameras(SurveillanceCameraSearchDTO surveillanceCameraSearchDTO);
	
	List<SurveillanceCameraDTO> convertSurveillanceCamerasToSurveillanceCameraDTOs(List<SurveillanceCamera> surveillanceCameras, SurveillanceCameraConvertCriteriaDTO convertCriteria);

	SurveillanceCameraDTO getSurveillanceCameraDTOById(Integer surveillanceCameraId);







}





