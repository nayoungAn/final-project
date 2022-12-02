package com.greedy.onoff.teacher.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.teacher.service.TeacherHistoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ono")
@Slf4j
public class TeacherHistoryController {
	
	private final TeacherHistoryService teacherHistoryService;
	
	public TeacherHistoryController(TeacherHistoryService teacherHistoryService) {
		this.teacherHistoryService = teacherHistoryService;
	}
	

	/* 강사 이력 목록 조회(관리자) */
	@GetMapping("/teachersHistory-management-nopaging/{memberCode}")
	public ResponseEntity<ResponseDto> selectTeacherHistoryListForAdminNopaging(@PathVariable Long memberCode) {
		
		log.info("[TeacherHistoryController] selectTeacherHistoryListForAdminNopaging Start ================================");

			
		log.info("[TeacherHistoryController] selectTeacherHistoryListForAdminNopaging End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", teacherHistoryService.selectTeacherHistoryListForAdmin(memberCode)));
	}
	

	
	
}
