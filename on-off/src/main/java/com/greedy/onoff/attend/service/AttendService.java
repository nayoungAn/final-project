package com.greedy.onoff.attend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.attend.dto.AttendDto;
import com.greedy.onoff.attend.entity.Attend;
import com.greedy.onoff.attend.repository.AttendRepository;
import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.repository.HistoryRepository;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.FindMemberFaildeException;
import com.greedy.onoff.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AttendService {
	
	private final AttendRepository attendRepository;
	private final ModelMapper modelMapper;
	private final HistoryRepository historyRepository;
	private final MemberRepository memberRepository;
	
	public AttendService(AttendRepository attendRepository, ModelMapper modelMapper,
			HistoryRepository historyRepository, MemberRepository memberRepository) {
		this.attendRepository = attendRepository;
		this.modelMapper = modelMapper;
		this.historyRepository = historyRepository;
		this.memberRepository = memberRepository;
				
	}
	
	//학생 조회
	public List<ClassesHistoryDto> selectStudent(Long classCode) {
		
		List<ClassesHistory> historyList = historyRepository.findByClassCode(classCode);
		
		return historyList.stream().map(history -> modelMapper.map(history, ClassesHistoryDto.class)).collect(Collectors.toList());
	}

	//출석부 조회
	public List<AttendDto> selectCheckList(Long classCode) {
		
		List<Attend> attendList = attendRepository.findByClassCode(classCode);
		
				
		return attendList.stream().map(attend -> modelMapper.map(attend, AttendDto.class)).collect(Collectors.toList());
	}

	
	@Transactional
	public AttendDto updateAttend(AttendDto attendDto) {
		
		Attend foundAttend = attendRepository.findById(attendDto.getAttendCode())
				.orElseThrow(()-> new RuntimeException("학생 정보를 찾을 수 없습니다."));
		
		foundAttend.update(attendDto.getAttendStatus());
		
		attendRepository.save(foundAttend);
		
		return attendDto;		
	}


	

	
	
	
	
	
	
	
	
	
	
	
}
