package com.greedy.onoff.sms.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.sms.dto.SmsDto;
import com.greedy.onoff.sms.service.SmsService;

@RestController
@RequestMapping("/ono/sms")
public class SmsController {
	
	private final SmsService smsService;
	
	public SmsController(SmsService smsService) {
		this.smsService = smsService;
	}
	
	/* 문자 전송 대상 조회 */
	@GetMapping("/sms-management")
	public ResponseEntity<ResponseDto> selectSmsListForAdmin(@RequestParam(name="page", defaultValue = "1") int page){
		
		Page<SmsDto> smsDtoList = smsService.selectSmsListForAdmin(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(smsDtoList);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(smsDtoList.getContent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "문자 전송 대상 조회 성공!", responseDtoWithPaging));
	}
	
	/* 문자 전송 대상 상세 조회 */
	@GetMapping("/sms-management/{smsCode}")
	public ResponseEntity<ResponseDto> selectSmsDetailForAdmin(@PathVariable Long smsCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "문자 전송 대상 상세 조회 성공!",
				smsService.selectSmsForAdmin(smsCode)));
	}

}
