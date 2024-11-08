package com.aladdinworks9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks9.domain.EnergyConsumption;
import com.aladdinworks9.dto.EnergyConsumptionDTO;
import com.aladdinworks9.dto.EnergyConsumptionSearchDTO;
import com.aladdinworks9.dto.EnergyConsumptionPageDTO;
import com.aladdinworks9.dto.EnergyConsumptionConvertCriteriaDTO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EnergyConsumptionService extends GenericService<EnergyConsumption, Integer> {

	List<EnergyConsumption> findAll();

	ResultDTO addEnergyConsumption(EnergyConsumptionDTO energyConsumptionDTO, RequestDTO requestDTO);

	ResultDTO updateEnergyConsumption(EnergyConsumptionDTO energyConsumptionDTO, RequestDTO requestDTO);

    Page<EnergyConsumption> getAllEnergyConsumptions(Pageable pageable);

    Page<EnergyConsumption> getAllEnergyConsumptions(Specification<EnergyConsumption> spec, Pageable pageable);

	ResponseEntity<EnergyConsumptionPageDTO> getEnergyConsumptions(EnergyConsumptionSearchDTO energyConsumptionSearchDTO);
	
	List<EnergyConsumptionDTO> convertEnergyConsumptionsToEnergyConsumptionDTOs(List<EnergyConsumption> energyConsumptions, EnergyConsumptionConvertCriteriaDTO convertCriteria);

	EnergyConsumptionDTO getEnergyConsumptionDTOById(Integer energyConsumptionId);







}





