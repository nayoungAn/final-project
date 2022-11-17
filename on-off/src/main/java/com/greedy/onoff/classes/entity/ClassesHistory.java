package com.greedy.onoff.classes.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.classes.dto.ClassesDto;
import com.greedy.onoff.classes.dto.MemberDto;
import com.greedy.onoff.subject.entity.Subject;

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
	
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private Classes classes;
	

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
