package com.greedy.onoff.attend.dto;

import java.sql.Date;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.dto.OpenClassesDto;

import lombok.Data;

@Data
public class AttendDto {
	
	private Long attendCode;
	private Date attendDate;
	private String attendStatus;
	private ClassesHistoryDto student;
	private OpenClassesDto classes;
	
}
