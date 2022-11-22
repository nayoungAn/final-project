package com.greedy.onoff.cons.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.cons.entity.ConsEntity;


public interface ConsRepository  extends JpaRepository<ConsEntity, Long>{
	
	/* 전체 조회 */
	Page<ConsEntity> findAll(Pageable pageable);

	/* consId로 조회 */
	Optional<ConsEntity> findByConsCode(Long consCode);
}
