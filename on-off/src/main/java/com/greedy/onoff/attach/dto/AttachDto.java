package com.greedy.onoff.attach.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greedy.onoff.append.dto.AppendDto;
import com.greedy.onoff.append.entity.Append;
import com.greedy.onoff.classes.dto.OpenClassesDto;

import lombok.Data;

@Data
public class AttachDto {
	
	private Long attachCode;
	private OpenClassesDto openClasses;
	private String attachDate;
	private List<AppendDto> appendList;
	private String attachNote;

	
	@JsonIgnore
	private List<MultipartFile> appendFile;



	



}
