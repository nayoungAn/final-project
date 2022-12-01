package com.greedy.onoff.classes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.service.ClassesHistoryService;
import com.greedy.onoff.classes.service.ClassesService;
import com.greedy.onoff.common.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class ClassesHistoryController {
	
	private final ClassesHistoryService classesHistoryService;
	
	public ClassesHistoryController(ClassesHistoryService classesHistoryService) {
		this.classesHistoryService = classesHistoryService;
	}
	
	/* 수강 이력 조회- classHistory로 수강이력 1개 조회, 상태 여부 'N' 포함 (관리자) */
    @GetMapping("/classesHistory/{classHistoryCode}")
    public ResponseEntity<ResponseDto> selectClassHistoryDetailForAdmin(@PathVariable Long classHistoryCode) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "수강 이력 상세정보 조회 성공",  
        		classesHistoryService.selectClassHistoryDetailForAdmin(classHistoryCode)));
    }
	/* 수강 이력 등록 */
    @PostMapping("/classesHistory")
    public ResponseEntity<ResponseDto> insertClassHistory(@RequestBody ClassesHistoryDto classesHistoryDto) {
    	
    	
    	return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "수강 이력 입력 성공", classesHistoryService.insertClassesHistory(classesHistoryDto)));
    	
    }
	
    /* 수강 이력 수정 */
	@PutMapping("/classesHistory")
	public ResponseEntity<ResponseDto> updateClassHistory(@RequestBody ClassesHistoryDto classesHistoryDto) {
		
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "수강 이력 수정 성공", classesHistoryService.updateClassesHistory(classesHistoryDto)));
		
	}
	
}
