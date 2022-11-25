package com.greedy.onoff.cons.dto;



import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ConsDto {
	
	    private Long consCode;
	    private String consDate;
	    private String consName;
	    private String consGender;
	    private String consBirth;
	    private String consTitle;
	    private String consDescription;
	    private String consPhone;
		
		
}
