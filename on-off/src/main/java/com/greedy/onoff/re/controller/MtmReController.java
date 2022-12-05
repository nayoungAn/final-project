package com.greedy.onoff.re.controller;



import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.re.dto.ReDto;
import com.greedy.onoff.re.service.ReService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ono")
public class MtmReController {

	private final ReService reService;
	
	public MtmReController(ReService reService) {
		this.reService = reService;
	}
	
	/* 상담 내역 조회 */
	@GetMapping("/myclass/qna/{classCode}")
	public ResponseEntity<ResponseDto> selectQnaList(@PathVariable Long classCode, @RequestParam(name="page", defaultValue="1")int page){
		
		log.info("[MtmReController] page : {}", page);
		
		Page<MtmDto> mtmDtoList = reService.selectQnaList(page, classCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(mtmDtoList);
		log.info("[MtmReController] pageInfo : {}", pageInfo);
		
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(mtmDtoList.getContent());
		
	
		log.info("[MtmReController] selectQnaList End ==============================");
		
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 글 조회 성공", responseDtoWithPaging));
		
	}
	
	/* 상담 상세 조회 */
	@GetMapping("/myclass/qna/classes/{mtmCode}")
	public ResponseEntity<ResponseDto> selectQna(@PathVariable Long mtmCode){
		
		log.info("[MtmReController] pageInfo : {}", mtmCode);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 글 상세 조회 성공", reService.selectQna(mtmCode)));
			
	}
	
	/* 상담 답글 상세 조회*/
	@GetMapping("/myclass/re/classes/{reCode}")
	public ResponseEntity<ResponseDto> selectQnaRe(@PathVariable Long reCode){
		
		log.info("[MtmReController] pageInfo : {}", reCode);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답글 상세 조회 성공", reService.selectQnaRe(reCode)));
			
	}
	
	
	
	/* 상담 답글 작성 */
	@PostMapping("/myclass/qnaReply")
	public ResponseEntity<ResponseDto> insertQnaReply(@RequestBody ReDto mtmReDto, @AuthenticationPrincipal MemberDto memberDto){
		
		mtmReDto.setMember(memberDto);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답글 작성 성공", reService.insertQnaReply(mtmReDto)));
	}	
	
	
	/* 상담 답글 수정*/
	@PutMapping("/myclass/qnaReply")
	public ResponseEntity<ResponseDto> updateQnaReply(@RequestBody ReDto mtmReDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답글 수정 성공", reService.updateQnaReply(mtmReDto)));
	}
	
	
	/*상담 답글 삭제*/
	@DeleteMapping("/myclass/qnaReply/{reCode}")
	public  ResponseEntity <ResponseDto> deleteQnaReply(@PathVariable Long reCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답글 삭제 성공", reService.deleteQnaReply(reCode)));
	}
	
	
}
