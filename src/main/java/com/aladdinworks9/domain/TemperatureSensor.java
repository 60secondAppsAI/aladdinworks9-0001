package com.aladdinworks9.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="temperature_sensors")
@Getter @Setter @NoArgsConstructor
public class TemperatureSensor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="temperature_sensor_id")
	private Integer temperatureSensorId;
    
  	@Column(name="current_temperature")
	private double currentTemperature;
    
  	@Column(name="status")
	private String status;
    
	




}
