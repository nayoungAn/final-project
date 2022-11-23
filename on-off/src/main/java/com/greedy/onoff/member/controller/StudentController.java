package com.greedy.onoff.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	

	/* 원생 조회 */
	@GetMapping("/student")
	public ResponseEntity<ResponseDto> selectStudentList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[StudentController] selectNoticeList Start ===============================");
		log.info("[StudentController] page : {}", page);
		
		Page<MemberDto> StudentDtoList = studentService.selectStudentList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(StudentDtoList);
		
		log.info("[StudentController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(StudentDtoList.getContent());
		
		log.info("[StudentController] selectNoticeList End ===============================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 원생 상세 조회 */
	@GetMapping("/student/{memberCode}")
	public ResponseEntity<ResponseDto> selectStudentDetail(@PathVariable Long memberCode) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생 상세 조회 성공", studentService.selectStudent(memberCode)));
	}
	
	
	/* 원생 정보 수정 */
	@PutMapping("/student")
	public ResponseEntity<ResponseDto> updateStudent(MemberDto memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생정보 수정 완료", studentService.updateStudent(memberDto)));
	}
	
	/* 원생 이름 검색 */
	@GetMapping("/student/search")
	public ResponseEntity<ResponseDto> selectSearchList
		(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String memberName) {
		
		log.info("[StudentController] selectSearchList Start ================================");
		log.info("[StudentController] page : {}", page);
		log.info("[StudentController] studentName : {}", memberName);
		
		Page<MemberDto> memberDtoList = studentService.selectStudentName(page, memberName);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberDtoList);
		 
		log.info("[StudentController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		log.info("[StudentController] selectSearchList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생 이름 검색 완료", responseDtoWithPaging));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
