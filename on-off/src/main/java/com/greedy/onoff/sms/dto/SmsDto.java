package com.greedy.onoff.sms.dto;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;

import lombok.Data;

@Data
public class SmsDto {
	
	private Long smsCode;
	private ClassesHistoryDto classesHistory;

}
