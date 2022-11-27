package com.greedy.onoff.subject.controller;


import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.subject.dto.SubjectDto;
import com.greedy.onoff.subject.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class SubjectController {
	
	private final SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	/* 1. 과목 목록 조회 - 페이징, 상태 'n' 상품 포함 (관리자) */
	@GetMapping("/subjects-management")
	public ResponseEntity<ResponseDto> selectSubjectListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[SubjectController] selectSubjectListForAdmin Start ================================");
		log.info("[SubjectController] page : {}", page);
		
		Page<SubjectDto> subjectDtoList = subjectService.selectSubjectListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subjectDtoList);
		 
		log.info("[SubjectController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(subjectDtoList.getContent());
		
		log.info("[SubjectController] selectSubjectListForAdmin End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
//
//	/* 1. 과목 목록 조회 - 상태 'n' 상품 포함 (관리자) */
//	@GetMapping("/subjects-management-nopaging")
//	public ResponseEntity<ResponseDto> selectSubjectListForAdminNopaging() {
//		
//		log.info("[SubjectController] selectSubjectListForAdminNopaging Start ================================");
//
//			
//		log.info("[SubjectController] selectSubjectListForAdmin End ================================");
//		
//		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", subjectService.selectSubjectListForAdmin()));
//	}
//	

	/* 2. 과목 검색 목록 조회 - 과목명 검색 기준, 페이징, 상태 'n' 상품 포함(관리자) */
	@GetMapping("/subjects/search")
	public ResponseEntity<ResponseDto> selectSearchList
		(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String subjectName) {
		
		log.info("[SubjectController] selectSearchList Start ================================");
		log.info("[SubjectController] page : {}", page);
		log.info("[SubjectController] subjectName : {}", subjectName);
		
		Page<SubjectDto> subjectDtoList = subjectService.selectSubjectListBySubjectName(page, subjectName);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subjectDtoList);
		 
		log.info("[SubjectController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(subjectDtoList.getContent());
		
		log.info("[SubjectController] selectSearchList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

	}


	/* 3. 과목 상세 조회 - subjectCode로 과목 1개 조회, 주문 불가 상품 포함(관리자) */
	/* GET /subjects-management/{subjectCode} */
    @GetMapping("/subjects-management/{subjectCode}")
    public ResponseEntity<ResponseDto> selectSubjectDetailForAdmin(@PathVariable Long subjectCode) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과목 상세정보 조회 성공",  
        		subjectService.selectSubjectForAdmin(subjectCode)));
    }
	
	/* 4. 과목 등록 */
    @PostMapping("/subjects")
    public ResponseEntity<ResponseDto> insertSubject(@ModelAttribute SubjectDto subjectDto) {
    	
    	return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과목 입력 성공", subjectService.insertSubject(subjectDto)));
    	
    }
	
    /* 8. 과목 수정 */
	@PutMapping("/subjects")
	public ResponseEntity<ResponseDto> updateSubject(@ModelAttribute SubjectDto subjectDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과목 수정 성공", subjectService.updateSubject(subjectDto)));
		
	}
	
	/* 과목 삭제 */
	@DeleteMapping("/subjects/{subjectCode}")
	public ResponseEntity<ResponseDto> removeSubject(@PathVariable Long subjectCode) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과목 삭제 성공", subjectService.deleteSubject(subjectCode)));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
