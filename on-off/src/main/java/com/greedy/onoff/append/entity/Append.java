package com.greedy.onoff.append.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.onoff.classes.entity.OpenClasses;
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
	
	@Column(name = "APPEND_SAVEFILE")
	private String appendSaveFile;
	
	@Column(name = "APPEND_PATH")
	private String appendPath;
	
//	@Column(name = "APPEND_TYPE")
//	private String appendType;
	
	//@ManyToOne
	@Column(name = "ATTACH_CODE")
	private Long attachCode;
	
	

	
	



	}
	
	
	

