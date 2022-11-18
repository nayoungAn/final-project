package com.greedy.onoff.classes.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ClassesSchedulePK implements Serializable {

	private Day day;
    private Long classCode;
    private Time time;
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3829124470014382343L;
	
}
