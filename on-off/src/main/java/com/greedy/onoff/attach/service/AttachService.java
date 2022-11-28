package com.greedy.onoff.attach.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.attach.repository.MyClassRepository;
import com.greedy.onoff.attach.repository.StudentInfoRepository;
import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttachService {
	
	private final MyClassRepository myclassRepository; 
	private final StudentInfoRepository studentinfoRepository;
	private final ModelMapper modelMapper;
	
	public AttachService(MyClassRepository myclassRepository, ModelMapper modelMapper, StudentInfoRepository studentinfoRepository ) {
		this.myclassRepository = myclassRepository;
		this.modelMapper = modelMapper;
		this.studentinfoRepository = studentinfoRepository;
		
	}
	
	
     /* 1. 내강의 목록 조회 - 페이징, 내강의 목록(강사)*/


	public Page<OpenClassesDto> findbyClassesforteacher(int page, MemberDto teacher) {
		
		
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classCode").descending());
		
		Page<OpenClasses> classesList = myclassRepository.findByMember(pageable, modelMapper.map(teacher, Member.class));
		
		Page<OpenClassesDto> classesDtoList = classesList.map(openclasses -> modelMapper.map(openclasses, OpenClassesDto.class));
		
		
		
		return classesDtoList;
	}
	
	
	
	/*2. 내강의 정보 상세 조회 */

	public OpenClassesDto selectMyclass(Long classCode) {
		
		log.info("[AttachService] selectMyclass Start==============================");
		log.info("[AttachService] classCode : {}", classCode);
		
		OpenClasses openClasses = (myclassRepository.findByclassCode(classCode))
				.orElseThrow(()-> new IllegalArgumentException("해당 강좌가 없습니다. classCode =" + classCode));
		OpenClassesDto openclassesDto = modelMapper.map(openClasses, OpenClassesDto.class);
		
		log.info("[AttachService] openclassesDto: " + openclassesDto);
		
		log.info("[AttachService] selectMyclass End==============================");
		
		
		return openclassesDto;
	}

	
	
	
	/*7. 원생정보 조회 */


	public Page<MemberDto> findBystudentList(int page, String memberName) {
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("memberCode").descending());
		
		Page<Member> memberList = studentinfoRepository.findByMemberNameContains(pageable, memberName);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		
		
		
		return memberDtoList;
	}





}
