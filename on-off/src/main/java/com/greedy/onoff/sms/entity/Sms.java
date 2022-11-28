package com.greedy.onoff.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.greedy.onoff.classes.entity.ClassesHistory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_SMS")
@SequenceGenerator(name = "SMS_SEQ_GENERATOR",
sequenceName = "SEQ_SMS_CODE",
initialValue = 1, allocationSize = 1)
@DynamicInsert
@DynamicUpdate
public class Sms {

	@Id
	@Column(name = "SMS_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SEQ_GENERATOR")
	private Long smsCode;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_HISTORY_CODE")
	private ClassesHistory classesHistory;
}
