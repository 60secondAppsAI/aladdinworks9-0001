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
@Table(name="event_logs")
@Getter @Setter @NoArgsConstructor
public class EventLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="event_log_id")
	private Integer eventLogId;
    
  	@Column(name="event_type")
	private String eventType;
    
  	@Column(name="event_date")
	private Date eventDate;
    
	




}
