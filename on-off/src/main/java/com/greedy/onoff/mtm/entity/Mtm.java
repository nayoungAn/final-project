package com.greedy.onoff.mtm.entity;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity	
@Table(name = "TBL_MTM")
@SequenceGenerator(name = "MTM_SEQ_GENERATOR", 
sequenceName = "SEQ_MTM_CODE", 
initialValue = 1, allocationSize = 1)
@DynamicInsert
@DynamicUpdate
public class Mtm {
	
	@Id
	@Column(name = "MTM_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MTM_SEQ_GENERATOR")
    private Long mtmCode;

	@Column(name = "MTM_DATE")
	private Date mtmDate;
	
	@Column(name = "MTM_TITLE")
    private String mtmTitle;
	
	@Column(name = "MTM_DESCRIPTION")
    private String mtmDescription;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private OpenClasses classes;
	
	@Column(name = "MTM_REFER")
    private Long mtmRefer;
	
	@Column(name = "ANSWER_CODE")
    private Long answerCode;
	
	@Column(name = "MTM_DELETE")
    private String mtmDelete;

	public void update(String mtmTitle, String mtmDescription) {
		this.mtmTitle = mtmTitle;
		this.mtmDescription = mtmDescription;
		
	}
	

}
