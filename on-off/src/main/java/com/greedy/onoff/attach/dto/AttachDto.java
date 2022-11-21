package com.greedy.onoff.attach.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.onoff.classes.dto.OpenClassesDto;

import lombok.Data;

@Data
public class AttachDto {
	
	private Long attachCode;
	private List<OpenClassesDto> classCode;
	private Date attachDate;
	private String attachNote;
	

}
