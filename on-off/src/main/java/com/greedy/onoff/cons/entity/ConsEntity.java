package com.greedy.onoff.cons.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity	
@Table(name = "TBL_CON")
@SequenceGenerator(name = "CON_SEQ_GENERATOR", 
sequenceName = "SEQ_CON_CODE", 
initialValue = 1, allocationSize = 1)
public class ConsEntity {
	
	@Id
	@Column(name = "CON_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CON_SEQ_GENERATOR")
    private Long conCode;

	@Column(name = "CON_DATE")
	private Date conDate;
	
	@Column(name = "CON_NAME")
    private String conName;
	
	@Column(name = "CON_GENDER")
    private String conGender;
	
	@Column(name = "CON_BIRTH")
    private Date conBirth;
	
	@Column(name = "CON_TITLE")
    private String conTitle;
	
	@Column(name = "CON_DESCRIPTION")
    private String conDescription;
	
	@Column(name = "CON_PHONE")
    private String conPhone;


}
