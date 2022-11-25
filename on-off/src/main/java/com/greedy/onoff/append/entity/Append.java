package com.greedy.onoff.append.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.greedy.onoff.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_APPEND")
@SequenceGenerator(name = "APPEND_SEQ_GENERATOR", sequenceName = "SEQ_APPEND_CODE", initialValue = 1, allocationSize = 1 )
@DynamicUpdate
@DynamicInsert
public class Append {
	
	@Id
	@Column(name = "APPEND_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPEND_SEQ_GENERATOR")
	private Long appendCode;
	
	@Column(name = "APPEND_FILE")
	private String appendFile;
	
	@Column(name = "APPEND_SAVE_FILE")
	private String appendSaveFile;
	
	@Column(name = "APPEND_PATH")
	private String appendPath;
	
	@Column(name = "APPEND_TYPE")
	private String appendType;
	
	@Column(name = "APPEND_SORT")
	private String appendSort;
	
	
	
}
