package com.greedy.onoff.classes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.member.entity.Member;

public interface HistoryRepository extends JpaRepository<ClassesHistory, Long>{

	/* 수강 이력 조회 */
	List<ClassesHistory> findByMember(Member member);

	/* 수강 이력 조회 - classHistoryCode로 수강이력 1개 조회, 상태 여부 'N' 포함 (관리자) */
	Optional<ClassesHistory> findByClassHistoryCode(Long classHistoryCode);
	
	
	

	
	
}
