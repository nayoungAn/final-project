package com.greedy.onoff.student.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.mtm.entity.Mtm;

public interface StudentQnaRepository extends JpaRepository<Mtm, Long> {

	/* 1:1상담 삭제*/
	Mtm findByMtmCode(Long mtmCode);
	
	Page<Mtm> findByMember(Pageable pageable, Member member);
	
	Page<Mtm> findAll(Pageable pageable);

	Page<Mtm> findByMemberAndMtmDeleteAndMtmTitleContains(Pageable pageable, Member member,String mtmdelete, String noticeName);

	Page<Mtm> findByMtmTitleContains(Pageable pageable, String noticeName);

	
	

}
