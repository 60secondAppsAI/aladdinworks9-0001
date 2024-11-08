package com.aladdinworks9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks9.domain.EventLog;
import com.aladdinworks9.dto.EventLogDTO;
import com.aladdinworks9.dto.EventLogSearchDTO;
import com.aladdinworks9.dto.EventLogPageDTO;
import com.aladdinworks9.dto.EventLogConvertCriteriaDTO;
import com.aladdinworks9.service.GenericService;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EventLogService extends GenericService<EventLog, Integer> {

	List<EventLog> findAll();

	ResultDTO addEventLog(EventLogDTO eventLogDTO, RequestDTO requestDTO);

	ResultDTO updateEventLog(EventLogDTO eventLogDTO, RequestDTO requestDTO);

    Page<EventLog> getAllEventLogs(Pageable pageable);

    Page<EventLog> getAllEventLogs(Specification<EventLog> spec, Pageable pageable);

	ResponseEntity<EventLogPageDTO> getEventLogs(EventLogSearchDTO eventLogSearchDTO);
	
	List<EventLogDTO> convertEventLogsToEventLogDTOs(List<EventLog> eventLogs, EventLogConvertCriteriaDTO convertCriteria);

	EventLogDTO getEventLogDTOById(Integer eventLogId);







}





