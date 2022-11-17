package com.greedy.onoff.classes.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@DynamicInsert
public class ClassesSchedule {

	@ManyToOne
	@JoinColumn(name = "DAY_CODE")
    private Day day;
	
	
	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
    private Classes classes;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "TIME_CODE")
    private Time time;


}
