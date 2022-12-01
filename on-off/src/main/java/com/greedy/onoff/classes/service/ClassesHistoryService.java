package com.greedy.onoff.classes.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.classes.repository.ClassesRepository;
import com.greedy.onoff.classes.repository.HistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassesHistoryService {
	
	private final HistoryRepository historyRepository;
	private final ModelMapper modelMapper;
	private final ClassesRepository classesRepository;
	public ClassesHistoryService(HistoryRepository historyRepository,
			ModelMapper modelMapper, ClassesRepository classesRepository) {
		this.modelMapper = modelMapper;
		this.historyRepository = historyRepository;
		this.classesRepository = classesRepository;

	}
		

	/*  수강 이력 조회 - 상태 'n' 제외 , 페이지 X (관리자) */
	public ClassesHistoryDto selectClassHistoryDetailForAdmin(Long classHistoryCode) { 

	     log.info("[ClassesHistoryService] selectClassHistoryDetailForAdmin Start ===================================");
	        log.info("[ClassesHistoryService] classHistoryCode : " + classHistoryCode);
	        
	        ClassesHistory classesHistory = historyRepository.findByClassHistoryCode(classHistoryCode)
	        		.orElseThrow(() -> new IllegalArgumentException("해당 수강이력이 없습니다. classHistoryCode=" + classHistoryCode));
	        
	        ClassesHistoryDto classesHistoryDto = modelMapper.map(classesHistory, ClassesHistoryDto.class);
	        
	        log.info("[ClassesHistoryService] classesHistoryDto : " + classesHistoryDto);
	        
	        log.info("[ClassesHistoryService] selectClassHistoryDetailForAdmin End ===================================");
	        
	        return classesHistoryDto;
		}
	
	/*  수강 이력 등록 */
	@Transactional
	public ClassesHistoryDto insertClassesHistory(ClassesHistoryDto classesHistoryDto) {
		
		log.info("[ClassesHistoryService] insertClassesHistory Start ===================================");
		log.info("[ClassesHistoryService] insertClassesHistory : {}", classesHistoryDto);
		
		historyRepository.save(modelMapper.map(classesHistoryDto, ClassesHistory.class));
		
		/*  등록 시 강의 수강인원 + 1 */
		OpenClasses openClasses = classesRepository.findByClassCode(classesHistoryDto.getOpenClasses().getClassCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. classCode=" + classesHistoryDto.getOpenClasses().getClassCode()));
		openClasses.setClassStudents(openClasses.getClassStudents() + 1);
		if(openClasses.getClassStudents() > openClasses.getClassQuota())
		{
			throw new RuntimeException("인원 모집이 끝났습니다.");
		}
		log.info("[ClassesService] insertClasses End ===================================");
		
		return classesHistoryDto;
					
	}

	/* 수강 이력 수정 */
	@Transactional
	public ClassesHistoryDto updateClassesHistory(ClassesHistoryDto classesHistoryDto) {

		log.info("[ClassesHistoryService] updateClassesHistory Start ===================================");
		log.info("[ClassesHistoryService] classesHistoryDto : {}", classesHistoryDto);


			ClassesHistory oriClassesHistory = historyRepository.findByClassHistoryCode(classesHistoryDto.getClassHistoryCode()).orElseThrow(
					() -> new IllegalArgumentException("해당 수강 이력이 없습니다. classHistoryCode=" + classesHistoryDto.getClassHistoryCode()));
	
			/* 조회 했던 기존 엔티티의 내용을 수정 */
			oriClassesHistory.update(
					classesHistoryDto.getClassStatus(),
					classesHistoryDto.getStartDate(),
					modelMapper.map(classesHistoryDto.getOpenClasses(), OpenClasses.class));
					
			/* 수강포기일경우 수강인원 -1 */
			if(oriClassesHistory.getClassStatus().equals("수강포기"))
			{
				OpenClasses openClasses = classesRepository.findByClassCode(classesHistoryDto.getOpenClasses().getClassCode())
						.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. classCode=" + classesHistoryDto.getOpenClasses().getClassCode()));
				openClasses.setClassStudents(openClasses.getClassStudents() - 1);
			}
			
		log.info("[ClassesHistoryService] updateClassesHistory End ===================================");

		return classesHistoryDto;
	}


	
	}


	