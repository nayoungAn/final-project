package com.greedy.onoff.teacher.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.teacher.entity.TeacherHistory;

public interface TeacherHistoryRepository extends JpaRepository<TeacherHistory, Long>{

	List<TeacherHistory> findByMember(Member member);

	Optional<TeacherHistory> findByHistoryCode(Long historyCode);
	

}