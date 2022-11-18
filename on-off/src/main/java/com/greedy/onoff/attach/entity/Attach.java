package com.greedy.onoff.attach.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.classes.dto.ClassesDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="TBL_ATTACH" )
@SequenceGenerator(name = "ATTACH_SEQ_GENERATOR",
sequenceName = "SEQ_CLASS_CODE",
initialValue = 1, allocationSize = 1)
public class Attach {
	
	@Column(name = "ATTACH_CODE")
    private Date attachDate;
	
	
	@Column(name = "ATTACH_NOTE")
	private String attachNote;
	
	@Id
	@ManyToOne 
	@JoinColumn(name = "CLASS_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="CLASSES_SEQ_GENERATOR")
    private List<ClassesDto> classes;
}