package com.greedy.onoff.teacher.dto;


import java.util.Date;

import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class TeacherHistoryDto {
	
	private Long historyCode;
	private MemberDto member;
	private Date joinDate;
	private Date RetirementDate;
	
	
}
