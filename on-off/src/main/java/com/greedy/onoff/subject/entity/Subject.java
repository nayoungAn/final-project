package com.greedy.onoff.subject.entity;

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

@NoArgsConstructor 
@Getter	
@Setter
@Entity
@Table(name = "TBL_SUBJECT")
@SequenceGenerator(name = "SUBJECT_CODE_SEQ_GENERATOR", sequenceName = "SEQ_SUBJECT_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Subject {

	@Id
	@Column(name = "SUBJECT_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBJECT_CODE_SEQ_GENERATOR")
    private Long subjectCode;
    
	@Column(name = "SUBJECT_NAME")
    private String subjectName;

	@Column(name = "SUBJECT_FORM")
    private String subjectForm;

	@Column(name = "SUBJECT_LANGUAGE")
    private String subjectLanguage;

	@Column(name = "SUBJECT_BOOK")
    private String subjectBook;
	
	@Column(name = "SUBJECT_DESCRIPTION")
    private String subjectDescription;
	
	@Column(name = "SUBJECT_LEARNING_OBJECTIVES")
    private String subjectLearningObjectives;

	public void update(String subjectName, String subjectForm, String subjectLanguage, String subjectBook,
			String subjectDescription, String subjectLearningObjectives) {
		this.subjectName =subjectName;
		this.subjectForm =subjectForm;
		this.subjectLanguage =subjectLanguage;
		this.subjectBook =subjectBook;
		this.subjectDescription =subjectDescription;
		this.subjectLearningObjectives =subjectLearningObjectives;


	}
	
	
}
