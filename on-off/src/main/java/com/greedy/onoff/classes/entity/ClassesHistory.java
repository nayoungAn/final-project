package com.greedy.onoff.classes.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = "TBL_CLASSES_HISTORY")
@DynamicInsert
@SequenceGenerator(name = "CLASSES_HISTORY_SEQ_GENERATOR", 
sequenceName = "SEQ_CLASSES_HISTORY_CODE", 
initialValue = 1, allocationSize = 1)
public class ClassesHistory {
	
	@Id
	@Column(name = "CLASS_HISTORY_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSES_HISTORY_SEQ_GENERATOR")
	private Long classHistoryCode;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private OpenClasses openClasses;
	
	@Column(name = "CLASS_STATUS")
	private Long classStatus;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	

	

		
	}
