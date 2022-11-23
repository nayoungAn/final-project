/*package com.greedy.onoff.mtm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.mtm.service.MtmService;

@RestController
@RequestMapping("/ono")
public class MtmController {

	private final MtmService mtmService;
	
	public MtmController(MtmService mtmService) {
		this.mtmService = mtmService;
	}
	
	@GetMapping("/mtm")
	public ResponseEntity<ResponseDto> getMtmList(@RequestParam(name="page", defaultValue="1")int page){
		
		Page<MtmDto> 
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.ok, "1:1상담 리스트 조회 완료", ));
	}
}*/
