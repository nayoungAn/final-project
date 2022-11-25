package com.greedy.onoff.mtm.controller;

import org.modelmapper.ModelMapper;
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

import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.mtm.service.MtmService;

@RestController
@RequestMapping("/ono")
public class MtmController {

	private final MtmService mtmService;
	
	public MtmController(MtmService mtmService) {
		this.mtmService = mtmService;
	}
	
	//상담내역조회
		@GetMapping("/qna/{classCode}")
		public ResponseEntity<ResponseDto> getMtmList(@PathVariable Long classCode, @RequestParam(name="page", defaultValue="1")int page){
			
			Page <MtmDto> mtmDtoList = mtmService.selectMtmList(page,classCode);
			
			PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(mtmDtoList);
			ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
			responseDtoWithPaging.setPageInfo(pageInfo);
			responseDtoWithPaging.setData(mtmDtoList.getContent());
			
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
		}
	
	
	
	//답글 작성
	@PostMapping("/qnaReply")
	public ResponseEntity<ResponseDto> insertQnaReply(@RequestBody MtmDto mtm, @AuthenticationPrincipal MemberDto member){
		
		mtm.setMember(member);
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답변 작성 성공", mtmService.insertQnaReply(mtm)));
	}
	
	//답글 수정
	@PutMapping("/qnaReply")
	public ResponseEntity<ResponseDto> updateQnaReply(@RequestBody MtmDto mtmDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답변 수정 성공", mtmService.updateQnaReply(mtmDto)));
	}
	
	//답글 삭제
	@DeleteMapping("/qnaReply/{mtmCode}")
	public ResponseEntity<ResponseDto> deleteQnaReply(@PathVariable Long mtmCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "답변 삭제 성공", mtmService.deleteQnaReply(mtmCode)));
	}
	
	
	
	
}
