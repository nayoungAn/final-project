package com.greedy.onoff.classes.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.entity.ClassesSchedule;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.classes.repository.ClassesRepository;
import com.greedy.onoff.classes.repository.ClassesScheduleRepository;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.subject.entity.Subject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassesService {
	
	private final ClassesRepository classesRepository;
	private final ClassesScheduleRepository classesScheduleRepository;
	private final ModelMapper modelMapper;
	
	public ClassesService(ClassesRepository classesRepository, ClassesScheduleRepository classesScheduleRepository, ModelMapper modelMapper) {
		this.classesRepository = classesRepository;
		this.modelMapper = modelMapper;
		this.classesScheduleRepository = classesScheduleRepository;

	}
	
	
	/* 강의 목록 조회 - 페이징, 상태 여부 'N' 포함 (관리자) */
	public Page<OpenClassesDto> selectClassListForAdmin(int page) {
		
		log.info("[ClassesService] selectClassListForAdmin Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classCode").descending());
		
		Page<OpenClasses> classList = classesRepository.findAll(pageable);
		Page<OpenClassesDto> classDtoList = classList.map(OpenClasses -> modelMapper.map(OpenClasses, OpenClassesDto.class));
		
		
		log.info("[ClassesService] classDtoList : {}", classDtoList.getContent());
		
		log.info("[ClassesService] selectClassListForAdmin End =====================" );
		
		return classDtoList;
	}




	
	/* 강의 목록 조회 - 강의명 검색 기준, 페이징, 상태 여부 'N' 포함 (관리자)  */
	public Page<OpenClassesDto> selectClassListByClassName(int page, String className) {
		
		log.info("[ClassesService] selectClassListByClassName Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classCode").descending());
		
		Page<OpenClasses> classList = classesRepository.findByClassNameContains(pageable, className);
		Page<OpenClassesDto> classDtoList = classList.map(OpenClasses -> modelMapper.map(OpenClasses, OpenClassesDto.class));
		
		log.info("[ClassesService] classDtoList : {}", classDtoList.getContent());
		
		log.info("[ClassesService] selectClassListByClassName End =====================" );
		
		return classDtoList;
	}



	/* 강의 상세 조회 - classCode로 강의 1개 조회, 상태 여부 'N' 포함 (관리자) */
	public OpenClassesDto selectClassForAdmin(Long classCode) { 
        log.info("[ClassesService] selectClassForAdmin Start ===================================");
        log.info("[ClassesService] classCode : " + classCode);
        
        OpenClasses classes = classesRepository.findByClassCode(classCode)
        		.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. classCode=" + classCode));
        OpenClassesDto classesDto = modelMapper.map(classes, OpenClassesDto.class);
        
        log.info("[ClassesService] classesDto : " + classesDto);
        
        log.info("[ClassesService] selectClassForAdmin End ===================================");
        
        return classesDto;
	}



	/*  강의 등록 */
	@Transactional
	public OpenClassesDto insertClasses(OpenClassesDto openClassesDto) {
		
		log.info("[ClassesService] insertClasses Start ===================================");
		log.info("[ClassesService] insertClasses : {}", openClassesDto);
		
			classesRepository.save(modelMapper.map(openClassesDto, OpenClasses.class));
			
			
		log.info("[ClassesService] insertClasses End ===================================");
		
		return openClassesDto;
		
	}
	
	/* 강의 수정 */
	@Transactional

	public OpenClassesDto updateClasses(OpenClassesDto openClassesDto) {

		log.info("[ClassesService] updateClasses Start ===================================");
		log.info("[ClassesService] openClassesDto : {}", openClassesDto);


			OpenClasses oriOpenClasses = classesRepository.findByClassCode(openClassesDto.getClassCode()).orElseThrow(
					() -> new IllegalArgumentException("해당 강의가 없습니다. classCode=" + openClassesDto.getClassCode()));
	
			/* 조회 했던 기존 엔티티의 내용을 수정 */
			oriOpenClasses.update(
					openClassesDto.getClassCode(),
					openClassesDto.getClassName(), 
					openClassesDto.getClassQuota(),
					openClassesDto.getClassPrice(),
					openClassesDto.getClassStartDate(),
					openClassesDto.getClassEndDate(),
					openClassesDto.getClassRoom(),
					openClassesDto.getClassStatus(),
					openClassesDto.getClassDescription(),
					openClassesDto.getClassStudents(),
					openClassesDto.getClassesScheduleList().stream()
					.map(scheduleList -> modelMapper.map(scheduleList,ClassesSchedule.class))
					.collect(Collectors.toList()),
					modelMapper.map(openClassesDto.getMember(), Member.class), 
					modelMapper.map(openClassesDto.getSubject(), Subject.class));
					

			classesRepository.save(oriOpenClasses);
			/* null값 스케쥴코드 삭제*/
			List<ClassesSchedule> classesSchedule = classesScheduleRepository.findByClassCode(null);
			
			classesScheduleRepository.deleteAll(classesSchedule);
			
		log.info("[ClassesService] updateClasses End ===================================");

		return openClassesDto;
	}


	
	}


	