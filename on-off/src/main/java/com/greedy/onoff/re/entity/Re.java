package com.greedy.onoff.re.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.onoff.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter	
@Setter
@Entity
@Table(name = "TBL_RE")
@DynamicInsert
/*@SequenceGenerator(name = "RE_SEQ_GENERATOR", 
sequenceName = "SEQ_RE_CODE", 
initialValue = 1, allocationSize = 1)*/

public class Re {
	
	@Id
	@Column(name = "RE_CODE")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RE_SEQ_GENERATOR")
    private Long reCode;

	@Column(name = "RE_TITLE")
    private String reTitle;
	
	@Column(name = "RE_CONTENT")
    private String reContent;

	@Column(name = "RE_DATE")
    private Date reDate;
	
	@Column(name = "RE_STATUS")
    private String reStatus;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
    private Member member;
	
	@Column(name = "MTM_CODE")
    private Long mtmCode;
	
	
}

	public void update(String reTitle, String reContent) {
		this.reTitle = reTitle;
		this.reContent = reContent;
		
	}
	
	
}
