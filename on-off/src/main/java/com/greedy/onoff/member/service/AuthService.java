package com.greedy.onoff.member.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.onoff.jwt.TokenProvider;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.dto.TokenDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.FindMemberFaildeException;
import com.greedy.onoff.member.exception.LoginFailedException;
import com.greedy.onoff.member.repository.MemberRepository;
import com.greedy.onoff.util.MailUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	private final MailUtil mailUtil;

	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
			ModelMapper modelMapper, TokenProvider tokenProvider, MailUtil mailUtil) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.mailUtil = mailUtil;
		
	}
	
	public TokenDto login(MemberDto memberDto) {
		log.info("[AuthService] login Start ==================================");
		log.info("[AuthService] memberDto : {}" , memberDto);
		Member member = memberRepository.findByMemberId(memberDto.getMemberId())
							.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));
		if(!passwordEncoder.matches(memberDto.getMemberPassword(), member.getMemberPassword())) {
			log.info("[AuthService] Password Match Fil!!!!!");
			throw new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다.");
		}
		
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(member, MemberDto.class));
        log.info("[AuthService] tokenDto : {}", tokenDto );
		
		log.info("[AuthService] login End ==================================");
		
		return tokenDto;
	}



	public MemberDto findId(MemberDto memberDto) {
		
		Member member = memberRepository.findByMemberNameAndMemberEmail(memberDto.getMemberName(), memberDto.getMemberEmail())
				.orElseThrow(() -> new FindMemberFaildeException("아이디 찾기에 실패하였습니다."));
			
		return modelMapper.map(member, MemberDto.class);
		//return  member.getMemberId();
	}
	

	public Object resetPassword(MemberDto memberDto) {
		log.info("[AuthService] resetPassword Start ==================================");
		log.info("[AuthService] memberDto : {}" , memberDto);
		String result = null;
		Member member = memberRepository.findByMemberIdAndMemberEmail(memberDto.getMemberId(), memberDto.getMemberEmail())
				.orElseThrow(() -> new FindMemberFaildeException("아이디 또는 이메일을 정확하게 입력해 주세요."));
		
		if(member != null) {
				
			String tempPw = UUID.randomUUID().toString().replace("-", "");
			tempPw = tempPw.substring(0,10);
			
			memberDto.setMemberPassword(tempPw);
		
			mailUtil.sendEmail(memberDto);
			log.info("[AuthService] memberDto : {}" , memberDto);
			
			String securePw = passwordEncoder.encode(memberDto.getMemberPassword());
			memberDto.setMemberPassword(securePw);
			log.info("[AuthService] securePw : {}" , securePw);
			
			member.update(memberDto.getMemberPassword());
			log.info("[AuthService] Password : {}" , member.getMemberPassword());
			memberRepository.save(member);
			
			result = "true";
			
		}else {
			result = "false";
		}
		
		
		return result;
	}

}
