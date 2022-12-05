package com.greedy.onoff.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.onoff.classes.entity.ClassesHistory;

public interface SmsRepository extends JpaRepository<ClassesHistory, String>{

	/* 문자 전송 대상 조회 */
	@Query("SELECT h " +
			 "FROM ClassesHistory h " +
			 "JOIN fetch h.openClasses " +
			"WHERE h.openClasses.className like %:className% ")
	
	List<ClassesHistory> findByClassName(@Param("className") String className);
	
	@Query("SELECT h " +
			 "FROM ClassesHistory h " +
			 "JOIN fetch h.member " +
			"WHERE h.member.memberName like %:memberName% ")
	
	List<ClassesHistory> findByMemberName(@Param("memberName")String memberName);		

}
