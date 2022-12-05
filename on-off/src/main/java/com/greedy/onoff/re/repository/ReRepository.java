package com.greedy.onoff.re.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.re.entity.Re;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ReRepository extends JpaRepository<Re, Long> {
	
	
	@Query("SELECT r " + 
			"FROM Re r " +
			"WHERE r.reCode = :reCode"
			)
	Optional<Re> findByReCode(@Param("reCode") Long reCode);
	
	
	
}
