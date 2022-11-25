package com.greedy.onoff.cons.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.cons.entity.Cons;



public interface ConsRepository  extends JpaRepository<Cons, Long>{
	
	/* 전체 조회 */
	Page<Cons> findAll(Pageable pageable);

	/* consId로 조회 */
	Optional<Cons> findByConsCode(Long consCode);
}
