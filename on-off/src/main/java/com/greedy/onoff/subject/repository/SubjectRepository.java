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
//	
//	/* 5. 과목 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 제외(고객) */
//	//Optional<Product> findByProductCodeAndProductOrderable(Long productCode, String productOrderable);
//	
//	@Query("SELECT p " +
//	         "FROM Product p " +
//			"WHERE p.productCode = :productCode " +
//	          "AND p.productOrderable = 'Y'")
//    Optional<Product> findByProductCode(@Param("productCode") Long productCode);

	/* 3. 과목 상세 조회 - subjectCode로 과목 1개 조회, 상태 여부 'Y' 포함 상품 포함(관리자) */
	 Optional<Subject> findById(Long subjectCode);


	
	
//	/* 7. 과목 수정  8. 과목 수정 => save 메소드가 이미 구현 되어 있음 */

	 
	 /* 과목 삭제 */
	  Optional<Subject> findBySubjectCode(Long subjectCode);

	
}
