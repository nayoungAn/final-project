package com.greedy.onoff.student.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.notice.entity.Notice;

public interface StudentNoticeRepository extends JpaRepository<Notice, Long>{

	//Page<Notice> findByNoticeNameContains(Pageable pageable, String noticeName);

	Page<Notice> findByNoticeTitleContains(Pageable pageable, String noticeName);

}
