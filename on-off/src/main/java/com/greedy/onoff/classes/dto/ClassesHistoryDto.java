package com.greedy.onoff.classes.dto;


import lombok.Data;

@Data
public class ClassesHistoryDto {
	
	  private ClassesDto classes;
	  private MemberDto member;
	  private String classStatus;
	
}
