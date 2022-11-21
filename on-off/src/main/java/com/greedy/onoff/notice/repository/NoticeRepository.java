package com.greedy.onoff.notice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.onoff.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	


	/* 1. 공지사항 목록 조회 - 페이징 */
	
	/* 2. 공지사항 상세 조회 */
	@Query("SELECT n " +
			"FROM Notice n " +
			"WHERE n.noticeCode = :noticeCode")
	Optional<Notice> findByNoticeCode(@Param("noticeCode") Long noticeCode);
	
	/* 3. 공지사항 등록 4. 공지사항 수정 -> save 메소드로 구현 */
}
