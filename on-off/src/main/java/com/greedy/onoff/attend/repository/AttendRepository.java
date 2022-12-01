package com.greedy.onoff.attend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import com.greedy.onoff.attend.dto.AttendDto;
import com.greedy.onoff.attend.entity.Attend;
import com.greedy.onoff.classes.entity.ClassesHistory;

public interface AttendRepository extends JpaRepository<Attend, Long> {

	
	//출석부 조회
	@Query("SELECT a " + 
			"FROM Attend a " +
			"WHERE a.classes.classCode = :classCode"
			)
	List <Attend> findByClassCode(@Param("classCode") Long classCode);


	

	

}
