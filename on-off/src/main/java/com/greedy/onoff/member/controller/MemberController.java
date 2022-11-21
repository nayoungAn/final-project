package com.greedy.onoff.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.service.MemberService;

@RestController
@RequestMapping("/ono")
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/teachers")
	public ResponseEntity<ResponseDto> teacherRegister(@ModelAttribute MemberDto memberDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "정상적으로 등록 되었습니다.", memberService.teacherRegister(memberDto)));
	}
}
