package com.greedy.onoff.sms.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.sms.dto.SmsDto;
import com.greedy.onoff.sms.entity.Sms;
import com.greedy.onoff.sms.repository.SmsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService {
	
	private final SmsRepository smsRepository;
	private final ModelMapper modelMapper;
	
	public SmsService(SmsRepository smsRepository,
			ModelMapper modelMapper) {
		this.smsRepository = smsRepository;
		this.modelMapper = modelMapper;
	}

	/* 문자 전송 대상 조회 */
	public Page<SmsDto> selectSmsListForAdmin(int page) {
		
		log.info("[SmsService] selectSmsListForAdmin Start ====================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("smsCode").descending());
		Page<Sms> smsList = smsRepository.findAll(pageable);
		Page<SmsDto> smsDtoList = smsList.map(sms -> modelMapper.map(sms, SmsDto.class));
		
		log.info("[SmsService] smsDtoList : {}", smsDtoList.getContent());
		
		log.info("[SmsService] selectSmsListForAdmin End ====================");
		
		return smsDtoList;
	}

	/* 문자 전송 대상 상세 조회 */
	public Object selectSmsForAdmin(Long smsCode) {
		
		log.info("[SmsService] selectSmsForAdmin Start ====================");
		log.info("[SmsService] smsCode " + smsCode);
		
		Sms sms = smsRepository.findById(smsCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 내역이 업습니다!. accCode=" + smsCode));
		SmsDto smsDto = modelMapper.map(sms, SmsDto.class);
		
		log.info("[SmsService] smsDto : " + smsDto);
		log.info("[SmsService] selectSmsForAdmin End ====================");
		
		return smsCode;
	}
	
	
	

}
