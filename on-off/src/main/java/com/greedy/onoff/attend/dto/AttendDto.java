package com.greedy.onoff.attend.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class AttendDto {
	
	private Long attendCode;
	private Date attendDate;
	private String attendStatus;
	//private classesHistoryDto student;
	//private ClassesDto class;
	
}
