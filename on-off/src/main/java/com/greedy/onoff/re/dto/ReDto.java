package com.greedy.onoff.re.dto;

import java.sql.Date;

import com.greedy.onoff.member.dto.MemberDto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReDto {
	
  	private Long reCode;
    private String reTitle;
    private String reContent;
    private String reDate;
    private String reStatus;
    private MemberDto member;
    private Long mtmCode;


}
