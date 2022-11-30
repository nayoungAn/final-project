package com.greedy.onoff.classes.service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.classes.repository.HistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassesHistoryService {
	
	private final HistoryRepository historyRepository;
	private final ModelMapper modelMapper;
	
	public ClassesHistoryService(HistoryRepository historyRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.historyRepository = historyRepository;

	}
	
	
	/*  수강 이력 등록 */
	@Transactional
	public ClassesHistoryDto insertClassesHistory(ClassesHistoryDto classesHistoryDto) {
		
		log.info("[ClassesHistoryService] insertClassesHistory Start ===================================");
		log.info("[ClassesHistoryService] insertClassesHistory : {}", classesHistoryDto);
		
		historyRepository.save(modelMapper.map(classesHistoryDto, ClassesHistory.class));
			
			
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
					

			
		log.info("[ClassesHistoryService] updateClassesHistory End ===================================");

		return classesHistoryDto;
	}


	
	}


	