package com.greedy.onoff.attach.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.onoff.append.entity.Append;
import com.greedy.onoff.attach.dto.AttachDto;
import com.greedy.onoff.attach.entity.Attach;
import com.greedy.onoff.classes.dto.OpenClassesDto;

public interface AttachRepository extends JpaRepository<Attach, String> {

	@Query("select a from Attach a where a.openClasses.classCode =:classCode")
	List<Attach> findByOpenClasses(@Param("classCode")Long classCode);

//	OpenClasses findByattachSearch(Long classCode);
//public interface AttachRepository extends JpaRepository<AttachDto, Long> {
	
	
	
//	AttachDto findByattachSearchList(OpenClassesDto classCode);
	
	//AttachDto findByattachSearch(OpenClassesDto classCode, String attachCode);



//	/*4. 내강의 - 첨부파일조회*/
//	Optional<Attach> findByattachSearch(Long classCode, Long attachCode);
	
	

	

	

}
