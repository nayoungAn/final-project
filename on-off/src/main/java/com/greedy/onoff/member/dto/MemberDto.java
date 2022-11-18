package com.greedy.onoff.member.dto;

import java.sql.Date;
import java.util.List;
import com.greedy.onoff.append.dto.AppendDto;
import lombok.Data;

@Data
public class MemberDto {
	
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private Date memberBirthday;
	private String memberGender;
	private String memberAddress;
	private String memberStatus;
	private String memberEmail;
	private Long memberCode;
	private String memberRole;
	private Date memberRegisterDate;
	private List<AppendDto> memberImg;

}
