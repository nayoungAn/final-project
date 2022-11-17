package com.greedy.onoff.attach.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="TBL_ATTACH" )

@SequenceGenerator(name = "ATTACH_SEQ_GENERATOR",
		sequenceName = "SEQ_CLASS_CODE")
public class Attach {
	
	/*@Id
	@JoinColumn(name = "CLASS_CODE")
    private Classes classCode;*/
	
	@Column(name = "ATTACH_CODE")
    private Date attachDate;
	
	
	@Column(name = "ATTACH_NOTE")
	private String attachNote;
}
