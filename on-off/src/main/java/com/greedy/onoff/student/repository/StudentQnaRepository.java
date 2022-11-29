package com.greedy.onoff.student.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.mtm.entity.Mtm;

public interface StudentQnaRepository extends JpaRepository<Mtm, Long> {
	
//	@Query("SELECT m " + 
//			"FROM Mtm m " +
//			"WHERE m.mtmDelete = 'N'" +
//			"AND m.classes.classCode = :classCode" +
//			"AND m.member.memberCode = :memberCode"
//			)
//	Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode, Long memberCode);

	//Mtm findBymtmCode(Long memberCode);
	
	
	//Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode, Long membercode);


	//Object findBymtmCod(Long memberCode);


	//Optional<Mtm> findBymtmCod(MemberDto member);


	//Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode, Mtm membercode);

//	@Query("SELECT m " + 
//			"FROM Mtm m " +
//			"WHERE m.mtmDelete = 'N'" +
//			"AND m.member.memberCode = :memberCode"
//			)
//	Optional<Mtm> findBymemberCode(Long memberCode);
//
//
//	Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode);

//	@Query("SELECT m " + 
//			"FROM Mtm m " +
//			"WHERE m.mtmDelete = 'N'" +
//			"AND m.classes.classCode = :classCode"
//			)
//	Page<Mtm> findByClassCode(Pageable pageable, Long classCode);
	
	
//	@Query("SELECT t " + 
//			"FROM Mtm t " +
//			"WHERE t.mtmDelete = 'N'" +
//			"AND t.classes.classCode = :classCode" 
//			
//	)
//	Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode);
//
//	Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode, Long memberCode);
	
	//Page<Mtm> findByClassCodeAndMember(Pageable pageable, Long classCode, Member member);

	/* 1:1상담 삭제*/
	Mtm findByMtmCode(Long mtmCode);

	//Optional<Mtm> findAll(MemberDto member);
	
	
	Page<Mtm> findByMemberAndClasses(Pageable pageable, Long memberCode , Long classCode);




	

	


//	@Query("SELECT m " + 
//			"FROM Mtm m " +
//			"WHERE m.mtmDelete = 'N'" +
//			"AND m.classes.classCode = :classCode" +
//			"AND m.member.memberCode = :memberCode"
//			)
	//Page<Mtm> findByClassCodeAndMemberCode(Pageable pageable, Long classCode, Long memberCode);
	

}
