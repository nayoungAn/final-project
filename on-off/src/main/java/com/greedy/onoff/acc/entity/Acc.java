package com.greedy.onoff.acc.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_ACC")
@SequenceGenerator(name = "ACC_SEQ_GENERATOR",
sequenceName = "SEQ_CON_CODE",
initialValue = 1, allocationSize = 1)
public class Acc {

	@Id
	@Column(name = "ACC_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_SEQ_GENERATOR")
	private Long accCode;
	
	@Column(name = "ACC_DATE")
	private Date accDate;
	
	@Column(name = "ACC_OPTION")
	private String accOption;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@Column(name = "ACC_CONTENT")
	private String accContent;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private OpenClasses classes;
	
}
