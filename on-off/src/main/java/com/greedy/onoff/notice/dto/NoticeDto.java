package com.greedy.onoff.notice.dto;

import java.util.Date;

import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class NoticeDto {

	private Long noticeCode;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private MemberDto memberCode;
	
}