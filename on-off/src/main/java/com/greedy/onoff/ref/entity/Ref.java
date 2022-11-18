package com.greedy.onoff.ref.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.greedy.onoff.acc.entity.Acc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity	
@Table(name = "TBL_REF")
public class Ref {
	
	@Column(name = "REF_PRICE")
	private Long refPrice;
	
	@Column(name = "REF_DATE")
	private Date refDate;
	
	@Column(name = "REF_CONTENT")
	private String refContent;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ACC_CODE")
	private Acc acc;

}
