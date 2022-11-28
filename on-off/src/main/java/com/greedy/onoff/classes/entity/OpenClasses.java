package com.greedy.onoff.classes.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.onoff.attach.entity.Attach;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.subject.entity.Subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter	
@Setter
@Entity
@Table(name = "TBL_CLASSES")
@SequenceGenerator(name = "CLASSES_SEQ_GENERATOR", 
	sequenceName = "SEQ_CLASSES_CODE", 
	initialValue = 1, allocationSize = 1)
@DynamicInsert
public class OpenClasses {
	@Id
	@Column(name = "CLASS_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSES_SEQ_GENERATOR")
	private Long classCode;
	
	@Column(name = "CLASS_NAME")
	private String className;
	
	@Column(name = "CLASS_QUOTA")
	private Long classQuota;
	
	@Column(name = "CLASS_PRICE")
	private Long classPrice;
	
	@Column(name = "CLASS_START_DATE")
	private Date classStartDate;
	
	@Column(name = "CLASS_END_DATE")
	private Date classEndDate;
	
	@Column(name = "CLASS_ROOM")
	private String classRoom;
	
	@Column(name = "CLASS_STATUS")
	private String classStatus;
	
	@Column(name = "CLASS_CIRCUIT")
	private String classCircuit;
	
	@Column(name = "CLASS_DESCRIPTION")
	private String classDescription;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_CODE")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "SUBJECT_CODE")
	private Subject subject;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CLASS_CODE")
	private List<ClassesSchedule> classesScheduleList;
	
	@Column(name = "CLASS_STUDENTS")
	private Long classStudents;

	@OneToMany(mappedBy = "classes")
	private List <Attach> attachList;
	
	public void update(Long classCode, String className, Long classQuota, Long classPrice, Date classStartDate, Date classEndDate,
			String classRoom, String classStatus, String classDescription, Long classStudents,
			List<ClassesSchedule> classesScheduleList, Member member, Subject subject) {
		this.classCode = classCode;
		this.className = className;
		this.classQuota = classQuota;
		this.classPrice = classPrice;
		this.classStartDate = classStartDate;
		this.classEndDate = classEndDate;
		this.classRoom = classRoom;
		this.classStatus = classStatus;
		this.classDescription = classDescription;
		this.classStudents = classStudents;
		this.classesScheduleList = classesScheduleList;
		this.member = member;
		this.subject = subject;
		
		
		
		
	}
	
	
	
	}
