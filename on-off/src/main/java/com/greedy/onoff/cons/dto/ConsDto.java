package com.greedy.onoff.cons.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ConsDto {
	
	    private Long conCode;
	    private Date conDate;
	    private String conName;
	    private String conGender;
	    private Date conBirth;
	    private String conTitle;
	    private String conDescription;
	    private String conPhone;

}
