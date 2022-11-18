package com.greedy.onoff.notice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.notice.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class NoticeController {

private final NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/* 1. 공지사항 목록 조회 (페이징) */
	
	
	/* 2. 공지사항 상세 조회 */
	
	/* 3. 공지사항 수정 */
	
	/* 4. 공지사항 삭제 */
}
