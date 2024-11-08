package com.aladdinworks9.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CoolingUnitSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer coolingUnitId;
	
	private double coolingCapacity;
	
	private double currentLoad;
	
}
