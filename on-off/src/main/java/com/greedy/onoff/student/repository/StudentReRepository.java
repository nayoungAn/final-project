package com.greedy.onoff.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.re.entity.Re;

public interface StudentReRepository extends JpaRepository<Re, Long> {

	

//	Re findByMtmCode(Object object);
//
//	Re findByReTitle(Object object);
//
//	//Re deleteAllByReTitle(Re reList);
//
//	void deleteAllByReTitle(Re reList);
//
//	Re findByMtmMtmCode(Object object);

}
