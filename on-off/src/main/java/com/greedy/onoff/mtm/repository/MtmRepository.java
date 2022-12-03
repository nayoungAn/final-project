package com.greedy.onoff.mtm.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.greedy.onoff.mtm.entity.Mtm;

public interface MtmRepository extends JpaRepository<Mtm, Long>{
	
	@EntityGraph(attributePaths = {"reList"})
	@Query("SELECT m " + 
			"FROM Mtm m " +
			"LEFT JOIN m.reList r on (r.reStatus = 'N') " +
			"WHERE m.mtmDelete = 'N' " +
		"AND m.classes.classCode = :classCode "
			)
	Page<Mtm> findByClassCode(Pageable pageable, @Param("classCode") Long classCode);
}

