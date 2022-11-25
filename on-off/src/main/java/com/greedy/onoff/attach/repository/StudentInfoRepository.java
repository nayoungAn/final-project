package com.greedy.onoff.attach.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.onoff.member.entity.Member;

public interface StudentInfoRepository extends JpaRepository<Member, Long> {


	/*7. 학생정보 조회*/
	
	@Query("SELECT m from Member m WHERE m.memberRole='ROLE_STUDENT'")
	Page<Member> findByMemberNameContains(Pageable pageable, String memberName);
	

	
	
	/*8. 학생정보 상세 조회*/
	
	

	
}
