package com.greedy.onoff.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.onoff.jwt.TokenProvider;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.dto.TokenDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.LoginFailedException;
import com.greedy.onoff.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	
	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}
	
	public TokenDto login(MemberDto memberDto) {
		
		Member member = memberRepository.findByMemberId(memberDto.getMemberId())
							.orElseThrow(() -> new LoginFailedException("1"));
		if(!passwordEncoder.matches(memberDto.getMemberPassword(), member.getMemberPassword())) {
			
			throw new LoginFailedException("2");
		}
		
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(member, MemberDto.class));
		
		return tokenDto;
	}

}
