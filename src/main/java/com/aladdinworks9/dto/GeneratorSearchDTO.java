package com.aladdinworks9.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneratorSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer generatorId;
	
	private String fuelType;
	
	private double capacity;
	
	private double operationalHours;
	
}
