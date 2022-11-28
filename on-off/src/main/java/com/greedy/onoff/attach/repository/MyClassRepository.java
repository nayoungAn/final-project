package com.greedy.onoff.attach.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.entity.Member;

public interface MyClassRepository extends JpaRepository<OpenClasses, Long>  {
	
	/*1. 강의 목록 조회 - 페이징 , 내강의 조회(강사-본인 강의만 조회)*/
	
	 Page<OpenClasses> findByMember( Pageable pageable, Member teacher);

	
		
	 
	
	/*2. 강의 목록 상세 조회 - 내강의 정보 조회*/
	 
	 Optional<OpenClasses> findByclassCode(Long classCode);

	
	
}
