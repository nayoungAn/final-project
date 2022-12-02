package com.greedy.onoff.acc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.acc.entity.Acc;
import com.greedy.onoff.member.entity.Member;

public interface AccRepository extends JpaRepository<Acc, Long>{

	/* 수납 내역 조회 */
	Page<Acc> findByAccStatusContains(Pageable pageable, String accStatus);

}
