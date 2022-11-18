package com.greedy.onoff.classes.entity;

import java.io.Serializable;

import com.greedy.onoff.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ClassesHistoryPK implements Serializable {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3990523420602941577L;
	
	private Member member;
	private Classes classes;





}
