package com.greedy.onoff.classes.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.service.ClassesService;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class ClassesController {
	
	private final ClassesService classesService;
	
	public ClassesController(ClassesService classesService) {
		this.classesService = classesService;
	}
	
	
	
	/* 강의 목록 조회 - 페이징, 상태 여부 'N' 포함 (관리자) */
	@GetMapping("/classes-management")
	public ResponseEntity<ResponseDto> selectClassListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[ClassesController] selectClassListForAdmin Start ================================");
		log.info("[ClassesController] page : {}", page);
		
		Page<OpenClassesDto> classDtoList = classesService.selectClassListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(classDtoList);
		 
		log.info("[ClassesController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(classDtoList.getContent());
		
		log.info("[ClassesController] selectClassListForAdmin End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}

	/* 강의 목록 조회 - 강의명 검색 기준, 페이징, 상태 여부 'N' 포함 (관리자)  */
	@GetMapping("/classes/search")
	public ResponseEntity<ResponseDto> selectClassesSearchList
		(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String className) {
		
		log.info("[ClassesController] selectClassesSearchList Start ================================");
		log.info("[ClassesController] page : {}", page);
		log.info("[ClassesController] className : {}", className);
		
		Page<OpenClassesDto> classDtoList = classesService.selectClassListByClassName(page, className);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(classDtoList);
		 
		log.info("[ClassesController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(classDtoList.getContent());
		
		log.info("[ClassesController] selectClassesSearchList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

	}


	/* 강의 상세 조회 - classCode로 강의 1개 조회, 상태 여부 'N' 포함 (관리자) */
    @GetMapping("/classes-management/{classCode}")
    public ResponseEntity<ResponseDto> selectClassesDetailForAdmin(@PathVariable Long classCode) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "강사 상세정보 조회 성공",  
        		classesService.selectClassForAdmin(classCode)));
    }
	
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
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
}
