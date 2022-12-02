package com.greedy.onoff.re.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.mtm.service.MtmService;
import com.greedy.onoff.notice.controller.NoticeController;
import com.greedy.onoff.re.service.ReService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class ReController {
	
private final ReService reService;
	
	public ReController(ReService reService) {
		this.reService = reService;
	}

}
