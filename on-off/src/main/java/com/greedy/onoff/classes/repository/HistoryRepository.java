package com.greedy.onoff.classes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.member.entity.Member;

public interface HistoryRepository extends JpaRepository<ClassesHistory, Long>{

	/* 수강 이력 조회 */
	List<ClassesHistory> findByMember(Member member);

	/* 수강 이력 조회 - classHistoryCode로 수강이력 1개 조회, 상태 여부 'N' 포함 (관리자) */
	Optional<ClassesHistory> findByClassHistoryCode(Long classHistoryCode);
	
	/* 출석부 조회 */
	@Query("SELECT c " +
			"FROM ClassesHistory c " +
			"WHERE c.openClasses.classCode = :classCode " +
			"AND c.classStatus = '수강중'")
	List<ClassesHistory> findByClassCode(@Param("classCode") Long classCode);

	
}
