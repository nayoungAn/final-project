package com.greedy.onoff.classes.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = "TBL_CLASSES_HISTORY")
@DynamicInsert
@IdClass(ClassesHistoryPK.class)
public class ClassesHistory {
	

	@Id
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private Classes classes;
	
	@Column(name = "CLASS_STATUS")
	private Long classStatus;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	

	

		
	}
