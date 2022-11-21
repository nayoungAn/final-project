package com.greedy.onoff.subject.controller;


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
	
	/* 1. 과목 목록 조회 - 페이징, 주문 불가 상품 제외 (관리자) */
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
		
		log.info("[ProductController] selectSubjectListForAdmin End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}

//	
//
//	/* 4. 상품 목록 조회 - 상품명 검색 기준, 페이징, 주문 불가 상품 제외 (고객) */
//	@GetMapping("/products/search")
//	public ResponseEntity<ResponseDto> selectSearchList
//		(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String productName) {
//		
//		log.info("[ProductController] selectSearchList Start ================================");
//		log.info("[ProductController] page : {}", page);
//		log.info("[ProductController] productName : {}", productName);
//		
//		Page<ProductDto> productDtoList = productService.selectProductListByProductName(page, productName);
//		
//		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(productDtoList);
//		 
//		log.info("[ProductController] pageInfo : {}", pageInfo);
//		
//		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
//		responseDtoWithPaging.setPageInfo(pageInfo);
//		responseDtoWithPaging.setData(productDtoList.getContent());
//		
//		log.info("[ProductController] selectSearchList End ================================");
//		
//		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
//
//	}
//
//	/* 5. 상품 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 제외(고객) */
//	@GetMapping("/products/{productCode}")
//	public ResponseEntity<ResponseDto> selectProudctDetail(@PathVariable Long productCode) {
//		
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDto(HttpStatus.OK, "상품 상세 정보 조회 성공", productService.selectProduct(productCode)));
//		
//	}
//
//	/* 6. 상품 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 포함(관리자) */
//	/* GET /products-management/{productCode} */
//    @GetMapping("/products-management/{productCode}")
//    public ResponseEntity<ResponseDto> selectProductDetailForAdmin(@PathVariable Long productCode) {
//
//        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 상세정보 조회 성공",  
//        		productService.selectProductForAdmin(productCode)));
//    }
//	
//	/* 7. 상품 등록 */
//    @PostMapping("/products")
//    public ResponseEntity<ResponseDto> insertProduct(@ModelAttribute ProductDto productDto) {
//    	
//    	return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 입력 성공", productService.insertProduct(productDto)));
//    	
//    }
//	
//    /* 8. 상품 수정 */
//	@PutMapping("/products")
//	public ResponseEntity<ResponseDto> updateProduct(@ModelAttribute ProductDto productDto) {
//		
//		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 수정 성공", productService.updateProduct(productDto)));
//		
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
