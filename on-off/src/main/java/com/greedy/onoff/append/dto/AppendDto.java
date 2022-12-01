package com.greedy.onoff.append.dto;

import java.util.List;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class AppendDto {
	
	private Long appendCode;
	
	private String appendSaveFile;
	private String appendPath;
	private String appendType;
	private String appendSort;
	private Long classCode;	
	private Long memberCode;
	
	//private String appendFile;
	
//	@JsonIgnore
//	private List<MultipartFile> appendFiles;
	
//	@JsonIgnore
//	private List<MultipartFile> appendFile;
	
}
