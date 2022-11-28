package com.greedy.onoff.notice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.notice.dto.NoticeDto;
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
	@GetMapping("/notice")
	public ResponseEntity<ResponseDto> selectNoticeList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[NoticeController] selectNoticeList Start ===============================");
		log.info("[NoticeController] page : {}", page);
		
		Page<NoticeDto> NoticeDtoList = noticeService.selectNoticeList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(NoticeDtoList);
		
		log.info("[NoticeController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(NoticeDtoList.getContent());
		
		log.info("[NoticeController] selectNoticeList End ===============================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 2. 공지사항 상세 조회 */
	@GetMapping("/notice/{noticeCode}")
	public ResponseEntity<ResponseDto> selectNoticeDetail(@PathVariable Long noticeCode) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지사항 상세 조회 성공", noticeService.selectNotice(noticeCode)));
	}
	
	/* 3. 공지사항 등록 */
	@PostMapping("/notice")
	public ResponseEntity<ResponseDto> insertNotice(@ModelAttribute NoticeDto noticeDto, @AuthenticationPrincipal MemberDto member) {
		
		noticeDto.setMember(member);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지사항 등록 성공", noticeService.insertNotice(noticeDto)));
	}
	
	/* 4. 공지사항 수정 */
	@PutMapping("/notice")
	public ResponseEntity<ResponseDto> updateNotice(@ModelAttribute NoticeDto noticeDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지사항 수정 완료", noticeService.updateNotice(noticeDto)));
	}
	
	/* 4. 공지사항 삭제 */
	@DeleteMapping("/notice/{noticeCode}")
	public ResponseEntity<ResponseDto> deleteNotice(@PathVariable Long noticeCode) {
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지사항 삭제 완료", noticeService.deleteNotice(noticeCode)));
	}
}