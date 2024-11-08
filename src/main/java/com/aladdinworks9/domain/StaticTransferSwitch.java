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
@Table(name="static_transfer_switchs")
@Getter @Setter @NoArgsConstructor
public class StaticTransferSwitch {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="static_transfer_switch_id")
	private Integer staticTransferSwitchId;
    
  	@Column(name="input_current")
	private double inputCurrent;
    
  	@Column(name="output_current")
	private double outputCurrent;
    
  	@Column(name="status")
	private String status;
    
	




}
