package com.greedy.onoff.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.member.entity.Member;

public interface MemberInsertRepository extends JpaRepository<Member, Long> {
	
	/*이메일 중복*/
	Member findByMemberEmail(String memberEmail);
	
	/*아이디 중복*/
	Member findByMemberId(String memberId);

}
