package com.greedy.onoff.cons.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Cons {
	
	@Id
	@Column(name = "CON_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CON_SEQ_GENERATOR")
    private Long consCode;

	@Column(name = "CON_DATE")
	private String consDate;
	
	@Column(name = "CON_NAME")
    private String consName;
	
	@Column(name = "CON_GENDER")
    private String consGender;
	
	@Column(name = "CON_BIRTH")
    private String consBirth;
	
	@Column(name = "CON_TITLE")
    private String consTitle;
	
	@Column(name = "CON_DESCRIPTION")
    private String consDescription;
	
	@Column(name = "CON_PHONE")
    private String consPhone;

	public void updateCons(Long consCode,String consDate, String consName,String consGender,String consBirth, 
			String consTitle, String consDescription, String consPhone) {
		
		this.consCode = consCode;
		this.consDate = consDate;
		this.consName = consName;
		this.consGender = consGender;
		this.consBirth = consBirth;
		this.consTitle = consTitle;
		this.consDescription = consDescription;
		this.consPhone = consPhone;
		
	}


}
