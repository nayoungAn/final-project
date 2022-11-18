package com.greedy.onoff.attach.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.onoff.classes.dto.ClassesDto;

import lombok.Data;

@Data
public class AttachDto {
	
	private List<ClassesDto> classCode;
	private Date attachDate;
	private String attachNote;
	

}
