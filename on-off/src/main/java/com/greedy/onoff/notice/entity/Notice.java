package com.greedy.onoff.notice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_NOTICE")
public class Notice {

	@Id
	@Column(name = "NOTICE_CODE")
	private Long noticeCode;
	
	@Column(name = "NOTICE_TITLE")
	private String noticeTitle;
	
	@Column(name = "NOTICE_CONTENT")
	private String noticeContent;
	
	@Column(name = "NOTICE_DATE")
	private Date noticeDate;
	
	@Column(name = "MEMBER_CODE")
	private Long memberCode;
	
	public void update(String noticeTitle, String noticeContent) {
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		
	}
}