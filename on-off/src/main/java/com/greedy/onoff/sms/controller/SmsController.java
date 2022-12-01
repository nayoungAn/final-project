package com.greedy.onoff.sms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.sms.dto.SmsCriteria;
import com.greedy.onoff.sms.service.SmsService;

@RestController
@RequestMapping("/ono")
public class SmsController {
	
	private final SmsService smsService;
	
	public SmsController(SmsService smsService) {
		this.smsService = smsService;
	}
	
	/* 문자 전송 대상 조회 */
	@GetMapping("/sms")
	public ResponseEntity<ResponseDto> selectSmsListForAdmin(@RequestBody SmsCriteria smsCriteria){
		
		List<ClassesHistoryDto> classesHistory = smsService.selectSmsListForAdmin(smsCriteria);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "문자 전송 대상 조회 완료!", classesHistory)) ;
		
	}

}
