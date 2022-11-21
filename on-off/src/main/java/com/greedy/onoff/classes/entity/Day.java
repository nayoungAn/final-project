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
@Table(name = "TBL_DAY")
public class Day {

	@Id
	@Column(name = "DAY_CODE")
    private Long dayCode;
    
	@Column(name = "DAY_NAME")
    private String dayName;

    
}
