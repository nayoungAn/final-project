package com.greedy.onoff.teacher.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "TBL_TEACHER_HISTORY")
@SequenceGenerator(name = "TEACHER_HISTORY_SEQ_GENERATOR", sequenceName = "SEQ_TEACHER_HISTORY_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class TeacherHistory {

	@Id
	@Column(name = "HISOTRY_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHER_HISTORY_SEQ_GENERATOR")
    private Long historyCode;
    
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	

	@Column(name = "JOIN_DATE")
	private Date JoinDate;
	
	@Column(name = "RETIREMENT_DATE")
	private Date retirementDate;
	
	
	
    
}
