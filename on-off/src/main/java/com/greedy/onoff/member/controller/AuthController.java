package com.greedy.onoff.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	//
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody MemberDto memberDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK,"로그인 성공", authService.login(memberDto)));
	}
}
