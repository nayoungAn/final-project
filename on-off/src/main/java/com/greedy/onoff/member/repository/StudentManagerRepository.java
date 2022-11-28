package com.greedy.onoff.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.member.entity.Member;

public interface StudentManagerRepository extends JpaRepository<Member, Long>{

	/* 원생 이름 검색 */
	Page<Member> findByMemberNameContains(Pageable pageable, String memberName);

	/* 이메일 중복 확인 */
	Member findByMemberEmail(String memberEmail);

	/* 아이디 중복 확인 */
	Member findByMemberId(String memberId);

	/* 원생 조회 필터링 ROLE_STUDENT만 조회 */
	Page<Member> findByMemberRole(Pageable pageable, String memberRole);

	
}
