package com.greedy.onoff.student.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.entity.Member;

public interface StudentClassesRepository extends JpaRepository<ClassesHistory, Long>{

	/* 내강의 조회*/
	Page<ClassesHistory> findByMemberAndClassStatus(Pageable pageable, Member map, String ClassStatus);
	

	@Query("SELECT m " + 
			"FROM OpenClasses m " +
			"WHERE m.classCode = :classCode" 
			
			)
	Optional<OpenClasses> findByClassCode(Long classCode);



}



