package com.greedy.onoff.attend.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.attend.dto.AttendDto;
import com.greedy.onoff.attend.entity.Attend;
import com.greedy.onoff.attend.repository.AttendRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AttendService {
	
	private final AttendRepository attendRepository;
	private final ModelMapper modelMapper;
	
	public AttendService(AttendRepository attendRepository, ModelMapper modelMapper) {
		this.attendRepository = attendRepository;
		this.modelMapper = modelMapper;
	}
	
	//출석부 조회
	public Page<AttendDto> selectCheckList(Long classCode, int page) {
		
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("attendCode").descending());
		
		Page<Attend> attnedList = attendRepository.findByAttendCode(classCode, pageable);
		
		Page<AttendDto> attendDtoList = attnedList.map(att -> modelMapper.map(att, AttendDto.class));
		
		log.info("[AttendService] 출석부 조회 : {}", attendDtoList);
		return attendDtoList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
