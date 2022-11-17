package com.greedy.onoff.classes.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.greedy.onoff.classes.entity.ClassesSchedule;
import com.greedy.onoff.subject.dto.SubjectDto;

import lombok.Data;

@Data
public class ClassesDto {
	
	private Long classCode;
	private String className;
	private Long classQuota;
	private Long classPrice;
	private Date classStartDate;
	private Date classEndDate;
	private String classRoom;
	private String classStatus;
	private String classCircuit;
	private String classDescription;
	private Long classStudents; 
	private MemberDto member;
	private SubjectDto subject;
	private List<ClassesSchedule> classesSchedule;
	

}
