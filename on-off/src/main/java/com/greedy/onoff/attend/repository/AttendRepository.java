package com.greedy.onoff.attend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.greedy.onoff.attend.entity.Attend;

public interface AttendRepository extends JpaRepository<Attend, Long> {
	
	
	@Query("SELECT a " +
			"FROM  Attend a "+
			"WHERE a.classes.classCode = :classCode " 
			)
	Page<Attend> findByAttendCode(Long classCode, Pageable pageable);
	
	

}
