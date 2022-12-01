package com.greedy.onoff.student.repository;

import java.util.List;
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

	/* 내강의 목록 조회 - 노페이징*/
	
//	@Query("SELECT m.className " + 
//			"FROM ClassHistory m " +
//			"WHERE m.member.memberCode = :memberCode" +
//			"AND m.classStatus = :classStatus" 
//			
//			)
	List<ClassesHistory> findByMemberMemberCodeAndClassStatus(Long memberCode, String classStatus);




}



