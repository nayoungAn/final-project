package com.greedy.onoff.attach.entity;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.onoff.classes.entity.Classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="TBL_ATTACH" )
public class Attach {
	
	
	@Column(name = "ATTACH_DATE")
    private Date attachDate;
	
	
	@Column(name = "ATTACH_NOTE")
	private String attachNote;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CLASS_CODE")
	private List<Classes> classes;
}
