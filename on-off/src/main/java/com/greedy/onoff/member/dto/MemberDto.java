package com.greedy.onoff.member.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class MemberDto {
	
	private Long memberCode;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private Date memberBirthday;
	private String memberGender;
	private String memberAddress;
	private String memberStatus;
	private String memberEmail;
	private String memberRole;
	private Date memberRegisterDate;

}
