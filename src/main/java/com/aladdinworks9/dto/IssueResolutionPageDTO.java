package com.aladdinworks9.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueResolutionPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<IssueResolutionDTO> issueResolutions;
}





