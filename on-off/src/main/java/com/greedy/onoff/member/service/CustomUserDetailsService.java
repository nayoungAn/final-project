package com.greedy.onoff.member.service;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.UserNotFoundException;
import com.greedy.onoff.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public CustomUserDetailsService(MemberRepository memberRepository, ModelMapper modelMapper) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		
		return memberRepository.findByMemberId(memberId)
				.map(user -> addAuthorities(user))
				.orElseThrow(() -> new UserNotFoundException(memberId + "찾을 수 없습니다."));
	}
	
	private MemberDto addAuthorities(Member member) {
		
		MemberDto memberDto = modelMapper.map(member, MemberDto.class);
		memberDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberDto.getMemberRole())));
		
		return memberDto;
	}
}
