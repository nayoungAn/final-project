package com.greedy.onoff.append.dto;

import javax.persistence.Column;

import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class AppendDto {
	
	private Long appendCode;
	private String appendFile;
	private String appendSaveFile;
	private String appendPath;
	private String appendType;
	private String appendSort;
	private Long classCode;	
	private Long memberCode;
	
}
