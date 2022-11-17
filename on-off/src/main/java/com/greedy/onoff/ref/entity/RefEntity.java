package com.greedy.onoff.ref.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.greedy.onoff.acc.dto.AccDto;

public class RefEntity {
	
	@Column(name = "REF_PRICE")
	private Long refPrice;
	
	@Column(name = "REF_DATE")
	private Date refDate;
	
	@Column(name = "REF_CONTENT")
	private String refContent;
	
	@ManyToOne
	@JoinColumn(name = "ACC_CODE")
	private AccDto acc;

}
