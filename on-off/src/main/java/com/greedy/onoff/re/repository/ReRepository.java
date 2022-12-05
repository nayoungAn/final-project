package com.greedy.onoff.re.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.re.entity.Re;

public interface ReRepository extends JpaRepository<Re, Long>{




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.onoff.re.entity.Re;



public interface ReRepository extends JpaRepository<Re, Long> {
	
	
	@Query("SELECT r " + 
			"FROM Re r " +
			"WHERE r.reCode = :reCode"
			)
	Optional<Re> findByReCode(@Param("reCode") Long reCode);
	
	
	
}
