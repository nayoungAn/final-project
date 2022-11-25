package com.greedy.onoff.sms.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greedy.onoff.sms.dto.SmsDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService {

	//private final SmsRepository smsRepository;
	private final ModelMapper modelMapper;
	
	public SmsService(//SmsRepository smsRepository,
			ModelMapper modelMapper) {
		//this.smsRepository = smsRepository;
		this.modelMapper = modelMapper;
	}

	/* 문자 대상 조회 */
	public Page<SmsDto> selectSmsListForAdmin(int page) {
		
		//log.info("[SmsService] selectSmsListForAdmin Start ====================");
		
		//Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("accCode").descending());
		//Page<Sms> smsList = smsRepository.findAll(pageable);
		//Page<SmsDto> smsDtoList = smsList.map(sms -> modelMapper.map(sms, SmsDto.class));
		
		//log.info("[SmsService] smsDtoList : {}", smsDtoList.getContent());
		
		//log.info("[SmsService] selectSmsListForAdmin End ====================");
		
		return null;
	}
	
	

}
