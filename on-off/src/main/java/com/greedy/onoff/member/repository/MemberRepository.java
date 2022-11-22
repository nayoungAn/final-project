package com.greedy.onoff.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByMemberEmail(String memberEmail);

	Optional<Member> findByMemberId(String memberId);

	Optional<Member> findByMemberEmailAndMemberName(String memberEmail, String memberName);

	Optional<Member>findByMemberIdAndMemberEmail(String memberId, String memberEmail);

	Optional<Member> findByMemberNameAndMemberEmail(String memberEmail, String memberName);

	
}
