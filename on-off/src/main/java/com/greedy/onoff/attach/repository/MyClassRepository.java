package com.greedy.onoff.attach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.classes.entity.OpenClasses;

public interface MyClassRepository extends JpaRepository<OpenClasses, Long>  {

}
