package com.greedy.onoff.acc.dto;

import java.sql.Date;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;

import lombok.Data;

@Data
public class AccDto {

	private Long accCode;
	private Date accDate;
	private String accOption;
	private String accStatus;
	private String accContent;
	private ClassesHistoryDto classesHistory;
	
}
