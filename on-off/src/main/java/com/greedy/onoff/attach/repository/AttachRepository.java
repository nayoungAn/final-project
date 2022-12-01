package com.greedy.onoff.attach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.onoff.append.entity.Append;
import com.greedy.onoff.attach.entity.Attach;

public interface AttachRepository extends JpaRepository<Attach, String> {
//public interface AttachRepository extends JpaRepository<Attach, List<MultipartFile>> {
	
	

	

	

}
