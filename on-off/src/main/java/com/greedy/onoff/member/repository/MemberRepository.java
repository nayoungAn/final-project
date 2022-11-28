package com.greedy.onoff.member.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.onoff.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByMemberEmail(String memberEmail);

    Optional <Member> findByMemberId(String memberId);

	Optional<Member>findByMemberIdAndMemberEmail(String memberId, String memberEmail);

	Optional<Member> findByMemberNameAndMemberEmail(String memberEmail, String memberName);

	Page<Member> findByMemberRole(Pageable pageable, String memberRole);

	Page<Member> findByMemberNameContainsAndMemberRole(Pageable pageable, String memberName, String memberRole);
	
	/* 현재 등록하는 멤버 코드 값*/
	@Query(value = "SELECT SEQ_MEMBER_CODE.currval FROM dual", nativeQuery = true)
    public Long getCurrvalMemberCodeSequence();
}