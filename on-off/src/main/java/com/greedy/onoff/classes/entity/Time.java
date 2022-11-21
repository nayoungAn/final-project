package com.greedy.onoff.classes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter	
@Setter
@Entity
@Table(name = "TBL_TIME")
public class Time {

	@Id
	@Column(name = "TIME_CODE")
    private Long timeCode;
    
	@Column(name = "TIME_NAME")
    private String timeName;

    
}
