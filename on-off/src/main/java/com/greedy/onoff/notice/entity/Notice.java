package com.greedy.onoff.notice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_NOTICE")
@SequenceGenerator(name = "NOTICE_SEQ_GENERATOR", 
	sequenceName = "SEQ_NOTICE_CODE", 
	initialValue = 1, allocationSize = 1)
public class Notice {

	@Id
	@Column(name = "NOTICE_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GENERATOR")
	private Long noticeCode;
	
	@Column(name = "NOTICE_TITLE")
	private String noticeTitle;
	
	@Column(name = "NOTICE_CONTENT")
	private String noticeContent;
	
	@Column(name = "NOTICE_DATE")
	private String noticeDate;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	
	/* 수정 용도의 메소드 정의 */
	public void update(String noticeTitle, String noticeContent, String date) {
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeDate = date;
		
	}
}