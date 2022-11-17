package com.greedy.onoff.classes.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.onoff.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity	
@Table(name = "TBL_CLASSES_HISTORY")

public class ClassesHistory {
	

	
	@Column(name = "CLASS_STATUS")
	private Long classStatus;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private Classes classes;
	

		
	}
