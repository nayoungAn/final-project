package com.greedy.onoff.classes.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassesScheduleDto {
	
	private Long scheduleCode;
	//private OpenClassesDto openClasses;
	private DayDto day;
	private TimeDto time;
}
