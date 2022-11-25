package com.greedy.onoff.mtm.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.mtm.entity.Mtm;

public interface MtmRepository extends JpaRepository<Mtm, Long>{



	Optional<Mtm> findByMtmCodeAndMtmReferAndAnswerCode(Long mtmCode, Long mtmRefer, Long answerCode);

	
	@Query("SELECT m " +
			"FROM Mtm m " +
			"WHERE m.mtmCode = :mtmCode " +
			"AND m.mtmRefer = :mtmRefer"
			)
	Mtm findByMtmCodeAndMtmRefer(@Param("mtmCode") Long mtmCode,@Param("mtmRefer") Long mtmRefer);


	Mtm findByMtmCode(Long mtmCode);


	@Query("SELECT m " + 
			"FROM Mtm m " +
			"WHERE m.mtmDelete = 'N'" +
			"AND m.classes.classCode = :classCode"
			)
	Page<Mtm> findByClassCode(Pageable pageable, Long classCode);



	

}
