package com.greedy.onoff.subject.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.subject.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	/* 1. 과목 목록 조회 - 페이징, 상태 여부 'N' 제외 (고객) */
	Page<Subject> findAll(Pageable pageable);
//	
//	/* 2. 상품 목록 조회 - 페이징, 주문 불가 상품 포함 (관리자) */
//	/* JpaRepository<Product, Long>에 이미 구현 되어 있는 Page<Product> findAll(Pageable pageable) 사용 가능 */
//	@EntityGraph(attributePaths= {"category"})
//	Page<Product> findAll(Pageable pageable);
//	
//	/* 3. 상품 목록 조회 - 카테고리 기준, 페이징, 주문 불가 상품 제외 (고객) */
//	Page<Product> findByCategoryAndProductOrderable(Pageable pageable, Category category, String productOrderable);
//		
//	/* 4. 상품 목록 조회 - 상품명 검색 기준, 페이징, 주문 불가 상품 제외 (고객) */
//	@EntityGraph(attributePaths= {"category"})
//	Page<Product> findByProductNameContainsAndProductOrderable
//	(Pageable pageable, String productName, String productOrderable);
//	
//	/* 5. 상품 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 제외(고객) */
//	//Optional<Product> findByProductCodeAndProductOrderable(Long productCode, String productOrderable);
//	
//	@Query("SELECT p " +
//	         "FROM Product p " +
//			"WHERE p.productCode = :productCode " +
//	          "AND p.productOrderable = 'Y'")
//    Optional<Product> findByProductCode(@Param("productCode") Long productCode);
//	
//	/* 6. 상품 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 포함(관리자) */
//	/* Optional<Product> findById(Long productCode) 가 이미 구현 되어 있음 */
//	
//	/* 7. 상품 등록 8. 상품 수정 => save 메소드가 이미 구현 되어 있음 */

}
