package com.greedy.onoff.attend.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_ATTEND")
@SequenceGenerator(name = "ATTEND_SEQ_GENERATOR", sequenceName = "SEQ_ATTEND_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Attend {
	
	@Id
	@Column(name = "ATTEND_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTEND_SEQ_GENERATOR")
	private Long attendCode;
	
	@Column(name = "ATTEND_DATE")
	private Date attendDate;
	
	@Column(name = "ATTEND_STATUS")
	private String attendStatus;
	
	/*
	@JoinColumn(name = "MEMBER_CODE")
	@ManyToOne
	private classesHistory student;
	
	@JoinColumn(name = "CLASS_CODE")
	@ManyToOne
	private Classes class;*/
	
}
