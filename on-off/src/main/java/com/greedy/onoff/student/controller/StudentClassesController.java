package com.greedy.onoff.student.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.common.paging.Pagenation;
import com.greedy.onoff.common.paging.PagingButtonInfo;
import com.greedy.onoff.common.paging.ResponseDtoWithPaging;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.student.service.StudentClassesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j  
@RestController
@RequestMapping("/ono")
public class StudentClassesController {
	
	
	private final StudentClassesService studentClassesService;
	
	public StudentClassesController(StudentClassesService studentClassesService) {
		this.studentClassesService = studentClassesService;
	}
	
	
	/* 1. 내정보 조회(원생) - 로그인한 원생의 정보 조회  */
	@GetMapping("/memberinfo")
	public ResponseEntity<ResponseDto> myinfoForMember(@AuthenticationPrincipal MemberDto Member) {
		
		log.info("[AttachController] myinfoForMember Start=================================" );
		log.info("[AttachController] member :{}", Member);
		log.info("[AttachController] myinfoForMember End===================================" );
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "원생정보 조회 성공", Member));
	
	
	}
	
	
	
	/* 2. 내강의 목록조회(원생) - 페이징 , 로그인한 멤버의 강의 목록 조회 */
	@GetMapping("/memberclass")
	public ResponseEntity<ResponseDto> myclassListForMember(@RequestParam(name="page", defaultValue="1")int page, @AuthenticationPrincipal MemberDto Member) {
		
		log.info("[AttachController] myclassListForMember Start=================================" );
		log.info("[AttachController] page :{}", page);
		log.info("[AttachController] member :{}", Member);
		
		Page<ClassesHistoryDto> classesDtoList = studentClassesService.myclassListForMember(page, Member);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(classesDtoList);
		log.info("[ProductController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(classesDtoList.getContent());
		
		
		log.info("[AttachController] myclassListForMember End===================================" );
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "내강의 목록 조회성공", responseDtoWithPaging));
	
	
	}
	
	/*3. 내강의 상세조회(원생) - 페이징 , 로그인한 강사의 해당수업 강의만 조회  */
	@GetMapping("/memberclass/{classCode}")
	public ResponseEntity<ResponseDto> myclassDetail(@PathVariable Long classCode) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK,"내강의 상세 정보 조회 성공", studentClassesService.selectMyclass(classCode)));
	}
	
	/* 4. 내 강의 목록조회(등록용) - 노페이징, 로그인한 멤버의 강의 목록 */
	@GetMapping("/classeshistory-nopaging")
	public ResponseEntity<ResponseDto> selectClassHistoryListNopaging(@AuthenticationPrincipal MemberDto Member) {
		
		Long memberCode = Member.getMemberCode();
		log.info("[ClassesController] selectClassListForAdminNopaging Start ================================");
		List<ClassesHistoryDto> classDtoList = studentClassesService.selectClassHistoryListNopaging(Member);
			
		log.info("[ClassesController] selectClassListForAdminNopaging End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", classDtoList));
	}
	
	
	/* 5. 내 상담신청 목록조회(원생) - 페이징 , 로그인한 멤버의 상담 신청 내역 조회  */
	@GetMapping("/memberclass/qna")
	public ResponseEntity<ResponseDto> getMtmList(@RequestParam(name="page", defaultValue="1")int page,@AuthenticationPrincipal MemberDto Member ){
			
		Long memberCode = Member.getMemberCode();
		log.info("멤버코드 : {} ", Member.getMemberCode());
		Page <MtmDto> mtmDtoList = studentClassesService.selectMtmList(page, Member);
		log.info("상담내역조회 : {} ", mtmDtoList);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(mtmDtoList);
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(mtmDtoList.getContent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 신청 목록 조회 성공", responseDtoWithPaging));
	}
	
	
	
	
		//1:1상담 상세조회
		@GetMapping("/memberclass/qnadetail/{mtmCode}")
		public ResponseEntity<ResponseDto> selectMtmDetail(@PathVariable Long mtmCode){
			
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 상세 조회 성공", studentClassesService.selectMtmDetail(mtmCode)));
		}
		
		
		//1:1상담 작성
		@PostMapping("/memberclass/qna")
		public ResponseEntity<ResponseDto> insertQnaReply(@RequestBody MtmDto mtmDto, @AuthenticationPrincipal MemberDto member){
			
			mtmDto.setMember(member);
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 신청 성공", studentClassesService.insertQnaRequest(mtmDto, member)));
		}
		
		//1:1상담 수정
		@PutMapping("/memberclass/qna")
		public ResponseEntity<ResponseDto> updateQnaReply(@RequestBody MtmDto mtmDto){
			
			log.info("받기 {}",mtmDto.toString());
			
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 내용 수정 성공", studentClassesService.updateQnaRequest(mtmDto)));
		}
		
		//1:1상담 삭제
		@DeleteMapping("/memberclass/qna/qnaRequest/{mtmCode}")
		public ResponseEntity<ResponseDto> deleteQnaReply(@PathVariable Long mtmCode){
			
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상담 삭제 성공", studentClassesService.deleteQnaRequest(mtmCode)));
		}
		
		
	

}
