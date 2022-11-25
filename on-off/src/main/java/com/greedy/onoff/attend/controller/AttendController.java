package com.greedy.onoff.attend.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.attend.dto.AttendDto;
import com.greedy.onoff.attend.service.AttendService;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;

@RestController
@RequestMapping("/ono")
public class AttendController {
	
	private final AttendService attendService;
	
	public AttendController(AttendService attendService) {
		this.attendService = attendService;
	}

	@GetMapping("/teacherclass/attend/{classCode}")
	public ResponseEntity<ResponseDto> selectCheckList(@PathVariable Long classCode, @RequestParam(name="page", defaultValue="1") int page){
		
		Page<AttendDto> attendDtoList = attendService.selectCheckList(classCode, page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(attendDtoList);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(attendDtoList.getContent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "출석부 조회 성공", responseDtoWithPaging));
	}
	
}
