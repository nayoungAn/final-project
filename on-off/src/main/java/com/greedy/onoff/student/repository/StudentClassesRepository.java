package com.greedy.onoff.student.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.mtm.entity.Mtm;

public interface StudentClassesRepository extends JpaRepository<ClassesHistory, Long>{

	/* 내강의 조회*/
	Page<ClassesHistory> findByMemberAndClassStatus(Pageable pageable, Member map, String ClassStatus);
	
	/* 멤버 조회*/
	


}

