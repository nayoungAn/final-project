package com.greedy.onoff.cons.controller;

import java.io.IOException;
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.cons.dto.ConsDto;
import com.greedy.onoff.cons.service.ConsService;


import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/ono")
public class ConsController {
	
private final ConsService consService;
	
	public ConsController(ConsService consService) {
		this.consService = consService;
	}
	
	@GetMapping("/cons/{consCode}")
	public ResponseEntity<ResponseDto> selectMyMemberInfo(@PathVariable Long consCode) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록상담 조회 성공", consService.selectMyInfo(consCode)));
		
	}
	
	/* 2. 등록 상담 목록 조회 - 페이징, 주문 불가 상품 포함 (관리자) */
	@GetMapping("/cons")
	public ResponseEntity<ResponseDto> selectProductListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[ProductController] selectProductListForAdmin Start ================================");
		log.info("[ProductController] page : {}", page);
		
		Page<ConsDto> productDtoList = consService.selectConsListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(productDtoList);
		 
		log.info("[ProductController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(productDtoList.getContent());
		
		log.info("[ProductController] selectProductListForAdmin End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록상담 조회 성공", responseDtoWithPaging));
	}
	
	/* 7. 등록상담 등록 */
    @PostMapping("/cons")
    public ResponseEntity<ResponseDto> insertCons(@ModelAttribute ConsDto consDto) throws IOException  {
    
    	
    	return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록상담 입력 성공", consService.insertCons(consDto)));
    	
    }
	
    /* 8. 등록상담 수정 */
	@PutMapping("/cons")
	public ResponseEntity<ResponseDto> updateCons(@ModelAttribute ConsDto consDto) throws Exception {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록상담 수정 성공", consService.updateCons(consDto)));
		
	}
	
	/* 8. 등록상담 삭제 */
	@DeleteMapping("/cons/{consCode}")
	public ResponseEntity<ResponseDto> deleteCons(@PathVariable Long consCode) throws Exception {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록상담 삭제 성공", consService.deleteCons(consCode)));
	}
	
	
}
