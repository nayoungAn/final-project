package com.greedy.onoff.subject.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.subject.dto.SubjectDto;
import com.greedy.onoff.subject.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	/* 1. 과목 목록 조회 - 페이징, 상태 여부 'N' 제외 (고객) */
	Page<Subject> findAll(Pageable pageable);

		
	/* 2. 과목 목록 조회 - 상품명 검색 기준, 페이징, 상태 여부 'N' 포함 (관리자) */
	Page<Subject> findBySubjectNameContains(Pageable pageable, String subjectName);

	/* 3. 과목 상세 조회 - subjectCode로 과목 1개 조회, 상태 여부 'Y' 포함 상품 포함(관리자) */
	 Optional<Subject> findById(Long subjectCode);

	Subject findBySubjectName(String subjectName);
	 
	 /* 과목 삭제 */
	  Optional<Subject> findBySubjectCode(Long subjectCode);




	
}
