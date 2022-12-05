package com.greedy.onoff.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.cons.entity.Cons;

public interface StudentClassesDetailRepository extends JpaRepository<OpenClasses, Long>{

	Optional<OpenClasses> findByClassCode(Long classCode);

}
