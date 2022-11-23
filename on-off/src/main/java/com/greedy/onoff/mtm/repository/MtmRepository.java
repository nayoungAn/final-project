package com.greedy.onoff.mtm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.onoff.mtm.entity.Mtm;

public interface MtmRepository extends JpaRepository<Mtm, Long>{

	Optional<Mtm> findByMtmCode(Long mtmCode);

}
