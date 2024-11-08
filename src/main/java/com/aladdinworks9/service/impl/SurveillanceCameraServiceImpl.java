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
import com.aladdinworks9.dao.SurveillanceCameraDAO;
import com.aladdinworks9.domain.SurveillanceCamera;
import com.aladdinworks9.dto.SurveillanceCameraDTO;
import com.aladdinworks9.dto.SurveillanceCameraSearchDTO;
import com.aladdinworks9.dto.SurveillanceCameraPageDTO;
import com.aladdinworks9.dto.SurveillanceCameraConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.SurveillanceCameraService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class SurveillanceCameraServiceImpl extends GenericServiceImpl<SurveillanceCamera, Integer> implements SurveillanceCameraService {

    private final static Logger logger = LoggerFactory.getLogger(SurveillanceCameraServiceImpl.class);

	@Autowired
	SurveillanceCameraDAO surveillanceCameraDao;

	


	@Override
	public GenericDAO<SurveillanceCamera, Integer> getDAO() {
		return (GenericDAO<SurveillanceCamera, Integer>) surveillanceCameraDao;
	}
	
	public List<SurveillanceCamera> findAll () {
		List<SurveillanceCamera> surveillanceCameras = surveillanceCameraDao.findAll();
		
		return surveillanceCameras;	
		
	}

	public ResultDTO addSurveillanceCamera(SurveillanceCameraDTO surveillanceCameraDTO, RequestDTO requestDTO) {

		SurveillanceCamera surveillanceCamera = new SurveillanceCamera();

		surveillanceCamera.setSurveillanceCameraId(surveillanceCameraDTO.getSurveillanceCameraId());


		surveillanceCamera.setModel(surveillanceCameraDTO.getModel());


		surveillanceCamera.setCoverageAngle(surveillanceCameraDTO.getCoverageAngle());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		surveillanceCamera = surveillanceCameraDao.save(surveillanceCamera);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SurveillanceCamera> getAllSurveillanceCameras(Pageable pageable) {
		return surveillanceCameraDao.findAll(pageable);
	}

	public Page<SurveillanceCamera> getAllSurveillanceCameras(Specification<SurveillanceCamera> spec, Pageable pageable) {
		return surveillanceCameraDao.findAll(spec, pageable);
	}

	public ResponseEntity<SurveillanceCameraPageDTO> getSurveillanceCameras(SurveillanceCameraSearchDTO surveillanceCameraSearchDTO) {
	
			Integer surveillanceCameraId = surveillanceCameraSearchDTO.getSurveillanceCameraId(); 
 			String model = surveillanceCameraSearchDTO.getModel(); 
  			String sortBy = surveillanceCameraSearchDTO.getSortBy();
			String sortOrder = surveillanceCameraSearchDTO.getSortOrder();
			String searchQuery = surveillanceCameraSearchDTO.getSearchQuery();
			Integer page = surveillanceCameraSearchDTO.getPage();
			Integer size = surveillanceCameraSearchDTO.getSize();

	        Specification<SurveillanceCamera> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, surveillanceCameraId, "surveillanceCameraId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SurveillanceCamera> surveillanceCameras = this.getAllSurveillanceCameras(spec, pageable);
		
		//System.out.println(String.valueOf(surveillanceCameras.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(surveillanceCameras.getTotalPages()));
		
		List<SurveillanceCamera> surveillanceCamerasList = surveillanceCameras.getContent();
		
		SurveillanceCameraConvertCriteriaDTO convertCriteria = new SurveillanceCameraConvertCriteriaDTO();
		List<SurveillanceCameraDTO> surveillanceCameraDTOs = this.convertSurveillanceCamerasToSurveillanceCameraDTOs(surveillanceCamerasList,convertCriteria);
		
		SurveillanceCameraPageDTO surveillanceCameraPageDTO = new SurveillanceCameraPageDTO();
		surveillanceCameraPageDTO.setSurveillanceCameras(surveillanceCameraDTOs);
		surveillanceCameraPageDTO.setTotalElements(surveillanceCameras.getTotalElements());
		return ResponseEntity.ok(surveillanceCameraPageDTO);
	}

	public List<SurveillanceCameraDTO> convertSurveillanceCamerasToSurveillanceCameraDTOs(List<SurveillanceCamera> surveillanceCameras, SurveillanceCameraConvertCriteriaDTO convertCriteria) {
		
		List<SurveillanceCameraDTO> surveillanceCameraDTOs = new ArrayList<SurveillanceCameraDTO>();
		
		for (SurveillanceCamera surveillanceCamera : surveillanceCameras) {
			surveillanceCameraDTOs.add(convertSurveillanceCameraToSurveillanceCameraDTO(surveillanceCamera,convertCriteria));
		}
		
		return surveillanceCameraDTOs;

	}
	
	public SurveillanceCameraDTO convertSurveillanceCameraToSurveillanceCameraDTO(SurveillanceCamera surveillanceCamera, SurveillanceCameraConvertCriteriaDTO convertCriteria) {
		
		SurveillanceCameraDTO surveillanceCameraDTO = new SurveillanceCameraDTO();
		
		surveillanceCameraDTO.setSurveillanceCameraId(surveillanceCamera.getSurveillanceCameraId());

	
		surveillanceCameraDTO.setModel(surveillanceCamera.getModel());

	
		surveillanceCameraDTO.setCoverageAngle(surveillanceCamera.getCoverageAngle());

	

		
		return surveillanceCameraDTO;
	}

	public ResultDTO updateSurveillanceCamera(SurveillanceCameraDTO surveillanceCameraDTO, RequestDTO requestDTO) {
		
		SurveillanceCamera surveillanceCamera = surveillanceCameraDao.getById(surveillanceCameraDTO.getSurveillanceCameraId());

		surveillanceCamera.setSurveillanceCameraId(ControllerUtils.setValue(surveillanceCamera.getSurveillanceCameraId(), surveillanceCameraDTO.getSurveillanceCameraId()));

		surveillanceCamera.setModel(ControllerUtils.setValue(surveillanceCamera.getModel(), surveillanceCameraDTO.getModel()));

		surveillanceCamera.setCoverageAngle(ControllerUtils.setValue(surveillanceCamera.getCoverageAngle(), surveillanceCameraDTO.getCoverageAngle()));



        surveillanceCamera = surveillanceCameraDao.save(surveillanceCamera);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SurveillanceCameraDTO getSurveillanceCameraDTOById(Integer surveillanceCameraId) {
	
		SurveillanceCamera surveillanceCamera = surveillanceCameraDao.getById(surveillanceCameraId);
			
		
		SurveillanceCameraConvertCriteriaDTO convertCriteria = new SurveillanceCameraConvertCriteriaDTO();
		return(this.convertSurveillanceCameraToSurveillanceCameraDTO(surveillanceCamera,convertCriteria));
	}







}
