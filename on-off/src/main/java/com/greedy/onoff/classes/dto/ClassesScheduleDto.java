package com.greedy.onoff.classes.dto;


import lombok.Data;

@Data
public class ClassesScheduleDto {
	
	  private OpenClassesDto openClasses;
	  private DayDto day;
	  private TimeDto time;
	  private Long scheduleCode;
}
