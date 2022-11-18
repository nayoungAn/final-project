package com.greedy.onoff.ref.dto;

import java.sql.Date;

import com.greedy.onoff.acc.dto.AccDto;

import lombok.Data;

@Data
public class RefDto {
	
	private Long refPrice;
	private Date refDate;
	private String refContent;
	private AccDto acc;
	
}
