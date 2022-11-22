package com.greedy.onoff.attach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.attach.service.AttachService;
import com.greedy.onoff.classes.dto.OpenClassesDto;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/ono")
public class AttachController {
	
	private final AttachService attachService;
	
	public AttachController(AttachService attachService) {
		this.attachService = attachService;
	}
}
	
//	
//	@GetMapping("/ono/myclass")
//	public List<OpenClassesDto> myclass(@RequestParam(name="page", defaultValue="1")int page) {
//		
//		log.info("[AttachController] myclass Start=================================" );
//		log.info("[AttachController] page :{}", page);
//		
//		page<
//		
//	}
//	
//	return "ono/myclass";
//	
//
//}
