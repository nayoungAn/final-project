package com.greedy.onoff.member.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "SEQ_MEMBER_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
@DynamicUpdate
public class Member {
	
	@Id
	@Column(name = "MEMBER_CODE" )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	private Long memberCode;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "MEMBER_PASSWORD")
	private String memberPassword;
	
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	@Column(name = "MEMBER_PHONE")
	private String memberPhone;
	
	@Column(name = "MEMBER_BIRTHDAY")
	private String memberBirthday;
	
	@Column(name = "MEMBER_GENDER")
	private String memberGender;
	
	@Column(name = "MEMBER_ADDRESS")
	private String memberAddress;
	
	@Column(name = "MEMBER_STATUS")
	private String memberStatus;
	
	@Column(name = "MEMBER_EMAIL")
	private String memberEmail;
	
	@Column(name = "MEMBER_ROLE")
	private String memberRole;
	
	@Column(name = "MEMBER_REGISTER_DATE")
	private Date memberRegisterDate;
	
	@Column(name = "MEMBER_IMAGE_URL")
	private String memberImageUrl;

	

	public void update(String memberPassword) {
		
		this.memberPassword =memberPassword;
		
	}
	
	public void studentUpdate(String memberId, String memberName, String memberPhone,
			String memberAddress, String memberStatus, String memberEmail, String memberRole, String memberImageUrl) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
		this.memberStatus = memberStatus;
		this.memberEmail = memberEmail;
		this.memberRole = memberRole;
		this.memberImageUrl = memberImageUrl;
	}



	public void update(String memberName, String memberPhone, String memberGender, String memberBirthday,
			String memberEmail, String memberStatus, String memberAddress, String memberImageUrl, Date memberRegisterDate) {
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberGender = memberGender;
		this.memberBirthday = memberBirthday;
		this.memberEmail = memberEmail;
		this.memberStatus = memberStatus;
		this.memberAddress = memberAddress;
		this.memberImageUrl = memberImageUrl;
		this.memberRegisterDate = memberRegisterDate;

	}



	
}