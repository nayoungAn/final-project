package com.greedy.onoff.member.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.append.entity.Append;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "SEQ_MEMBER_CODE", initialValue = 1, allocationSize = 1)
public class Member {
	
	@Column(name = "MEMBER_ID")
	private String memberId;
		
	@Column(name = "MEMBER_PASSWORD")
	private String memberPassword;
	
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	@Column(name = "MEMBER_PHONE")
	private String memberPhone;
	
	@Column(name = "MEMBER_BIRTHDAY")
	private Date memberBirthday;
	
	@Column(name = "MEMBER_GENDER")
	private String memberGender;
	
	@Column(name = "MEMBER_ADDRESS")
	private String memberAddress;
	
	@Column(name = "MEMBER_STATUS")
	private String memberStatus;
	
	@Column(name = "MEMBER_EMAIL")
	private String memberEmail;
	
	@Id
	@Column(name = "MEMBER_CODE" )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	private Long memberCode;
	
	@Column(name = "MEMBER_ROLE")
	private String memberRole;
	
	@Column(name = "MEMBER_REGISTER_DATE")
	private Date memberRegisterDate;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "MEMBER_CODE")
	private List<Append> memberImg;
}
