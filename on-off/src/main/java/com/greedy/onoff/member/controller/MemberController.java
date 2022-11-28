package com.greedy.onoff.member.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ono")
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/* 강사 목록 조회 - 페이징, 상태 'N' 포함, Role teacher (관리자) */
	@GetMapping("/teachers-management")
	public ResponseEntity<ResponseDto> selectTeacherListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[MemberController] selectTeacherListForAdmin Start ================================");
		log.info("[MemberController] page : {}", page);
		
		Page<MemberDto> memberDtoList = memberService.selectTeacherListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberDtoList);
		 
		log.info("[MemberController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		log.info("[MemberController] selectTeacherListForAdmin End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/*  강사 목록 조회 - 상태 'n' 상품 포함 (관리자) */
	@GetMapping("/teachers-management-nopaging")
	public ResponseEntity<ResponseDto> selectTeacherListForAdminNopaging() {
		
		log.info("[SubjectController] selectTeacherListForAdminNopaging Start ================================");

			
		log.info("[SubjectController] selectTeacherListForAdminNopaging End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", memberService.selectTeacherListForAdmin()));
	}
	
	/* 강사목록 조회 - 이름 검색 기준, 페이징, 상태 'N' 포함, Role teacher (관리자) */
	@GetMapping("/teachers/search")
	public ResponseEntity<ResponseDto> selectSearchTeacherList
		(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String memberName) {
		
		log.info("[MemberController] selectSearchTeacherList Start ================================");
		log.info("[MemberController] page : {}", page);
		log.info("[MemberController] productName : {}", memberName);
		
		Page<MemberDto> memberDtoList = memberService.selectTeacherListByMemberName(page, memberName);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberDtoList);
		 
		log.info("[MemberController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		log.info("[MemberController] selectSearchList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

	}
	

	/* 강사 상세 조회 - memberCode로 강사 1명 조회 , 포함상태 'N' 포함 (관리자) */
    @GetMapping("/teachers-management/{memberCode}")
    public ResponseEntity<ResponseDto> selectMemberDetailForAdmin(@PathVariable Long memberCode) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "강사 상세정보 조회 성공",  
        		memberService.selectTeacherForAdmin(memberCode)));
    }
    
	@PostMapping("/teachers")
	public ResponseEntity<ResponseDto> teacherRegister(@ModelAttribute MemberDto memberDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "정상적으로 등록 되었습니다.", memberService.teacherRegister(memberDto)));
	}
	
	/* 강사 */
		@PutMapping("/teachers")
		public ResponseEntity<ResponseDto> updateTeacher(@ModelAttribute MemberDto memberDto) {
			
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "강사 수정 성공", memberService.updateMember(memberDto)));
			
		}
	
}
