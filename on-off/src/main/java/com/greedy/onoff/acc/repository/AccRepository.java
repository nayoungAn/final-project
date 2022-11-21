package com.greedy.onoff.acc.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.acc.entity.Acc;

public interface AccRepository extends JpaRepository<Acc, Long>{

	/* 수납 내역 조회 */
	@EntityGraph(attributePaths = {"member"})
	Page<Acc> findAll(org.springframework.data.domain.Pageable pageable);

}
