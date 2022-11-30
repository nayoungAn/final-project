package com.greedy.onoff.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.sms.dto.SmsCriteria;
import com.greedy.onoff.sms.repository.SmsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService {
	
	private final SmsRepository smsRepository;
	private final ModelMapper modelMapper;
	
	public SmsService(SmsRepository smsRepository, ModelMapper modelMapper) {
		this.smsRepository = smsRepository;
		this.modelMapper = modelMapper;
	}

	/* 문자 전송대상 조회 */
	public List<ClassesHistoryDto> selectSmsListForAdmin(SmsCriteria smsCriteria) {
		
		log.info("[SmsService] smsClassList Start ============================");
		
		List<ClassesHistory> classesHistoryList = null; 
		
		if(smsCriteria.getClassName() != null) {
			// 클래스명 기준으로 List<ClassesHistory> 조회
			classesHistoryList = smsRepository.findByClassName(smsCriteria.getClassName());
			
			log.info("[SmsService] classHistoryList : {}" , classesHistoryList);
			
		} else if (smsCriteria.getMemberName() != null) {
			// 멤버이름 기준으로 List<ClassesHistory> 조회
			
			classesHistoryList = smsRepository.findByMemberName(smsCriteria.getMemberName());
		}
		
		// entity -> dto 변환 후 반환
		List <ClassesHistoryDto> classesHistoryDtoList = classesHistoryList.stream().map(classes -> modelMapper.map(classes, ClassesHistoryDto.class))
				.collect(Collectors.toList());
		
		log.info("[SmsService] smsClassList End ============================");
		
		return classesHistoryDtoList;
	}
	
	
	
	

	
	
	
	

}
