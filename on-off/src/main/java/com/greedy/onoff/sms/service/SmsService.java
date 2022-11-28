//package com.greedy.onoff.sms.service;
//
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import com.greedy.onoff.classes.dto.ClassesHistoryDto;
//import com.greedy.onoff.classes.entity.ClassesHistory;
//import com.greedy.onoff.member.entity.Member;
//import com.greedy.onoff.sms.dto.SmsCriteria;
//import com.greedy.onoff.sms.repository.SmsRepository;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class SmsService {
//	
//	private final SmsRepository smsRepository;
//	private final ModelMapper modelMapper;
//	
//	public SmsService(SmsRepository smsRepository, ModelMapper modelMapper) {
//		this.smsRepository = smsRepository;
//		this.modelMapper = modelMapper;
//	}
//
//	/* 문자 전송대상 조회 */
//	public List<ClassesHistory> selectSmsListForAdmin(SmsCriteria smsCriteria) {
//		
//		List<ClassesHistory> classHistoryList = null; 
//		
//		if(smsCriteria.getClassName() != null) {
//			// 클래스명 기준으로 List<ClassesHistory> 조회
//			classHistoryList = smsRepository.findByClassName(smsCriteria.getClassName());
//					
//		} else if (smsCriteria.getMemberName() != null) {
//			// 멤버이름 기준으로 List<ClassesHistory> 조회
//			
//			//classHistoryList = smsRepository.findByMemberName(smsCriteria.getMemberName());
//			
//		}
//		
//		ClassesHistory classesHistoryDto = modelMapper.map(classHistoryList, ClassesHistory.class);
//		// entity -> dto 변환 후 반환
//		
//		return null;
//	}
//	
//	
//	
//	
//
//	
//	
//	
//	
//
//}
