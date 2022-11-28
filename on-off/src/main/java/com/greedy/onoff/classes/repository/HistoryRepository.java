package com.greedy.onoff.classes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.member.entity.Member;

public interface HistoryRepository extends JpaRepository<ClassesHistory, Long>{

	/* 강의 조회 */
	List<ClassesHistory> findByMember(Member member);

	
	
}
