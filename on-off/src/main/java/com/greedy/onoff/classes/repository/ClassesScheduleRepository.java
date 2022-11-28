package com.greedy.onoff.classes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.ClassesSchedule;

public interface ClassesScheduleRepository extends JpaRepository<ClassesSchedule, Long> {

	List<ClassesSchedule> findByClassCode(Object object);

}
