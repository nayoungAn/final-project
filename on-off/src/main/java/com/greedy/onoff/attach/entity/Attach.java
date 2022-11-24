package com.greedy.onoff.attach.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.append.entity.Append;
import com.greedy.onoff.classes.entity.OpenClasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="TBL_ATTACH")
@SequenceGenerator(name = "ATTACH_SEQ_GENERATOR",
sequenceName = "SEQ_ATTACH_CODE",
initialValue = 1, allocationSize = 1)
public class Attach {
	
	@Id
	@Column(name = "ATTACH_CODE")
    private Date attachCode;
	
	@Column(name = "ATTACH_DATE")
    private Date attachDate;
	
	@Column(name = "ATTACH_NOTE")
	private String attachNote;
	
	@ManyToOne 
	@JoinColumn(name = "CLASS_CODE")
	private OpenClasses classes;
	
	@OneToMany
	@JoinColumn(name = "ATTACH_CODE")
	private List<Append>  appendList;
	
}
