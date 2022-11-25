package com.greedy.onoff.mtm.dto;

import java.sql.Date;

import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.member.dto.MemberDto;

import lombok.Data;

@Data
public class MtmDto {
	
	 	private Long mtmCode;
	    private Date mtmDate;
	    private String mtmTitle;
	    private String mtmDescription;
	    private MemberDto member;
	    private OpenClassesDto classes;
	    private Long mtmRefer;
	    private Long answerCode;
	    private String mtmDelete;

}