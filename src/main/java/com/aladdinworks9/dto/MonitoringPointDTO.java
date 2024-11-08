package com.aladdinworks9.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MonitoringPointDTO {

	private Integer monitoringPointId;

	private String parameterType;

	private double currentValue;






}
