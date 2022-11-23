package com.greedy.onoff.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.member.entity.Member;

public interface StudentRepository extends JpaRepository<Member, Long>{

	/* 원생 이름 검색 */
	Page<Member> findByMemberName(Pageable pageable, String studentName);

	
}
