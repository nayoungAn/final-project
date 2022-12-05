package com.greedy.onoff.sms.dto;

import java.util.List;

import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class SmsDto {
	
	private List<MemberDto> memberList;
	private String msgContent;

}