package com.greedy.onoff.classes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter	
@Setter
@Entity
@Table(name = "TBL_CLASSES_SCHEDULE")
@IdClass(ClassesSchedulePK.class)
@DynamicInsert
public class ClassesSchedule {

	@Id
	@ManyToOne
	@JoinColumn(name = "DAY_CODE")
    private Day day;
	
	@Id
	@Column(name = "CLASS_CODE")
    private Long classCode;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "TIME_CODE")
    private Time time;

}
