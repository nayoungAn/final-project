package com.greedy.onoff.member.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.classmate.MemberResolver;
import com.greedy.onoff.jwt.TokenProvider;
import com.greedy.onoff.member.dto.MailDto;
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

	public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
			ModelMapper modelMapper, TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		
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



	public String findId(MemberDto memberDto) {
		
		Member member = memberRepository.findByMemberEmailAndMemberName(memberDto.getMemberEmail(), memberDto.getMemberName())
				.orElseThrow(() -> new FindMemberFaildeException("아이디 찾기에 실패하였습니다."));
				
		return  member.getMemberId();
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
			
			MailUtil mail = new MailUtil();
			mail.sendEmail(member);
			member.update(member.getMemberPassword());
			
		/*	MailDto mailDto = new MailDto();
			
			mailDto.setAddress(memberDto.getMemberEmail());
			mailDto.setTitle(" ONO 임시 비밀번호 안내 이메일입니다.");
			mailDto.setMessage("안녕하세요. ONO 임시비밀번호 안내 관련 이메일 입니다." + "임시 비밀번호는" + tempPw + "입니다.");*/
			
			String securePw = passwordEncoder.encode(memberDto.getMemberPassword());
			memberDto.setMemberPassword(securePw);
			
			result = "true";
			
		}else {
			result = "false";
		}
		
		
		return result;
	}

/*	private String getTempPassword() {
		  
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
	                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	        String str = "";

	        int idx = 0;
	        for (int i = 0; i < 10; i++) {
	            idx = (int) (charSet.length * Math.random());
	            str += charSet[idx];
	        }
	        return str;
	}
	
	public void updatePassword(String str, String userEmail) {
		String pw = passwordEncoder.encode(str);
		
		Member member = memberRepository.findByMemberEmail(userEmail);
		
		memberRepository.save(member, str);
	
	}*/







}
