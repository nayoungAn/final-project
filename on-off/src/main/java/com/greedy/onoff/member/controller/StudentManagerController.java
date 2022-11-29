package com.greedy.onoff.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.service.StudentManagerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class StudentManagerController {
	
	private final StudentManagerService studentManagerService;
	
	public StudentManagerController(StudentManagerService studentManagerService) {
		this.studentManagerService = studentManagerService;
	}
	

	/* 원생 조회 */
	@GetMapping("/student-manager")
	public ResponseEntity<ResponseDto> selectStudentList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[StudentController] selectNoticeList Start ===============================");
		log.info("[StudentController] page : {}", page);
		
		Page<MemberDto> StudentDtoList = studentManagerService.selectStudentList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(StudentDtoList);
		
		log.info("[StudentController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(StudentDtoList.getContent());
		
		log.info("[StudentController] selectNoticeList End ===============================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 원생 상세 조회 (듣고 있는 강의 목록 조회)*/
	@GetMapping("/student-manager/{memberCode}")
	public ResponseEntity<ResponseDto> selectStudentDetail(@PathVariable Long memberCode, MemberDto memberDto) {
		
		// 원생 상세 정보 조회
		MemberDto member = studentManagerService.selectStudent(memberCode);
		
		// 듣고있는 강의 목록 조회
		List<ClassesHistoryDto> lectureList = studentManagerService.studentClassList(member.getMemberCode(), member);
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("lectureList", lectureList);
		data.put("memberInfo", member);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생 상세 조회 성공", data));
	}
	
	/* 원생 상세 조회 */
	@GetMapping("/student-managers/{memberCode}")
	public ResponseEntity<ResponseDto> selectStudentManagerDetail(@PathVariable Long memberCode) {
		

		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생 상세 조회 성공", 
				studentManagerService.selectStudentDetail(memberCode)));
	}
	
	
	
	/* 원생 정보 수정 */
	@PutMapping("/student-manager")
	public ResponseEntity<ResponseDto> updateStudent(MemberDto memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생정보 수정 완료", studentManagerService.updateStudent(memberDto)));
	}
	
	/* 원생 이름 검색 */
	@GetMapping("/student-manager/search")
	public ResponseEntity<ResponseDto> selectSearchList
		(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String memberName) {
		
		log.info("[StudentController] selectSearchList Start ================================");
		log.info("[StudentController] page : {}", page);
		log.info("[StudentController] studentName : {}", memberName);
		
		Page<MemberDto> memberDtoList = studentManagerService.selectStudentName(page, memberName);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberDtoList);
		 
		log.info("[StudentController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		log.info("[StudentController] selectSearchList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생 이름 검색 완료", responseDtoWithPaging));

	}
	
	
	/* 원생 등록 */
	@PostMapping("/student-manager")
	public ResponseEntity<ResponseDto> signupStudent(@RequestBody MemberDto memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "원생 등록 성공", studentManagerService.signupStudent(memberDto)));
	}
	
	
	/*  원생 목록 조회 - 상태 'n' 포함 , 페이징 X (관리자) */
	@GetMapping("/students-management-nopaging")
	public ResponseEntity<ResponseDto> selectStudentListForAdminNopaging() {
		
		log.info("[StudentController] selectStudentListForAdminNopaging Start ================================");

			
		log.info("[StudentController] selectStudentListForAdminNopaging End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", studentManagerService.selectStudentListForAdmin()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
