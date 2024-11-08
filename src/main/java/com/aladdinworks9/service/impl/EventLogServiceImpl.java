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
import com.aladdinworks9.dao.EventLogDAO;
import com.aladdinworks9.domain.EventLog;
import com.aladdinworks9.dto.EventLogDTO;
import com.aladdinworks9.dto.EventLogSearchDTO;
import com.aladdinworks9.dto.EventLogPageDTO;
import com.aladdinworks9.dto.EventLogConvertCriteriaDTO;
import com.aladdinworks9.dto.common.RequestDTO;
import com.aladdinworks9.dto.common.ResultDTO;
import com.aladdinworks9.service.EventLogService;
import com.aladdinworks9.util.ControllerUtils;





@Service
public class EventLogServiceImpl extends GenericServiceImpl<EventLog, Integer> implements EventLogService {

    private final static Logger logger = LoggerFactory.getLogger(EventLogServiceImpl.class);

	@Autowired
	EventLogDAO eventLogDao;

	


	@Override
	public GenericDAO<EventLog, Integer> getDAO() {
		return (GenericDAO<EventLog, Integer>) eventLogDao;
	}
	
	public List<EventLog> findAll () {
		List<EventLog> eventLogs = eventLogDao.findAll();
		
		return eventLogs;	
		
	}

	public ResultDTO addEventLog(EventLogDTO eventLogDTO, RequestDTO requestDTO) {

		EventLog eventLog = new EventLog();

		eventLog.setEventLogId(eventLogDTO.getEventLogId());


		eventLog.setEventType(eventLogDTO.getEventType());


		eventLog.setEventDate(eventLogDTO.getEventDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		eventLog = eventLogDao.save(eventLog);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<EventLog> getAllEventLogs(Pageable pageable) {
		return eventLogDao.findAll(pageable);
	}

	public Page<EventLog> getAllEventLogs(Specification<EventLog> spec, Pageable pageable) {
		return eventLogDao.findAll(spec, pageable);
	}

	public ResponseEntity<EventLogPageDTO> getEventLogs(EventLogSearchDTO eventLogSearchDTO) {
	
			Integer eventLogId = eventLogSearchDTO.getEventLogId(); 
 			String eventType = eventLogSearchDTO.getEventType(); 
   			String sortBy = eventLogSearchDTO.getSortBy();
			String sortOrder = eventLogSearchDTO.getSortOrder();
			String searchQuery = eventLogSearchDTO.getSearchQuery();
			Integer page = eventLogSearchDTO.getPage();
			Integer size = eventLogSearchDTO.getSize();

	        Specification<EventLog> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, eventLogId, "eventLogId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, eventType, "eventType"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("eventType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<EventLog> eventLogs = this.getAllEventLogs(spec, pageable);
		
		//System.out.println(String.valueOf(eventLogs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(eventLogs.getTotalPages()));
		
		List<EventLog> eventLogsList = eventLogs.getContent();
		
		EventLogConvertCriteriaDTO convertCriteria = new EventLogConvertCriteriaDTO();
		List<EventLogDTO> eventLogDTOs = this.convertEventLogsToEventLogDTOs(eventLogsList,convertCriteria);
		
		EventLogPageDTO eventLogPageDTO = new EventLogPageDTO();
		eventLogPageDTO.setEventLogs(eventLogDTOs);
		eventLogPageDTO.setTotalElements(eventLogs.getTotalElements());
		return ResponseEntity.ok(eventLogPageDTO);
	}

	public List<EventLogDTO> convertEventLogsToEventLogDTOs(List<EventLog> eventLogs, EventLogConvertCriteriaDTO convertCriteria) {
		
		List<EventLogDTO> eventLogDTOs = new ArrayList<EventLogDTO>();
		
		for (EventLog eventLog : eventLogs) {
			eventLogDTOs.add(convertEventLogToEventLogDTO(eventLog,convertCriteria));
		}
		
		return eventLogDTOs;

	}
	
	public EventLogDTO convertEventLogToEventLogDTO(EventLog eventLog, EventLogConvertCriteriaDTO convertCriteria) {
		
		EventLogDTO eventLogDTO = new EventLogDTO();
		
		eventLogDTO.setEventLogId(eventLog.getEventLogId());

	
		eventLogDTO.setEventType(eventLog.getEventType());

	
		eventLogDTO.setEventDate(eventLog.getEventDate());

	

		
		return eventLogDTO;
	}

	public ResultDTO updateEventLog(EventLogDTO eventLogDTO, RequestDTO requestDTO) {
		
		EventLog eventLog = eventLogDao.getById(eventLogDTO.getEventLogId());

		eventLog.setEventLogId(ControllerUtils.setValue(eventLog.getEventLogId(), eventLogDTO.getEventLogId()));

		eventLog.setEventType(ControllerUtils.setValue(eventLog.getEventType(), eventLogDTO.getEventType()));

		eventLog.setEventDate(ControllerUtils.setValue(eventLog.getEventDate(), eventLogDTO.getEventDate()));



        eventLog = eventLogDao.save(eventLog);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EventLogDTO getEventLogDTOById(Integer eventLogId) {
	
		EventLog eventLog = eventLogDao.getById(eventLogId);
			
		
		EventLogConvertCriteriaDTO convertCriteria = new EventLogConvertCriteriaDTO();
		return(this.convertEventLogToEventLogDTO(eventLog,convertCriteria));
	}







}
