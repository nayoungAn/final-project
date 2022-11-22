package com.greedy.onoff.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.greedy.onoff.member.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;

	
	public void sendEmail(MemberDto memberDto) {
		
		String subject= memberDto.getMemberName() +"님의 ONO 임시 비밀번호 안내 이메일입니다.";
		String msg = memberDto.getMemberName() + "님의 임시 비밀번호 입니다. 로그인 후 비밀번호를 변경해 주세요. 임시비밀번호는" + memberDto.getMemberPassword() +"입니다.";
		
			
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(memberDto.getMemberEmail()); //수신자
		message.setSubject(subject); //제목
		message.setText(msg); //내용
			
		mailSender.send(message);
		log.info("[sendEmail] message :{}", mailSender);
	}


}
