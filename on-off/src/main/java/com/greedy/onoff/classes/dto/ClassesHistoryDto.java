package com.greedy.onoff.classes.dto;


import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class ClassesHistoryDto {
	
	  private ClassesDto classes;
	  private MemberDto member;
	  private String classStatus;
	
}
