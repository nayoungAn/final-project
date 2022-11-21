package com.greedy.onoff.classes.dto;


import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class ClassesHistoryDto {
	
	  private OpenClassesDto openClasses;
	  private MemberDto member;
	  private String classStatus;
	  private Long classHistoryCode;
}
