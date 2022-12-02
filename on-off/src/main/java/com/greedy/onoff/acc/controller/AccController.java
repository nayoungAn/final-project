package com.greedy.onoff.acc.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.acc.dto.AccDto;
import com.greedy.onoff.acc.service.AccService;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono/acc")
public class AccController {
	
	private final AccService accService;
	
	public AccController(AccService accService) {
		this.accService = accService;
	}
	
	/* 수납 내역 조회 */
	@GetMapping("/acc/search")
	public ResponseEntity<ResponseDto> selectSearchList(@RequestParam(name="page", defaultValue = "1")
		int page, @RequestParam(name="search") String accStatus){
		
		log.info("[AccController] selectSearchList Start ====================");
		log.info("[AccController] page : {}", page);
		log.info("[AccController] productName : {}", accStatus);
		
		Page<AccDto> accDtoList = accService.selectAccListByAccStatus(page, accStatus);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(accDtoList);
		
		log.info("[AccController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(accDtoList.getContent());
		
		log.info("[AccController] selectSearchList End ====================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "검색 성공!", responseDtoWithPaging));
	}
	
	
	
	/* 수납 내역 상세 조회 */
	@GetMapping("/acc-management/{accCode}")
	public ResponseEntity<ResponseDto> selectAccDetailForAdmin(@PathVariable Long accCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "수납 내역 상세 조회 성공!",
				accService.selectAccForAdmin(accCode)));
	}
	
	/* 수납 내역 수정 */
	@PutMapping("/acc-management")
	public ResponseEntity<ResponseDto> updateAcc(@RequestBody AccDto accDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "수납 내역 수정 성공!", accService.updateAcc(accDto)));
	}

}
