package com.greedy.onoff.classes.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.OpenClasses;


public interface ClassesRepository extends JpaRepository<OpenClasses, Long> {


	/* 강의 목록 조회 - 페이징, 상태 여부 'N' 포함 (관리자) */
	@EntityGraph(attributePaths= {"member"})
	Page<OpenClasses> findAll(Pageable pageable);

	/* 강의 목록 조회 - 강의명 검색 기준, 페이징, 상태 여부 'N' 포함 (관리자)  */
	Page<OpenClasses> findByClassNameContains(Pageable pageable, String className);
	
	/* 강의 상세 조회 - classCode로 강의 1개 조회, 상태 여부 'N' 포함 (관리자) */
	Optional<OpenClasses> findByClassCode(Long classCode) ;




	


	
}
