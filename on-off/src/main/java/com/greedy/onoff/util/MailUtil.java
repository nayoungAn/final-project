package com.greedy.onoff.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.service.AuthService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailUtil {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JavaMailSender mailSender;
	//MailSender mailSender;
	/*private final AuthService authService;
	private final MailSender mailSender;
	
	public MailUtil(MailSender mailSender, AuthService authService) {
		this.mailSender = mailSender;
		this.authService = authService;
	}*/
	
	public void sendEmail(Member member) {
		
		String fromEmail="rhaehfdlvn91@gmail.com";
		String fromName="김융지";
		
		String subject= member.getMemberName() +"님의 ONO 임시 비밀번호 안내 이메일입니다.";
		String msg = member.getMemberName() + "님의 임시 비밀번호 입니다. 로그인 후 비밀번호를 변경해 주세요. 임시비밀번호는" + member.getMemberPassword() +"입니다.";
		
		String mailRecipient = member.getMemberEmail();
		
			
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail); //발신자
		message.setTo(mailRecipient); //수신자
		message.setSubject(subject); //제목
		message.setText(msg); //내용
			
		mailSender.send(message);
		log.info("[sendEmail] message :{}", mailSender);
	}
	
}
