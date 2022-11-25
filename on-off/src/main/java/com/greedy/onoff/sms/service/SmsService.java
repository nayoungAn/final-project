package com.greedy.onoff.sms.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.sms.dto.SmsCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService {

	private final ClassesHistory classesHistory;
	private final ModelMapper modelMapper;
	
	public SmsService(ClassesHistory classesHistory,
			ModelMapper modelMapper) {
		this.classesHistory = classesHistory;
		this.modelMapper = modelMapper;
	}

	/* 문자 대상 조회 */
	public List<ClassesHistoryDto> selectSmsListForAdmin(SmsCriteria smsCriteria) {
		
		if(smsCriteria.getClassName() != null) {
			// 클래스명 기준으로 List<ClassesHistory> 조회
			smsCriteria.setClassName(null);
					
		} else if (smsCriteria.getMemberName() != null) {
			// 멤버이름 기준으로 List<ClassesHistory> 조회
			smsCriteria.setMemberName(null);
			
		}
		
		return null;
	}
	
	
	
	

	
	
	
	

}
