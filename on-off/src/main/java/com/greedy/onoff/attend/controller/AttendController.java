package com.greedy.onoff.attend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.attend.dto.AttendDto;
import com.greedy.onoff.attend.service.AttendService;
import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class AttendController {
	
	private final AttendService attendService;
	
	public AttendController(AttendService attendService) {
		this.attendService = attendService;
	}
	
	//클래스 별 학생 조회
	@GetMapping("/myclass/attend/{classCode}")
	public ResponseEntity<ResponseDto> selectStudent(@PathVariable Long classCode){
		
		//List<ClassesHistoryDto> historyDtoList = attendService.selectCheckList(classCode); 
		
	
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 조회 성공", attendService.selectStudent(classCode)));
	}
	
	@GetMapping("/myclass/check/{classCode}")
	public ResponseEntity<ResponseDto> selectCheckList(@PathVariable Long classCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "출석부 조회 성동", attendService.selectCheckList(classCode)));
	}
	
	//출석부 수정
	@PutMapping("/myclass/attend")
	public ResponseEntity<ResponseDto> updateAttend(@RequestBody AttendDto attendDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK , "출석부 수정 성공", attendService.updateAttend(attendDto)));
	}
}
