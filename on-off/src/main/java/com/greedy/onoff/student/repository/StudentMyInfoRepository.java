package com.greedy.onoff.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.greedy.onoff.member.entity.Member;

public interface StudentMyInfoRepository extends JpaRepository<Member, Long>{
	

}
