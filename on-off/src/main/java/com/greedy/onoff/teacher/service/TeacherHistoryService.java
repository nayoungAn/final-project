package com.greedy.onoff.teacher.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.repository.MemberRepository;
import com.greedy.onoff.teacher.dto.TeacherHistoryDto;
import com.greedy.onoff.teacher.entity.TeacherHistory;
import com.greedy.onoff.teacher.repository.TeacherHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeacherHistoryService {
	
	private final ModelMapper modelMapper;
	private final TeacherHistoryRepository teacherHistoryRepository;
	private final MemberRepository memberRepository;
	
	public TeacherHistoryService(ModelMapper modelMapper, MemberRepository memberRepository, TeacherHistoryRepository teacherHistoryRepository) {
		this.modelMapper = modelMapper;
		this.teacherHistoryRepository = teacherHistoryRepository;
		this.memberRepository = memberRepository;
		
	}
	

	

	/* 강사 목록 조회 -  상태 여부 'N'포함, 페이징 X (관리자)*/
	public List<TeacherHistoryDto> selectTeacherHistoryListForAdmin(Long memberCode) {
			
			Member member = memberRepository.findByMemberCode(memberCode);
			
			List<TeacherHistory> teacherHistoryList = teacherHistoryRepository.findByMember(member);
			List<TeacherHistoryDto> teacherHistoryListDto = teacherHistoryList.stream()
			.map(teacherHistory -> modelMapper.map(teacherHistory, TeacherHistoryDto.class))
			.collect(Collectors.toList());
			
			
			log.info("[TeacherHistoryService] teacherHistoryListDto End =====================" );
			
			return teacherHistoryListDto;
		}	
		

	

}