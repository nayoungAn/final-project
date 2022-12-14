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
	

	/* ์์ ์กฐํ */
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
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์กฐํ ์ฑ๊ณต", responseDtoWithPaging));
	}
	
	/* ์์ ์์ธ ์กฐํ (๋ฃ๊ณ? ์๋ ๊ฐ์ ๋ชฉ๋ก ์กฐํ)*/
	@GetMapping("/student-manager/{memberCode}")
	public ResponseEntity<ResponseDto> selectStudentDetail(@PathVariable Long memberCode, MemberDto memberDto) {
		
		// ์์ ์์ธ ์?๋ณด ์กฐํ
		MemberDto member = studentManagerService.selectStudent(memberCode);
		
		// ๋ฃ๊ณ?์๋ ๊ฐ์ ๋ชฉ๋ก ์กฐํ
		List<ClassesHistoryDto> lectureList = studentManagerService.studentClassList(member.getMemberCode(), member);
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("lectureList", lectureList);
		data.put("memberInfo", member);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์์ ์์ธ ์กฐํ ์ฑ๊ณต", data));
	}
	
	/* ์์ ์์ธ ์กฐํ */
	@GetMapping("/student-managers/{memberCode}")
	public ResponseEntity<ResponseDto> selectStudentManagerDetail(@PathVariable Long memberCode) {
		

		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์์ ์์ธ ์กฐํ ์ฑ๊ณต", 
				studentManagerService.selectStudentDetail(memberCode)));
	}
	
	
	
	/* ์์ ์?๋ณด ์์? */
	@PutMapping("/student-manager")
	public ResponseEntity<ResponseDto> updateStudent(MemberDto memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์์์?๋ณด ์์? ์๋ฃ", studentManagerService.updateStudent(memberDto)));
	}
	
	/* ์์ ์ด๋ฆ ๊ฒ์ */
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
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์์ ์ด๋ฆ ๊ฒ์ ์๋ฃ", responseDtoWithPaging));

	}
	
	
	/* ์์ ๋ฑ๋ก */
	@PostMapping("/student-manager")
	public ResponseEntity<ResponseDto> signupStudent(@RequestBody MemberDto memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "์์ ๋ฑ๋ก ์ฑ๊ณต", studentManagerService.signupStudent(memberDto)));
	}
	
	
	/*  ์์ ๋ชฉ๋ก ์กฐํ - ์ํ 'n' ํฌํจ , ํ์ด์ง X (๊ด๋ฆฌ์) */
	@GetMapping("/students-management-nopaging")
	public ResponseEntity<ResponseDto> selectStudentListForAdminNopaging() {
		
		log.info("[StudentController] selectStudentListForAdminNopaging Start ================================");

			
		log.info("[StudentController] selectStudentListForAdminNopaging End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์กฐํ ์ฑ๊ณต", studentManagerService.selectStudentListForAdmin()));
	}
	
	/* ์์ด๋ ์ค๋ณต ํ์ธ */
	@GetMapping("/checkMemberId/{memberId}")
	public ResponseEntity<ResponseDto> checkMemberIdDuplicate(@PathVariable String memberId) {
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์์ด๋ ์ค๋ณต ์ฒดํฌ ์๋ฃ", studentManagerService.checkMemberIdDuplicate(memberId)));
	}
	
	/* ์ด๋ฉ์ผ ์ค๋ณต ํ์ธ */
	@GetMapping("/checkEmail/{memberEmail}")
	public ResponseEntity<ResponseDto> checkMemberEmailDuplicate(@PathVariable String memberEmail) {
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "์ด๋ฉ์ผ ์ค๋ณต ์ฒดํฌ ์๋ฃ", studentManagerService.checkMemberEmailDuplicate(memberEmail)));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
